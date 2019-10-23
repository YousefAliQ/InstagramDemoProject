package com.mpp.instagram.comments.controller;

import java.util.Collections;
//import java.util.ArrayList;
//import java.util.Arrays;
import java.util.HashMap;
//import java.util.List;
import java.util.function.Function;
//import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CuteWords {
	
	String words = "adorable\n" + 
			"angel\n" + 
			"baby\n" + 
			"bloop\n" + 
			"brumby\n" + 
			"bubbles\n" + 
			"bug\n" + 
			"bumberell\n" + 
			"bumble\n" + 
			"bungle\n" + 
			"burple\n" + 
			"buttercup\n" + 
			"butterfly\n" + 
			"butterscotch\n" + 
			"buttons\n" + 
			"canoodle\n" + 
			"charming\n" + 
			"chiffchaff\n" + 
			"chomp\n" + 
			"cuddly\n" + 
			"cupcake\n" + 
			"cute\n" + 
			"cutie pie\n" + 
			"cutsie\n" + 
			"delightful\n" + 
			"dimples\n" + 
			"dobby\n" + 
			"doink\n" + 
			"doll\n" + 
			"dovey\n" + 
			"fairy\n" + 
			"flarf\n" + 
			"fluffy\n" + 
			"foozle\n" + 
			"forever\n" + 
			"froglet\n" + 
			"frumpous\n" + 
			"fun\n" + 
			"funny\n" + 
			"gem\n" + 
			"giggles\n" + 
			"glittery\n" + 
			"glume\n" + 
			"heart\n" + 
			"hugsy\n" + 
			"hunnybunch\n" + 
			"jewel\n" + 
			"jiggly\n" + 
			"joy\n" + 
			"kinky\n" + 
			"kisses\n" + 
			"kissy\n" + 
			"kithly\n" + 
			"kitty\n" + 
			"ladybug\n" + 
			"love\n" + 
			"lovey\n" + 
			"munchkin\n" + 
			"paddywack\n" + 
			"pebbles\n" + 
			"piggy\n" + 
			"pipsqueek\n" + 
			"plonk\n" + 
			"pooch\n" + 
			"poplin\n" + 
			"precious\n" + 
			"pretty\n" + 
			"prince\n" + 
			"princess\n" + 
			"prinky\n" + 
			"pumpkin\n" + 
			"rainbow\n" + 
			"rhubarb\n" + 
			"schnoogle\n" + 
			"slurp\n" + 
			"smoochies\n" + 
			"smush\n" + 
			"snaffle\n" + 
			"snifty\n" + 
			"snuggle\n" + 
			"snuggly\n" + 
			"snurf\n" + 
			"snurfle\n" + 
			"spooky\n" + 
			"sprinkles\n" + 
			"squeeze\n" + 
			"sugar\n" + 
			"sugary\n" + 
			"sunshine\n" + 
			"sweet\n" + 
			"sweetie\n" + 
			"sweety\n" + 
			"thimble\n" + 
			"toot\n" + 
			"tootsie\n" + 
			"treasure\n" + 
			"whiffle\n" + 
			"whiskers\n" + 
			"wiggly\n" + 
			"wispy\n" + 
			"wobbly";
	
	//HashMap<Integer , String> list = new HashMap<>();
	
	private CuteWords() {}
	
	static Function<String[], HashMap<String , Integer>> wordsToMap = 
		/*(entries) -> IntStream.range(0, entries.length).boxed()
			.map(i -> 
				new HashMap<Integer , String> () {{
					put(i , entries[i]);
				}}.entrySet().stream().findFirst().get())
			.collect(Collectors.toMap(HashMap::getKey, HashMap::getValue));*/
		
		(entries) -> IntStream.range(0, entries.length).boxed()
			.sorted(Collections.reverseOrder())
			.collect(HashMap<String , Integer>::new , 
					(map , i) -> map.put(entries[i] , i ), 
					(map , i) -> {});
	
	public HashMap<String , Integer> getInstance(){
		
		return wordsToMap.apply(words.split("\\r?\\n"));
		
		/*Arrays.asList(words.split("\\r?\\n")).stream()
			.collect(Collectors.toMap(, Function.identity()));*/
	}
}
