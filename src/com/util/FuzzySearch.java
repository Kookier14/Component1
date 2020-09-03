package com.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FuzzySearch {
	public static boolean search(String name,String search){
		   Pattern pattern = Pattern.compile(name);
		   Matcher matcher = pattern.matcher(search);
		      if(matcher.find()){
		         return true;
		      }
		  
		   return false;
		}

}
