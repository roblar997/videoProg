package com.example.demo.wrapperServices;

import com.example.demo.entities.InitFenwick;
import com.example.demo.entities.Tidslinje;
import com.example.demo.wrapper.InitFenwickTidslinjeWrapper;

import java.util.List;

public class WrapperService {
    public static InitFenwickTidslinjeWrapper assembleInitFenwickTidslinjeWrapper(InitFenwick initFenwick, List<Tidslinje> tidslinjer){
        InitFenwickTidslinjeWrapper wrapper = new InitFenwickTidslinjeWrapper(initFenwick,tidslinjer);
        return wrapper;
    }
}
