package com.vote.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.vote.po.CircleArticle;

public class DateUtil {
	
	public static int getDay(Date date) {
		
		Calendar c = Calendar.getInstance();  
		c.setTime(date);
		return c.get(Calendar.DATE);
		
	}
	public static int compareDate(Date  d1, Date  d2) 
	{           
        try {
               if (d1.getTime() > d2.getTime())
                  {
                        return 1;
                  }
               else if (d1.getTime() < d2.getTime()) 
                 {
                        return -1;
                 } 
               else 
               {
                        return 0;
                }
        } catch (Exception exception) {
                exception.printStackTrace();
        }
        return 0;
 }
	
	public static int compareDate(String date1, String date2,String format) 
	
	{           
        DateFormat df = new SimpleDateFormat(format);
        try {
               java.util.Date d1 = df.parse(date1);
               java.util.Date d2 = df.parse(date2);
               if (d1.getTime() > d2.getTime())
                  {  
                        return 1;
                  }
               else if (d1.getTime() < d2.getTime()) 
                 {
                      
                        return -1;
                 } 
               else 
               {
                        return 0;
                }
        } catch (Exception exception) {
                exception.printStackTrace();
        }
        return 0;
	  }
	
	public static void main(String[] args){
		commonUtil.p(getDay(new Date()));
	}
	public static void dateCha(List<CircleArticle> circleArticles) {
		for (CircleArticle circleArticle : circleArticles) {
			Date addDate =circleArticle.getAddDate();
			Date now =new Date();
			long between = (now.getTime() - addDate.getTime())/1000;
			long mon = between/(24*3600*30);
			long day = between/(24*3600);
			long hour = between%(24*3600)/3600;
			long minute = between%3600/60;
			if(mon>12){
				circleArticle.setNewTime("很久之前");
			}
			else if (mon != 0 && mon<12) {
				circleArticle.setNewTime(mon+"月前");
			}
			else if(day !=0){
				circleArticle.setNewTime(day+"天前");
			}
			else if(hour !=0){
				circleArticle.setNewTime(hour+"小时前");
			}
			else if(minute !=0){
				circleArticle.setNewTime(minute+"分钟前");
			}
		}
		
	}
}
