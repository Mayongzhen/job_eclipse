package com.tools;

import java.util.Arrays;

public class CarcularDemo {

	public  static int[] popsort(int[] array) {
	   
		int[] arraydemo=Arrays.copyOf(array, array.length);
		for (int i=1;i<arraydemo.length;i++){
		   boolean flag = true;
			for(int j=0;j<arraydemo.length-1;j++){
				if(arraydemo[j]>arraydemo[j+1]){
					int tmp=arraydemo[j];
					arraydemo[j]=arraydemo[j+1];	
					arraydemo[j+1]=tmp;
					flag=false;
				}
				
			}
		
			if(flag){
				break;
			}
			
		}
		return arraydemo;
		
	}
	
	
	
	
	
	
	
	public static void main(String[] args) {
		
		 int[] array={4,8,3,0,9,73,6,7};
		 
		 popsort(array);
		
	
		
		
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
