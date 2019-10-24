package com.mpp.instagram.comments.controller;

@FunctionalInterface
public interface FourFunction<A , B , C , D , R> {
	R apply (A a , B b , C c , D d );
}