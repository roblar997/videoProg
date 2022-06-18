package com.example.demo.wrapper;

import com.example.demo.entities.Tidslinje;

import java.util.Objects;

public class TimelineWrapper {

   private String command;
   private Tidslinje preTimeLine;
    private Tidslinje postTimeLine;

    public String getCommand() {
        return command;
    }

    @Override
    public String toString() {
        return "TimelineWrapper{" +
                "command='" + command + '\'' +
                ", preTimeLine=" + preTimeLine +
                ", postTimeLine=" + postTimeLine +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TimelineWrapper)) return false;
        TimelineWrapper that = (TimelineWrapper) o;
        return Objects.equals(command, that.command) && Objects.equals(preTimeLine, that.preTimeLine) && Objects.equals(postTimeLine, that.postTimeLine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(command, preTimeLine, postTimeLine);
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Tidslinje getPreTimeLine() {
        return preTimeLine;
    }

    public void setPreTimeLine(Tidslinje preTimeLine) {
        this.preTimeLine = preTimeLine;
    }

    public Tidslinje getPostTimeLine() {
        return postTimeLine;
    }

    public void setPostTimeLine(Tidslinje postTimeLine) {
        this.postTimeLine = postTimeLine;
    }
}
