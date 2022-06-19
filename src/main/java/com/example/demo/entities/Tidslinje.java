package com.example.demo.entities;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Objects;

public class Tidslinje {

    private Integer id;
    private String user;
    private Long timestampCreated;
    private Long timestampChanged;
    private Integer start;
    private Integer end;
    private String text;
    private Boolean like;
    private Boolean dislike;
    private Boolean isDeleted;

    public Tidslinje(Integer id, String user, Long timestampCreated, Long timestampChanged, Integer start, Integer end, String text, Boolean like, Boolean dislike, Boolean isDeleted) {
        this.id = id;
        this.user = user;
        this.timestampCreated = timestampCreated;
        this.timestampChanged = timestampChanged;
        this.start = start;
        this.end = end;
        this.text = text;
        this.like = like;
        this.dislike = dislike;
        this.isDeleted = isDeleted;
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
                ", isDeleted=" + isDeleted +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tidslinje)) return false;
        Tidslinje tidslinje = (Tidslinje) o;
        return Objects.equals(id, tidslinje.id) && Objects.equals(user, tidslinje.user) && Objects.equals(timestampCreated, tidslinje.timestampCreated) && Objects.equals(timestampChanged, tidslinje.timestampChanged) && Objects.equals(start, tidslinje.start) && Objects.equals(end, tidslinje.end) && Objects.equals(text, tidslinje.text) && Objects.equals(like, tidslinje.like) && Objects.equals(dislike, tidslinje.dislike) && Objects.equals(isDeleted, tidslinje.isDeleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, timestampCreated, timestampChanged, start, end, text, like, dislike, isDeleted);
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
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
