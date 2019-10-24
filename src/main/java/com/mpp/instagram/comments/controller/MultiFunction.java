package com.mpp.instagram.comments.controller;

public interface MultiFunction {

}

@FunctionalInterface
interface FiveFunction<A , B , C , D , E , R> {
	R apply (A a , B b , C c , D d , E e);
}

@FunctionalInterface
interface FourFunction<A , B , C , D , R> {
	R apply (A a , B b , C c , D d );
}

@FunctionalInterface
interface TriFunction<A , B , C , R> {
	R apply (A a , B b , C c );
}