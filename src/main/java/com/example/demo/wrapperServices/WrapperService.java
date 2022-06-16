package com.example.demo.wrapperServices;

import com.example.demo.entities.Feature;
import com.example.demo.entities.InitFenwick;
import com.example.demo.entities.Tidslinje;
import com.example.demo.wrapper.InitFenwickTidslinjeFeatureWrapper;


import java.util.List;

public class WrapperService {
    public static InitFenwickTidslinjeFeatureWrapper assembleInitFenwickTidslinjeWrapper(InitFenwick initFenwick, List<Tidslinje> tidslinjer, List<Feature> featureList){
        InitFenwickTidslinjeFeatureWrapper wrapper = new InitFenwickTidslinjeFeatureWrapper(initFenwick,tidslinjer,featureList);
        return wrapper;
    }
}
