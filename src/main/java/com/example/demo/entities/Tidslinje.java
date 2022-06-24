package com.example.demo.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.util.Objects;
//@Entity
//@Table(name="tidslinje", schema = "schemaTest")
public class Tidslinje  {

   // @Id
    private Integer id;
    private String user;
    private Long timestampCreated;
    private Long timestampChanged;
    private Integer start;
    private Integer end;
    private String text;
    private boolean like;
    private boolean dislike;
    private boolean isdeleted;

    public Tidslinje(Integer id, String user, Long timestampCreated, Long timestampChanged, Integer start, Integer end, String text, Boolean like, Boolean dislike, Boolean isdeleted) {
        this.id = id;
        this.user = user;
        this.timestampCreated = timestampCreated;
        this.timestampChanged = timestampChanged;
        this.start = start;
        this.end = end;
        this.text = text;
        this.like = like;
        this.dislike = dislike;
        this.isdeleted = isdeleted;
    }

    public Tidslinje() {

    }

    @Override
    public String toString() {
        return "Tidslinje{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", timestampCreated=" + timestampCreated +
                ", timestampChanged=" + timestampChanged +
                ", start=" + start +
                ", end=" + end +
                ", text='" + text + '\'' +
                ", like=" + like +
                ", dislike=" + dislike +
                ", isdeleted=" + isdeleted +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tidslinje)) return false;
        Tidslinje tidslinje = (Tidslinje) o;
        return Objects.equals(id, tidslinje.id) && Objects.equals(user, tidslinje.user) && Objects.equals(timestampCreated, tidslinje.timestampCreated) && Objects.equals(timestampChanged, tidslinje.timestampChanged) && Objects.equals(start, tidslinje.start) && Objects.equals(end, tidslinje.end) && Objects.equals(text, tidslinje.text) && Objects.equals(like, tidslinje.like) && Objects.equals(dislike, tidslinje.dislike) && Objects.equals(isdeleted, tidslinje.isdeleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, timestampCreated, timestampChanged, start, end, text, like, dislike, isdeleted);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Long getTimestampCreated() {
        return timestampCreated;
    }

    public void setTimestampCreated(Long timestampCreated) {
        this.timestampCreated = timestampCreated;
    }

    public Long getTimestampChanged() {
        return timestampChanged;
    }

    public void setTimestampChanged(Long timestampChanged) {
        this.timestampChanged = timestampChanged;
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

    public Boolean getDeleted() {
        return isdeleted;
    }

    public void setDeleted(Boolean deleted) {
        isdeleted = deleted;
    }
}
