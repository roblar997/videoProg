package com.example.demo.DAO;

import com.example.demo.entities.Tidslinje;
import com.example.demo.wrapper.tidslinjeCommandWrapper;
import org.springframework.stereotype.Component;

import javax.ejb.Stateless;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
@Stateless
public class TidslinjeDAO {

    private List<Tidslinje> tidslinjer;



    public  TidslinjeDAO(){
        this.tidslinjer = new LinkedList<Tidslinje>();

        //INIT DATA
        Tidslinje tidslinje1 = new Tidslinje(1,"ARE",2655579696709L,2655579696709L,0,2,"RWR",false,true,false);
        Tidslinje tidslinje2 = new Tidslinje(2,"rr",2655579696709L,2655579696709L,0,2,"see",false,true,true);
        Tidslinje tidslinje3 = new Tidslinje(3,"gse",1555637983792L,2655579696709L,0,2,"ses",false,true,false);
        Tidslinje tidslinje4 = new Tidslinje(4,"rr",2655579696709L,2655579696709L,0,2,"RestsWR",false,true,false);
        tidslinjer.add(tidslinje1);
        tidslinjer.add(tidslinje2);
        tidslinjer.add(tidslinje3);
        tidslinjer.add(tidslinje4);
    }

    public List<Tidslinje> getTidslinjer(){
        return this.tidslinjer;
    }

    public String changeTidsline(Tidslinje tidslinje, Integer id){
        Optional<Tidslinje> tidslinjeOpt = this.tidslinjer.stream().filter((x)->x.getId() == id).findFirst();
        if(tidslinjeOpt.isPresent()){
            Tidslinje preTidslinje = tidslinjeOpt.get();
            preTidslinje.setDeleted(tidslinje.getDeleted());
            preTidslinje.setDislike(tidslinje.getDislike());
            preTidslinje.setEnd(tidslinje.getEnd());
            preTidslinje.setStart(tidslinje.getStart());
            preTidslinje.setText(tidslinje.getText());
            preTidslinje.setUser(tidslinje.getUser());
            preTidslinje.setTimestampChanged(tidslinje.getTimestampChanged());
            preTidslinje.setTimestampCreated(tidslinje.getTimestampCreated());
            preTidslinje.setLike(tidslinje.getLike());
            return "OK";
        }
        return "NOTFOUND";
    }
    public String removeTidsline(Integer id){
       Optional<Tidslinje> tidslinjeOpt = this.tidslinjer.stream().filter((x)->x.getId() == id).findFirst();
       if(tidslinjeOpt.isPresent()){
           this.tidslinjer.remove(tidslinjeOpt.get());
           return "OK";
       }
       return "NOTFOUND";
    }
    public Tidslinje addTidslinje(Tidslinje tidslinje){
            Integer id = 55;
            tidslinje.setId(id);
            return  tidslinje;
    }

    public List<Tidslinje> getLatestChangedOrAdded(Long timestamp){

        //Get newest changes
        return tidslinjer.stream().filter(x-> x.getTimestampChanged() > timestamp).collect(Collectors.toList());

    }
    public List<Tidslinje> getLatestChanged(Long timestamp){

        //Get newest changes
        return tidslinjer.stream().filter(x-> x.getTimestampCreated() != x.getTimestampChanged() && x.getTimestampChanged() > timestamp).collect(Collectors.toList());

    }

    public List<Tidslinje> getLatestAdded(Long timestamp){

        //Get newest changes
        return tidslinjer.stream().filter(x-> x.getTimestampCreated() > timestamp).collect(Collectors.toList());

    }



}
