package com.vote.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class commonUtil {
	

	public static boolean contain(String[] arr, String targetValue) {
	    for (String s : arr) {
	        if (s.equals(targetValue)) {
	            return true;
	        }
	    }
	    return false;
	}
	/**
	 * 打印函数
	 * @param object
	 */
	public static void p(Object object){
		System.out.println(object);
	}
	
	public static void removeDuplicate(List list) {  
	      HashSet h = new HashSet(list);  
	      list.clear();  
	      list.addAll(h);  
	}  

	/**
	 * 去除字符串前count位
	 * @param origin
	 * @param count
	 * @return
	 */
	public static String truncateHeadString(String origin, int count) {
        if (origin == null || origin.length() < count) {
            return null;
        }
         
        char[] arr = origin.toCharArray();
        char[] ret = new char[arr.length - count];
        for (int i = 0; i < ret.length; i ++) {
            ret[i] = arr[i + count];
        }
         
        return String.copyValueOf(ret);
    }
	
	/**
	 * 随机生成字符串
	 * @param length
	 * @return
	 */
	public static String getRandomString(int length){  
        Random random = new Random();  
          
        StringBuffer sb = new StringBuffer();  
          
        for(int i = 0; i < length; ++i){  
            int number = random.nextInt(3);  
            long result = 0;  
              
            switch(number){  
            case 0:  
                result = Math.round(Math.random() * 25 + 65);  
                sb.append(String.valueOf((char)result));  
                break;  
            case 1:  
                result = Math.round(Math.random() * 25 + 97);  
                sb.append(String.valueOf((char)result));  
                break;  
            case 2:  
                sb.append(String.valueOf(new Random().nextInt(10)));  
                break;  
            }  
        }  
        return sb.toString();     
    }  
	
	
	/***
     * 去掉字符串前后的空间，中间的空格保留
     * @param str
     * @return
     */
    public static String trimInnerSpaceStr(String str){
         str = str.trim(); //Returns a string whose value is this string, with any leading(前导) and trailing(尾随) whitespace removed
        while(str.startsWith(" ")){
        str = str.substring(1,str.length()).trim();
        }
        while(str.endsWith(" ")){
        str = str.substring(0,str.length()-1).trim();
        }
        return str;
    }
    
	/**
	 * md5加密
	 * @param str
	 * @return
	 */
	public static  String getMD5Str(String str) {  
        MessageDigest messageDigest = null;  
  
        try {  
            messageDigest = MessageDigest.getInstance("MD5");    
            messageDigest.reset();  
            messageDigest.update(str.getBytes("UTF-8"));  
        } catch (NoSuchAlgorithmException e) {  
            System.out.println("NoSuchAlgorithmException caught!");  
            System.exit(-1);  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
  
        byte[] byteArray = messageDigest.digest();  
  
        StringBuffer md5StrBuff = new StringBuffer();  
  
        for (int i = 0; i < byteArray.length; i++) {              
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)  
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));  
            else  
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));  
        }  
  
        return md5StrBuff.toString();  
    }
	/**
	 * 数组查有无某元素
	 * @param array
	 * @param v
	 * @return
	 */
	public static<T> boolean contains(final T[] array,final T v){
		 for ( final T e : array )
		        if ( e == v || (v != null && v.equals( e )) )
		            return true;

		    return false;
	}
}
