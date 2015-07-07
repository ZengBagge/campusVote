package com.vote.common;

import java.util.HashSet;
import java.util.List;

public class ArrayListUtil {

	/**
	 * list去重
	 * @param list
	 */
	@SuppressWarnings("rawtypes")
	public static void removeDuplicate(List list) {  
	      HashSet h = new HashSet(list);  
	      list.clear();  
	      list.addAll(h);  
	}  
	

}
