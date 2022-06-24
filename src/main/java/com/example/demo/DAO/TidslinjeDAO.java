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
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
@Stateless
public class TidslinjeDAO {


    @Autowired
    private JdbcTemplate db;

    private EntityManagerFactory emf;



    public List<Tidslinje> getTidslinjer(){
       // EntityManager em = emf.createEntityManager();
       // EntityTransaction tx = em.getTransaction();
        String sql =  "SELECT * FROM \"schemaTest\".\"Tidslinje\" WHERE \"isdeleted\" IS False";
        List<Tidslinje> tidslinjer = db.query(sql, new BeanPropertyRowMapper(Tidslinje.class));
        return tidslinjer;
    }

    public String changeTidsline(Tidslinje tidslinje, Integer id){

       // EntityManager em = emf.createEntityManager();
        //EntityTransaction tx = em.getTransaction();
        String sql =  "UPDATE \"schemaTest\".\"Tidslinje\" SET \"user\"=?, \"timestampcreated\"=?, \"timestampchanged\"=?, \"start\"=?, \"end\"=?, \"text\"=?, \"like\"=?, \"dislike\"=?, \"isdeleted\"=? WHERE \"id\"=?";
        db.update(sql,tidslinje.getUser(),tidslinje.getTimestampCreated(),tidslinje.getTimestampChanged(),tidslinje.getStart(),tidslinje.getEnd(),tidslinje.getText(),tidslinje.getLike(),tidslinje.getDislike(),tidslinje.getDeleted(),id);

        return "OK";

    }
    public String removeTidsline(Integer id, Long timestampchanged){

        //EntityManager em = emf.createEntityManager();
       // EntityTransaction tx = em.getTransaction();
        //

       String sql =  "UPDATE \"schemaTest\".\"Tidslinje\" SET \"isdeleted\"=TRUE, \"timestampchanged\"=? WHERE \"id\"=?";
       db.update(sql,timestampchanged,id);

       return "OK";

    }
    public Integer addTidslinje(Tidslinje tidslinje){

           // EntityManager em = emf.createEntityManager();
           // EntityTransaction tx = em.getTransaction();
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = "INSERT INTO \"schemaTest\".\"Tidslinje\" (\"user\",\"timestampcreated\",\"timestampchanged\",\"start\",\"end\",\"text\",\"like\",\"dislike\",\"isdeleted\") VALUES(?,?,?,?,?,?,?,?,?)";

        db.update(con -> {
            PreparedStatement query = con.prepareStatement(sql, new String[]{"id"});
            query.setString(1, tidslinje.getUser() );
            query.setObject(2, tidslinje.getTimestampCreated(), java.sql.Types.BIGINT);
            query. setObject(3, tidslinje.getTimestampChanged(), java.sql.Types.BIGINT);
            query.setInt(4, tidslinje.getStart());
            query.setInt(5,tidslinje.getEnd());
            query.setString(6,tidslinje.getText());
            query.setBoolean(7,tidslinje.getLike());
            query.setBoolean(8 ,tidslinje.getDislike());
            query.setBoolean(9 ,tidslinje.getDeleted());
            return query;
        },keyHolder);


        Integer id = keyHolder.getKey().intValue();
        return id;
    }

    public List<Tidslinje> getLatestChangedOrAdded(Long timestamp){

        //EntityManager em = emf.createEntityManager();
        //EntityTransaction tx = em.getTransaction();

        //Get newest changes
        String sql =  "SELECT * FROM \"schemaTest\".\"Tidslinje\" WHERE \"timestampchanged\" >= ? ";
        List<Tidslinje> tidslinjer = db.query(sql,new Long[]{timestamp}, new BeanPropertyRowMapper(Tidslinje.class));
        return tidslinjer;

    }
    public List<Tidslinje> getLatestChanged(Long timestamp){

       // EntityManager em = emf.createEntityManager();
        //EntityTransaction tx = em.getTransaction();
        String sql =  "SELECT * FROM \"schemaTest\".\"Tidslinje\" WHERE \"timestampchanged\" <> \"timestampcreated\" AND \"timestampchanged\" > ? ";
        List<Tidslinje> tidslinjer = db.query(sql,new Long[]{timestamp}, new BeanPropertyRowMapper(Tidslinje.class));
        return tidslinjer;
    }

    public List<Tidslinje> getLatestAdded(Long timestamp){

       // EntityManager em = emf.createEntityManager();
      //  EntityTransaction tx = em.getTransaction();
        String sql =  "SELECT * FROM \"schemaTest\".\"Tidslinje\" WHERE \"timestampcreated\" > ? ";
        List<Tidslinje> tidslinjer = db.query(sql,new Long[]{timestamp}, new BeanPropertyRowMapper(Tidslinje.class));
        return tidslinjer;

    }



}
