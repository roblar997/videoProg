package com.example.demo.DAO;

import com.example.demo.entities.Tidslinje;

import javax.ejb.Stateless;
import java.util.LinkedList;
import java.util.List;

@Stateless
public class TidslinjeDAO {

    private List<Tidslinje> tidslinjer;



    public  TidslinjeDAO(){
        this.tidslinjer = new LinkedList<Tidslinje>();
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
