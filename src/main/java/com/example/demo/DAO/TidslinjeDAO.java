package com.example.demo.DAO;

import com.example.demo.entities.Tidslinje;
import org.springframework.stereotype.Component;

import javax.ejb.Stateless;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


@Component
@Stateless
public class TidslinjeDAO {

    private List<Tidslinje> tidslinjer;



    public  TidslinjeDAO(){
        this.tidslinjer = new LinkedList<Tidslinje>();

        //INIT DATA
        Tidslinje tidslinje1 = new Tidslinje("ARE",2655579696709L,0,2,"RWR",false,true);
        Tidslinje tidslinje2 = new Tidslinje("rr",2655579696709L,0,2,"see",false,true);
        Tidslinje tidslinje3 = new Tidslinje("gse",2655579696709L,0,2,"ses",false,true);
        Tidslinje tidslinje4 = new Tidslinje("rr",2655579696709L,0,2,"RestsWR",false,true);
        tidslinjer.add(tidslinje1);
        tidslinjer.add(tidslinje2);
        tidslinjer.add(tidslinje3);
        tidslinjer.add(tidslinje4);
    }

    public List<Tidslinje> getTidslinjer(){
        return this.tidslinjer;
    }
    public void addTidslinje(Tidslinje tidslinje){

    }

    public List<Tidslinje> getChanges(Long timestamp){

        //Get newest changes
        return tidslinjer.stream().filter(x-> x.getTimestamp() > timestamp).collect(Collectors.toList());

    }


}
