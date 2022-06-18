package com.example.demo.entities;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Objects;

public class Tidslinje {
    private String user;
    private Long timestamp;
    private Integer start;
    private Integer end;
    private String text;
    private Boolean like;
    private Boolean dislike;


    public Tidslinje(String user, Long timestamp, Integer start, Integer end, String text, Boolean like, Boolean dislike) {
        this.user = user;
        this.timestamp = timestamp;
        this.start = start;
        this.end = end;
        this.text = text;
        this.like = like;
        this.dislike = dislike;
    }




    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getLike() {
        return like;
    }

    public void setLike(Boolean like) {
        this.like = like;
    }

    public Boolean getDislike() {
        return dislike;
    }

    public void setDislike(Boolean dislike) {
        this.dislike = dislike;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tidslinje)) return false;
        Tidslinje tidslinje = (Tidslinje) o;
        return Objects.equals(user, tidslinje.user) && Objects.equals(timestamp, tidslinje.timestamp) && Objects.equals(start, tidslinje.start) && Objects.equals(end, tidslinje.end) && Objects.equals(text, tidslinje.text) && Objects.equals(like, tidslinje.like) && Objects.equals(dislike, tidslinje.dislike);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, timestamp, start, end, text, like, dislike);
    }

}
