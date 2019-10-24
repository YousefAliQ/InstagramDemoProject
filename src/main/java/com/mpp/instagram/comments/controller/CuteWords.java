package com.mpp.instagram.comments.controller;

import java.util.HashMap;

public class CuteWords {
	
	static String words =
			"baby\n" + 
			"cute\n" + 
			"heart\n" + 
			"kisses\n" + 
			"love\n" + 
			"lovely\n" + 
			"pretty\n" + 
			"sweet\n" + 
			"sweetie\n" + 
			"sweety\n";
	
	public static HashMap<String , Integer> getInstance(){		
		return transformString(words.split("\\r?\\n"));
	}
	
	public static HashMap<String , Integer> transformString(String[] text) {
		return FunctionImp.wordsToMap.apply(text);
	}
}
