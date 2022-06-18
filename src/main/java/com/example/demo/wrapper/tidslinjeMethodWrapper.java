package com.example.demo.wrapper;

import com.example.demo.entities.Feature;
import com.example.demo.entities.InitFenwick;
import com.example.demo.entities.Tidslinje;

import java.util.List;
import java.util.Objects;

public class tidslinjeMethodWrapper {

    private String method;
    private Tidslinje tidslinje;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof tidslinjeMethodWrapper)) return false;
        tidslinjeMethodWrapper that = (tidslinjeMethodWrapper) o;
        return Objects.equals(method, that.method) && Objects.equals(tidslinje, that.tidslinje);
    }

    @Override
    public String toString() {
        return "tidslinjeMethodWrapper{" +
                "method='" + method + '\'' +
                ", tidslinje=" + tidslinje +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(method, tidslinje);
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Tidslinje getTidslinje() {
        return tidslinje;
    }

    public void setTidslinje(Tidslinje tidslinje) {
        this.tidslinje = tidslinje;
    }
}
