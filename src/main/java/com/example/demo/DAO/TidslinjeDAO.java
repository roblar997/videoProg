package com.example.demo.DAO;

import com.example.demo.entities.Tidslinje;
import org.springframework.stereotype.Component;

import javax.ejb.Stateless;
import java.util.LinkedList;
import java.util.List;

@Component
@Stateless
public class TidslinjeDAO {

    private List<Tidslinje> tidslinjer;



    public  TidslinjeDAO(){
        this.tidslinjer = new LinkedList<Tidslinje>();

        //INIT DATA
        Tidslinje tidslinje1 = new Tidslinje("ARE",200000,0,2,"RWR",2,1);
        Tidslinje tidslinje2 = new Tidslinje("rr",300000,0,2,"see",2,1);
        Tidslinje tidslinje3 = new Tidslinje("gse",500000,0,2,"ses",2,1);
        Tidslinje tidslinje4 = new Tidslinje("rr",600000,0,2,"RestsWR",1,1);
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
        return this.tidslinjer;
    }


}
