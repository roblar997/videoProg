package com.example.demo.wrapper;

import com.example.demo.entities.Feature;
import com.example.demo.entities.InitFenwick;
import com.example.demo.entities.Tidslinje;

import java.util.List;
import java.util.Objects;

public class tidslinjeMethodWrapper {



    public String getRemoteMethod() {
        return remoteMethod;
    }

    public void setRemoteMethod(String remoteMethod) {
        this.remoteMethod = remoteMethod;
    }

    public String getTimeline() {
        return timeline;
    }

    public void setTimeline(String timeline) {
        this.timeline = timeline;
    }

    private String remoteMethod;
    private String timeline;

}
