package com.mpp.instagram.comments.controller;

@FunctionalInterface
public interface FiveFunction<A , B , C , D , E , R> {
	R apply (A a , B b , C c , D d , E e);
}
