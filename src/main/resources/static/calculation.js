//Fenwick tree for each meta-data (feature) to add to time-line, to add and extract meta-data to time-slots
//
// Conseptional idea:
//===================
//
// Different part of a video can have different content. Idea is used today to group videos, by tagging
// each video with a label or tag that says something about the content
// Idea here is to push the tags or features into sub-parts of the video. By doing this search-engines
// may gain the oportunity to set start and stop time to appropriate place in video, based on content
// criterum. One may also gain the opportunity to clip out all part of the video containing this wanted feature,
// and paste them together. Doing this against many videos, can make a effective way of searching for vido contents

// Each number 0,1,2... nmbFeatures is associated with some feature. By updating f.update(timeSlot,feature) on
// give a timeslot of a video a feature.
//
// To extract features, use f.queryFeatures(start,stop) to get a list of features in this time range
class FenwFeatureTree {

    constructor(nmbFeatures,size){
        this.nmbFeatures = nmbFeatures
        this.size = size
        this.tree = []
        for(let i = 0; i < size; i++){
            this.tree[i] = []
            for(let j = 0; j < size; j++){
                this.tree[i][j] = 0
            }
        }
    }

    update(timeSlot, feature, val){
        if(timeSlot == 0) return //must start at 1
        while (timeSlot < this.size){
            this.tree[feature][timeSlot] += val
            timeSlot += timeSlot & (-timeSlot)
        }
    }
    addOne(timeSlot,feature){
        this.update(timeSlot, feature, 1)
    }

    removeOne(timeSlot,feature){
        this.update(timeSlot, feature, -1)
    }


    query(timeSlot){

        let returnArray = []
        for(let j = 0; j < this.nmbFeatures; j++){
            returnArray[j] = 0
        }

        while (timeSlot > 0) {
            for (const [feature, value] of Object.entries(returnArray)){
                returnArray[feature] += this.tree[feature][timeSlot]
            }
            timeSlot -= timeSlot & (-timeSlot)
        }

        return returnArray

    }

    listDiff(listA, listB){
        let retList=[]
        for (const [feature, value] of Object.entries(listA)){
            retList.push(listB[feature]-listA[feature])

        }
        return retList
    }
    rangeQuery(l,r){
        let ret = this.listDiff(this.query(l-1),this.query(r))
        return ret
    }


    extractFeatures(liste){
        let res = []
        for (const [feature, value] of Object.entries(liste)){
            if(liste[feature] != 0){
                res.push(feature)
            }

        }
        return res
    }

    queryFeatures(l,r){
        return this.extractFeatures(this.rangeQuery(l,r))
    }

