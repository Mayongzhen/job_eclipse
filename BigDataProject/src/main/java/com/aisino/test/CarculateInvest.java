package com.aisino.test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import com.aisino.constant.Common;
import com.aisino.tools.Arith;
import com.aisino.tools.HttpClientUtils;
import com.aisino.tools.Utills;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
public class CarculateInvest {
  private String name;
  private List<String> typeinfoStrings;
  public void modifydata(String name) {
	this.name = name;
	typeinfoStrings=new ArrayList<String>();
	typeinfoStrings.add("INVEST");
 }
  @Test
  public void carculate() {
	  String requestdata=HttpClientUtils.requestByPostForm
			   (Common.TESTURL+Common.getchangeinfourl,Utills.getParams(Utills.interfaceid002,name,typeinfoStrings),1000);	  
	 System.out.println(requestdata);
	 Map map,map1,map2;
	  map =JSONObject.parseObject(requestdata);
	 //遍历循环所得的所有key对应的value
	     map1=(Map) map.get("retData");
		//for(Object obj1:map2.keySet()){
			JSONArray jsonArray=(JSONArray) map1.get("INVEST");
			System.out.println("投资公司个数:"+jsonArray.size());
			double sum=0.0d;
			Integer noexitnum1=0;
			Map<String, Double> hashmap=new HashMap<String, Double>();
			Map<String, Integer> hashmap1=new HashMap<String, Integer>();
			for(int i=0;i<jsonArray.size();i++){
				//System.out.println(jsonArray.get(i).toString());
			    map2=jsonArray.getJSONObject(i);
			    if(map2.get("AMOUNT")!=null){
			    	//得到所有总金额
					sum=sum+Double.parseDouble(map2.get("AMOUNT").toString());
				}
			    if(map2.get("STATUS").toString().contains("注销")){
			    	noexitnum1=noexitnum1+1;
			    	
			    }else{
			    	 String companynameString=map2.get("EXT_COMPANY_NAME").toString();
						String provinceString=requstRequireProvince(companynameString);
						 //网络请求获取公司所在地区
							if(!hashmap.containsKey(provinceString)){
								if(map2.get("AMOUNT")!=null){
									hashmap.put(provinceString,Double.parseDouble(map2.get("AMOUNT").toString()));
								}else{
									hashmap.put(provinceString, 0.0d);
								}
								hashmap1.put(provinceString, 1);
							}else{
								if(map2.get("AMOUNT")!=null){
									double doubleString=Arith.add(hashmap.get(provinceString), Double.parseDouble(map2.get("AMOUNT").toString()));	
									hashmap.remove(provinceString);
									hashmap.put(provinceString, doubleString);
								}
								int num1=hashmap1.get(provinceString)+1;
								hashmap1.remove(provinceString);
								hashmap1.put(provinceString,num1 );
							}	
			    }
			}
			System.err.println("注销公司数量为"+noexitnum1);
			 // 将map.entrySet()转换成list
		      List<Map.Entry<String, Double>> list = new ArrayList<Map.Entry<String, Double>>(hashmap.entrySet());
		      // 通过比较器来实现排序
		      Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
		          @Override
		          public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
		              // 降序排序
		              return o2.getValue().compareTo(o1.getValue());
		          }
		      });
		      double sum1=0.0d;
		      int num3=0;
		      for (Map.Entry<String, Double> mapping : list) {
		    	  sum1=Arith.add(sum1, mapping.getValue());
		    	  num3=num3+hashmap1.get(mapping.getKey());
		          System.out.println("key:"+mapping.getKey() + "  value:" + mapping.getValue()+"在营、存续 公司数量"+hashmap1.get(mapping.getKey()));
		      }
		     System.out.println("存续、在营的数量为"+num3);
		     System.out.println("存续、在营投资总金额为"+sum1);
			 System.err.println("投资总金额为"+sum);	
  }
  private String requstRequireProvince(String companynameString) {
	  String princenameString ="";
	  List<String> list=new ArrayList<String>();
	  list.add("BASIC");
	  if(companynameString!=null){
		  //System.out.println(companynameString);
		  String requstdata2=HttpClientUtils.requestByPostForm
	      (Common.TESTURL+Common.getchangeinfourl,Utills.getParams(Utills.interfaceid002,companynameString,list),1000);
		 // System.out.println(requstdata2);
		  Map map,map1;
		  map =JSONObject.parseObject(requstdata2);
		 //遍历循环所得的所有key对应的value
		     map1=(Map) map.get("retData");
		  princenameString=(String) map1.get("SF");
		 // System.out.println(princenameString);
	  }
	 return princenameString;
}
  @BeforeTest
  public void beforeTest() {
	  System.out.println("=====初始化======");
	 modifydata("航天信息股份有限公司");
	 //中国航天科工集团公司
	 // modifydata("阿里巴巴（中国）网络技术有限公司");
  }
  @AfterTest
  public void afterTest() {
	  System.out.println("=====结束======");
  }
  
}
