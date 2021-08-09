package com.tools;
import java.util.regex.*;
public class RegexDemo {
	private static final String REGEX = "\\bcat\\b";
    private static final String INPUT =
                                    "cat cat cat cattie cat";

	public static void main(String[] args) {
	/*     String content = "I am noob " +
	    	        "from runoob.com.";
	    
	    	     // String pattern = ".^[1-9].*";
	    	 
	    	    //  boolean isMatch = Pattern.matches(pattern, content);
	    	    //  System.out.println("字符串中是否包含了 'runoob' 子字符串? " + isMatch);
	
	
	
	
	
	
	
	
	    	      // 按指定模式在字符串查找
	    	      String line = "This order was placed for QT3000! OK?";
	    	      String pattern = "[0-9]+";    	 
	    	      // 创建 Pattern 对象
	    	      Pattern r = Pattern.compile(pattern);
	    	 
	    	      // 现在创建 matcher 对象
	    	      Matcher m = r.matcher(line);
	    	      if (m.find( )) {
	    	         System.out.println("Found value: " + m.group(0) );
	    	         System.out.println("Found value: " + m.group(1) );
	    	         System.out.println("Found value: " + m.group(2) );
	    	         System.out.println("Found value: " + m.group(3) ); 
	    	      } else {
	    	         System.out.println("NO MATCH");
	    	      }
	    	      
	    	      */
	    	      
	    	      
	    	      
	    	      
	    	      
	    	      
	    	      Pattern p = Pattern.compile(REGEX);
	    	       Matcher m = p.matcher(INPUT); // 获取 matcher 对象
	    	       int count = 0;
	    	 
	    	       while(m.find()) {
	    	         count++;
	    	         System.out.println("Match number "+count);
	    	         System.out.println("start(): "+m.start());
	    	         System.out.println("end(): "+m.end());     
	    	      	
	
	}

	}}
