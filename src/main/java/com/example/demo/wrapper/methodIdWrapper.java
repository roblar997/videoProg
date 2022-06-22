package com.example.demo.wrapper;

import com.example.demo.entities.Tidslinje;

import java.util.Objects;

public class methodIdWrapper {


    private String remoteMethod;
    private Integer id;

    @Override
    public String toString() {
        return "methodIdWrapper{" +
                "remoteMethod='" + remoteMethod + '\'' +
                ", id=" + id +
                '}';
    }

    public methodIdWrapper(String remoteMethod, Integer id) {
        this.remoteMethod = remoteMethod;
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof methodIdWrapper)) return false;
        methodIdWrapper that = (methodIdWrapper) o;
        return Objects.equals(remoteMethod, that.remoteMethod) && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(remoteMethod, id);
    }

    public String getRemoteMethod() {
        return remoteMethod;
    }

    public void setRemoteMethod(String remoteMethod) {
        this.remoteMethod = remoteMethod;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
