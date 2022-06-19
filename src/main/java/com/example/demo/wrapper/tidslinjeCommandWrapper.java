package com.example.demo.wrapper;

import com.example.demo.entities.Tidslinje;

import java.util.Objects;

public class tidslinjeCommandWrapper {

    enum Command {
        ADD,
        REMOVE,
        CHANGE
    }

    private Command command;
    private Tidslinje tidslinje;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof tidslinjeCommandWrapper)) return false;
        tidslinjeCommandWrapper that = (tidslinjeCommandWrapper) o;
        return command == that.command && Objects.equals(tidslinje, that.tidslinje);
    }

    @Override
    public String toString() {
        return "tidslinjeCommandWrapper{" +
                "command=" + command +
                ", tidslinje=" + tidslinje +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(command, tidslinje);
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public Tidslinje getTidslinje() {
        return tidslinje;
    }

    public void setTidslinje(Tidslinje tidslinje) {
        this.tidslinje = tidslinje;
    }
}
