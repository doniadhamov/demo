package com.example.demo.dto.datatables;

public class oPaginate {

    private String sFirst;
    private String sLast;
    private String sNext;
    private String sPrevious;

    public String getsFirst() {
        return sFirst;
    }

    public void setsFirst(String sFirst) {
        this.sFirst = sFirst;
    }

    public String getsLast() {
        return sLast;
    }

    public void setsLast(String sLast) {
        this.sLast = sLast;
    }

    public String getsNext() {
        return sNext;
    }

    public void setsNext(String sNext) {
        this.sNext = sNext;
    }

    public String getsPrevious() {
        return sPrevious;
    }

    public void setsPrevious(String sPrevious) {
        this.sPrevious = sPrevious;
    }
}
