package com.example.demo.wrapper;

import com.example.demo.entities.InitFenwick;
import com.example.demo.entities.Tidslinje;

import java.util.List;
import java.util.Objects;

public class InitFenwickTidslinjeWrapper {

    private InitFenwick initFenwick;

    public InitFenwickTidslinjeWrapper(InitFenwick initFenwick, List<Tidslinje> tidslinjer) {
        this.initFenwick = initFenwick;
        this.tidslinjer = tidslinjer;
    }

    private List<Tidslinje> tidslinjer;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InitFenwickTidslinjeWrapper)) return false;
        InitFenwickTidslinjeWrapper that = (InitFenwickTidslinjeWrapper) o;
        return Objects.equals(initFenwick, that.initFenwick) && Objects.equals(tidslinjer, that.tidslinjer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(initFenwick, tidslinjer);
    }

    public InitFenwick getInitFenwick() {
        return initFenwick;
    }

    public void setInitFenwick(InitFenwick initFenwick) {
        this.initFenwick = initFenwick;
    }

    public List<Tidslinje> getTidslinjer() {
        return tidslinjer;
    }

    public void setTidslinjer(List<Tidslinje> tidslinjer) {
        this.tidslinjer = tidslinjer;
    }


}
