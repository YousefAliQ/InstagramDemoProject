package com.mpp.instagram.FunctionalCode;

import java.util.List;

@FunctionalInterface
public interface TriFunction<A,B,C,R> {
    public R find(A a,B b, C c);
}
