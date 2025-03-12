package org.example.data_structure;


public class Trunk {
    public Trunk prev;
    public String str;

    public Trunk(Trunk prev, String str) {
        this.prev = prev;
        this.str = str;
    }
}