package com.infogrupo.loja.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {
	
	public static String decodeParam(String s) {
		
		try {
			
			return URLDecoder.decode(s,"UTF-8");
			
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
	public static List<Integer>decodeIntList(String params){
		
		String[]vet = params.split(",");
		List<Integer>lst = new ArrayList<Integer>();
		
		for (int i = 0; i < vet.length; i++) {
			lst.add(Integer.parseInt(vet[i]));
		}
		//Utilizando lambda 
		//return Arrays.asList(s.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
		
		return lst;
	}
}
