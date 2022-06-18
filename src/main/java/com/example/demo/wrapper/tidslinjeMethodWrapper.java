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

    @Override
    public String toString() {
        return "tidslinjeMethodWrapper{" +
                "remoteMethod='" + remoteMethod + '\'' +
                ", timeline='" + timeline + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof tidslinjeMethodWrapper)) return false;
        tidslinjeMethodWrapper that = (tidslinjeMethodWrapper) o;
        return Objects.equals(remoteMethod, that.remoteMethod) && Objects.equals(timeline, that.timeline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(remoteMethod, timeline);
    }

    public void setTimeline(String timeline) {
        this.timeline = timeline;
    }

    private String remoteMethod;
    private String timeline;

}
