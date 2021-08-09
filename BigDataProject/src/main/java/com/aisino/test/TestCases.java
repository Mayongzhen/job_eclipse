package com.aisino.test;


import org.testng.annotations.Test;


public class TestCases {
	private int[] nums={1,2,3};
	private String[] strings={"abc","mmmm","ccc"};

	
	public void Setup(){
		
		for(int i=0;i<nums.length;i++){
			for(int j=0;j<0;j++){
					System.out.println(nums[i]+"==="+strings[i]);
					break;	
			}
		}
	}
	
	public void excute(){
		
	}
	
	
}
