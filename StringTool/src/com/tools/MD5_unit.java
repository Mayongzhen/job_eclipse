package com.tools;

import javax.swing.tree.TreeNode;

public class MD5_unit {
	public static void main(String[] args) {
		/*System.out.println(Math.abs(hash1)%100);
		System.out.println(Math.abs(hash2)%100);
		System.out.println(Math.abs(hash3)%100);
		System.out.println(Math.abs(hash4)%100);
		System.out.println(Math.abs(hash5)%100);*/
		
		
		int hash1 = "stu_rewrite_unit1587378173".hashCode();
		int hash2 = "stu_preheat_unit1587375069".hashCode();
		int hash3 = "stu_rewrite_unit1587375060".hashCode();
		int hash4 = "stu_preheat_unit122216621".hashCode();
		int hash5 = "gkid_course1587381299".hashCode();
		int hash6 = "gkid_course1587381299".hashCode();
			
        String keyword="level3";
		int i=0;
		for (int stu_id=1000;stu_id<=1100;stu_id++){
			// String string ="359237550174371840";
			// String hashvalue1="359237550174371840"+stu_id;
			// String mdString= Md5Utils.string2Md5(hashvalue1);
			String hashvalue= "361181308491137024"+stu_id;
			 int hashcodeabs=Math.abs(hashvalue.hashCode())%100;			
			if(hashcodeabs>=0&&hashcodeabs<40){
				System.out.println("满足"+keyword+"的学生id--"+stu_id+"--hashcodevalue"+hashcodeabs);
				i++;			
			}else {			
				System.out.println("不满足"+keyword+"的学生id"+stu_id+"--hashcodevalue"+hashcodeabs);
			}
					
		}
		System.out.println("满足放量的数量"+i);
		
		
		//System.out.println("md5===="+Math.abs(Md5Utils.string2Md5("21062").hashCode()));
		
	}


}
