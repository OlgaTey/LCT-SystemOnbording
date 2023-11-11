package org.lesson.model;

public class Output {
    private int time;
    private int[] values;

    public Output() {
    }

    public Output(int time, int[] values) {
        this.time = time;
        this.values = values;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int[] getValues() {
        return values;
    }

    public void setValues(int[] values) {
        this.values = values;
    }
}
