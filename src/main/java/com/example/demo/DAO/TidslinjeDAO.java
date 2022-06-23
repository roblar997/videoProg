package com.example.demo.DAO;

import com.example.demo.entities.Tidslinje;
import com.example.demo.wrapper.tidslinjeCommandWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.sql.PreparedStatement;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
@Stateless
public class TidslinjeDAO {

    //private List<Tidslinje> tidslinjer;

    @Autowired
    private JdbcTemplate db;

    private EntityManagerFactory emf;


    //public  TidslinjeDAO(){
     //   this.tidslinjer = new LinkedList<Tidslinje>();
       // this.emf = Persistence.createEntityManagerFactory("schemaTest");
        //INIT DATA
       // Tidslinje tidslinje1 = new Tidslinje(1,"ARE",2655579696709L,2655579696709L,0,2,"RWR",false,true,false);
       // Tidslinje tidslinje2 = new Tidslinje(2,"rr",2655579696709L,2655579696709L,0,2,"see",false,true,true);
       // Tidslinje tidslinje3 = new Tidslinje(3,"gse",1555637983792L,2655579696709L,0,2,"ses",false,true,false);
       // Tidslinje tidslinje4 = new Tidslinje(4,"rr",2655579696709L,2655579696709L,0,2,"RestsWR",false,true,false);
       // tidslinjer.add(tidslinje1);
        //tidslinjer.add(tidslinje2);
        //tidslinjer.add(tidslinje3);
        //tidslinjer.add(tidslinje4);
   // }

    public List<Tidslinje> getTidslinjer(){
       // EntityManager em = emf.createEntityManager();
       // EntityTransaction tx = em.getTransaction();
        String sql =  "SELECT * FROM \"schemaTest\".\"Tidslinje\"";
        List<Tidslinje> tidslinjer = db.query(sql, new BeanPropertyRowMapper(Tidslinje.class));
        return tidslinjer;
    }

    public String changeTidsline(Tidslinje tidslinje, Integer id){

       // EntityManager em = emf.createEntityManager();
        //EntityTransaction tx = em.getTransaction();
        String sql =  "SELECT * FROM \"schemaTest\".\"Tidslinje\"";
        List<Tidslinje> tidslinjer = db.query(sql, new BeanPropertyRowMapper(Tidslinje.class));
        Optional<Tidslinje> tidslinjeOpt = tidslinjer.stream().filter((x)->x.getId() == id).findFirst();
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

        //EntityManager em = emf.createEntityManager();
       // EntityTransaction tx = em.getTransaction();
        //
       String sql =  "SELECT * FROM \"schemaTest\".\"Tidslinje\"";
       List<Tidslinje> tidslinjer = db.query(sql, new BeanPropertyRowMapper(Tidslinje.class));
       Optional<Tidslinje> tidslinjeOpt = tidslinjer.stream().filter((x)->x.getId() == id).findFirst();
       if(tidslinjeOpt.isPresent()){
           tidslinjeOpt.get().setDeleted(true);
           return "OK";
       }
       return "NOTFOUND";
    }
    public Tidslinje addTidslinje(Tidslinje tidslinje){

           // EntityManager em = emf.createEntityManager();
           // EntityTransaction tx = em.getTransaction();
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = "INSERT INTO \"schemaTest\".\"Tidslinje\" (user,timestampcreated,timestampchanged,start,end,text,like,dislike,isdeleted) VALUES(?,?,?,?,?,?,?,?,?)";

        db.update(con -> {
            PreparedStatement query = con.prepareStatement(sql, new String[]{"id"});
            query.setString(1, tidslinje.getUser() );
            query.setLong(   2, tidslinje.getTimestampCreated());
            query.setLong(3, tidslinje.getTimestampChanged());
            query.setInt(4, tidslinje.getStart());
            query.setInt(5,tidslinje.getEnd());
            query.setString(6,tidslinje.getText());
            query.setBoolean(7,tidslinje.getLike());
            query.setBoolean(8 ,tidslinje.getDislike());
            query.setBoolean(9 ,tidslinje.getDeleted());
            return query;
        },keyHolder);

            Integer id = keyHolder.getKey().intValue();
            tidslinje.setId(id);
            return  tidslinje;
    }

    public List<Tidslinje> getLatestChangedOrAdded(Long timestamp){

        //EntityManager em = emf.createEntityManager();
        //EntityTransaction tx = em.getTransaction();

        //Get newest changes
        String sql =  "SELECT * FROM \"schemaTest\".\"Tidslinje\"";
        List<Tidslinje> tidslinjer = db.query(sql, new BeanPropertyRowMapper(Tidslinje.class));
        return tidslinjer.stream().filter(x-> x.getTimestampChanged() > timestamp).collect(Collectors.toList());

    }
    public List<Tidslinje> getLatestChanged(Long timestamp){

       // EntityManager em = emf.createEntityManager();
        //EntityTransaction tx = em.getTransaction();
        String sql =  "SELECT * FROM \"schemaTest\".\"Tidslinje\"";
        List<Tidslinje> tidslinjer = db.query(sql, new BeanPropertyRowMapper(Tidslinje.class));
        //Get newest changes
        return tidslinjer.stream().filter(x-> x.getTimestampCreated() != x.getTimestampChanged() && x.getTimestampChanged() > timestamp).collect(Collectors.toList());

    }

    public List<Tidslinje> getLatestAdded(Long timestamp){

       // EntityManager em = emf.createEntityManager();
      //  EntityTransaction tx = em.getTransaction();
        String sql =  "SELECT * FROM \"schemaTest\".\"Tidslinje\"";
        List<Tidslinje> tidslinjer = db.query(sql, new BeanPropertyRowMapper(Tidslinje.class));
        //Get newest changes
        return tidslinjer.stream().filter(x-> x.getTimestampCreated() > timestamp).collect(Collectors.toList());

    }



}