    rangeSearch(liste,l,r){
        let featureList = this.queryFeatures(l,r)
        let resList = [l,r]

        for (const [key, val] of Object.entries(liste)){

            if(!featureList.some(x => x == val))
                return [-1,-1]
        }

        let midtpoint = Math.floor((l+r)/2)

        if(l != r)
            resList = this.rangeSearch(liste,l,midtpoint)



        if (resList.toString() === [-1,-1].toString()){
            resList = this.rangeSearch(liste,midtpoint+1,r)
            if(resList.toString() === [-1,-1].toString())
                return [l,r]
            else
                return resList
        }
        else
            return resList


    }



}
var timeLineModule = (function(){

    let timeLines = [];
    let fenwFeatureTree;
    let timestamp

    async function getInitPState() {
        await $.post("/videoServlet",{ remoteMethod: "getInitState"},(res)=>{
            timestamp = new Date().valueOf();
            this.fenwFeatureTree = new FenwFeatureTree(res.initFenwick.nmbFeatures,res.initFenwick.size)
            //this.timestamp = res.timestamp


            for (const [key, value] of Object.entries(res.featureList)){

                this.fenwFeatureTree.update(value.timeslot,value.featureNmb,value.val);
            }
            for (const [key, value] of Object.entries(res.tidslinjer)){
                timeLines.push(value)
           }

        }).promise();
    }

    async function sendTimePLine(timeline) {
//
        await $.post({
            url: '/videoServlet',
            data: JSON.stringify({ "remoteMethod": "addTimeLine", "timeline": timeline}),
            contentType: "application/json; charset=utf-8"
        }).done((res) => {

            addPTimeLine(res);
        }).promise();

    }
    async function getPChanges() {
        await $.post({
            url: '/videoServlet',
            data: JSON.stringify({ "remoteMethod": "getChanges", "timestamp": timestamp}),
            contentType: "application/json; charset=utf-8"
        }).done((res) => {

               for (let key in JSON.parse(res)){

                   if(res[key].commmand == "ADD"){
                        console.log(res[key].commmand)
                       this.timeLines.push(res[key].timeline)
                   }
                   else if(res[key].command == "CHANGE"){
                       console.log(res[key].commmand)
                       let index = this.timeLines.findIndex((x)=>{return x.id == res[key].timeline.id})
                       this.timeLines.splice(index,1,res[key].timeline)
                   }
                   else if(res[key].command == "REMOVE"){
                       console.log(res[key].commmand)
                       let index = this.timeLines.findIndex((x)=>{return x.id == res[key].timeline.id})
                       this.timeLines.splice(index,1)
                   }
                   else {
                       console.log("ERROR")
                   }
               }
            timestamp = new Date().valueOf();
            $( "#amount" ).val( "" + $( "#slider-range" ).slider( "values", 0 ) +
                " of " + $( "#slider-range" ).slider( "values", 1 ) );
            $( "#likes" ).val(timeLineModule.countLikes( $( "#slider-range" ).slider( "values", 0 ), $( "#slider-range" ).slider( "values", 1 ),$("#percent").val() ) );
            $( "#dislikes" ).val(timeLineModule.countDisLikes( $( "#slider-range" ).slider( "values", 0 ), $( "#slider-range" ).slider( "values", 1 ),$("#percent").val() ) );
            $("#comments").empty()
            obj=timeLineModule.filterListByTime($( "#slider-range" ).slider( "values", 0 ) ,$( "#slider-range" ).slider( "values", 1 ),$("#percent").val() );
            let html = '<div style="background-color:rgb(220,220,220); color:black;margin-bottom: 40px;">';

            for (let key in obj) {
                html += '<p>' +  " <strong>id</strong>: " + obj[key].id + '</p>';
                html += '<p>' +  " <strong>user</strong>: " + obj[key].user + '</p>';
                html += '<p>' +  " <strong>timestampCreated</strong>: " + obj[key].timestampCreated + '</p>';
                html += '<p>' +  " <strong>timestampChanged</strong>: " + obj[key].timestampChanged + '</p>';
                html += '<p>' +  "<strong> text</strong>: " + obj[key].text + '</p>';
                html += '<p>' +  " <strong>like</strong>: " + obj[key].like + '</p>';
                html += '<p>' +  " <strong>dislike</strong>: " + obj[key].dislike + '</p>';
                html += '<p>' +  " <strong>deleted</strong>: " + obj[key].isDeleted + '</p>';
                html += '<div style="background-color:white; color:white">f</div>'
            }

            $(html).appendTo("#comments");
            $("#comments").css("background-color","white")
        }).promise();


    }
    function filterPListByTime(start,end,percent){
        return timeLines.filter((x)=>{
            return x.start >= start && x.end <= end && ((x.start-x.end)/(start-end))*100 >= percent;
        })
    }

    function getPAllTimeLines(){
        return timeLines;
    }

    function extractPFeatures(l,r){
        return this.fenwFeatureTree.queryFeatures(l,r);
    }

    function countPLikes(start,end,percent){
        let timeLinesFilteredTime = filterPListByTime(start,end,percent);
        return timeLinesFilteredTime.reduce((nmbLikes,timeline)=>{
            if(timeline.like) return nmbLikes + 1;
            else              return nmbLikes;
        },0.0)
    }
    function countPDisLikes(start,end,percent){
        let timeLinesFilteredTime = filterPListByTime(start,end,percent);
        return timeLinesFilteredTime.reduce((nmbDisLike,timeline)=>{
            if(timeline.dislike) return nmbDisLike + 1;
            else              return nmbDisLike;
        },0.0)
    }
    function   initPFeatureTree(nmbFeatures,size){
        this.fenwFeatureTree = new FenwFeatureTree(nmbFeatures,size)
    }

    function updateP(timeslot,feature){
        //+1 because we need to start at 1 in fenwick
        this.fenwFeatureTree.addOne(timeslot+1,feature)
    }
    function rangeSearchP(liste,l,r){
        //+1 because we start at 1 in fenwick
        return this.fenwFeatureTree.rangeSearch(liste,l+1,r+1)
    }
    function filterPListByTimeAndUser(start,end,user){
        return timeLines.filter((x)=>{
            return x.start >= start && x.end <= end && x.user == user;
        })
    }


    function addPTimeLine(timeline){
        timeLines.push(timeline);
        $( "#amount" ).val( "" + $( "#slider-range" ).slider( "values", 0 ) +
            " of " + $( "#slider-range" ).slider( "values", 1 ) );
        $( "#likes" ).val(timeLineModule.countLikes( $( "#slider-range" ).slider( "values", 0 ), $( "#slider-range" ).slider( "values", 1 ),$("#percent").val() ) );
        $( "#dislikes" ).val(timeLineModule.countDisLikes( $( "#slider-range" ).slider( "values", 0 ), $( "#slider-range" ).slider( "values", 1 ),$("#percent").val() ) );
        $("#comments").empty()
        obj=timeLineModule.filterListByTime($( "#slider-range" ).slider( "values", 0 ) ,$( "#slider-range" ).slider( "values", 1 ),$("#percent").val() );
        let html = '<div style="background-color:rgb(220,220,220); color:black;margin-bottom: 40px;">';

        for (let key in obj) {
            html += '<p>' +  " <strong>id</strong>: " + obj[key].id + '</p>';
            html += '<p>' +  " <strong>user</strong>: " + obj[key].user + '</p>';
            html += '<p>' +  " <strong>timestampCreated</strong>: " + obj[key].timestampCreated + '</p>';
            html += '<p>' +  " <strong>timestampChanged</strong>: " + obj[key].timestampChanged + '</p>';
            html += '<p>' +  "<strong> text</strong>: " + obj[key].text + '</p>';
            html += '<p>' +  " <strong>like</strong>: " + obj[key].like + '</p>';
            html += '<p>' +  " <strong>dislike</strong>: " + obj[key].dislike + '</p>';
            html += '<p>' +  " <strong>deleted</strong>: " + obj[key].isDeleted + '</p>';
            html += '<div style="background-color:white; color:white">f</div>'
        }

        $(html).appendTo("#comments");
        $("#comments").css("background-color","white")
    }

    return {
        filterListByTime: function (start,end,percent){
            return filterPListByTime(start,end,percent)
        }
        ,
        getAllTimeLines: function(){
            return getPAllTimeLines()
        }
        ,
        countLikes: function(start,end,percent){
            return countPLikes(start,end,percent)
        },
        countDisLikes: function(start,end,percent){
            return countPDisLikes(start,end,percent)
        },
        filterListByTimeAndUser:  function(start,end,user){
            filterListByTimeAndUser(start,end,user)
        },
        getTimeLine: function(commentId){
            getPTimeLine(commentId)
        } ,
        getInitState : async function (){
            await getInitPState().then();
        } ,
        sendTimeLine : function (timeline){
            sendTimePLine(timeline).then();
        } ,
        getChanges : function (){
            getPChanges().then();
        },

        extractFeatureAndUpdate: function(){
            let start=$( "#slider-range" ).slider( "values", 0 )
            let end=$( "#slider-range" ).slider( "values", 1 )
            let featureNumber=$("#featureNumber").val()
            for(let i=start; i <= end; i++){
                //+1 because we start at 1 in fenwick
                updateP(i,featureNumber)
            }

        },
        extractFeatures: function() {
            let start=$( "#slider-range" ).slider( "values", 0 )
            let end=$( "#slider-range" ).slider( "values", 1 )
            return extractPFeatures(start+1, end+1)
        },
        extractTidslinje: function(){


            let tidslinjeData = {
                id: -1,
                user:   $("#commentUser").val().trim(),
                timestampCreated: new Date().valueOf(),
                timestampChanged: new Date().valueOf(),
                start: $( "#slider-range" ).slider( "values", 0 ) ,
                end: $( "#slider-range" ).slider( "values", 1 ),
                text:  $("#commentComment").val().trim(),
                like: $("#likeYes").is(':checked'),
                dislike: $("#dislikeYes").is(':checked'),
                isDeleted: false

            }

            return tidslinjeData;

        },
        initFeatureTree: function(nmbFeatures,size){
            initPFeatureTree(nmbFeatures,size)
        },
        update: function(timeslot,feature){
            updateP(timeslot+1,feature)
        },
        rangeSearch: function (){
            let start=$( "#slider-range" ).slider( "values", 0 )
            let end=$( "#slider-range" ).slider( "values", 1 )
            let liste = $( "#featuresToFind" ).val().split(",")
            let res = rangeSearchP(liste,start+1,end+1)
            return [res[0]-1,res[1]-1]
        }

    }
})();