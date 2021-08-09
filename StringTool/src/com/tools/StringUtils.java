package com.tools;
import java.util.Random;

public class StringUtils {
	public static void main(String[] args) {
		String[] strings=new String[]{"Jesus Christ",
				"Jesus fuck",
				"Jesus H. Christ",
				"Jesus Harold Christ",
				"Jesus wept",
				"Jesus, Mary and Joseph"};
			
		String b="<<";
		for(String str : strings){
			String d=addStr(b, str);	
			System.out.println(d);
		}
		
	}
	   public static String addStr(String splitStr, String str)  {
	        StringBuffer sb = new StringBuffer();
	        String temp = str;
	        int len = str.length();
	        Random random=new Random();	        
	        if (len > 0) {
	        	int n=random.nextInt(len);      
	            sb.append(temp.substring(0, n)).append(splitStr);
	             temp=temp.substring(n,len); 
	             sb.append(temp);
	        }
	        return sb.toString();
	    }
}