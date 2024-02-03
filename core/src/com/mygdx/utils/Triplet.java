package com.mygdx.utils;

public class Triplet<X, Y, Z>  extends Pair<X, Y>{
    public final Z z;

    public Triplet(X x, Y y, Z z){
        super(x, y);
        this.z = z;
    }
}
