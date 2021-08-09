package com.aisino.test;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import com.aisino.constant.Common;
import com.aisino.main.BigData;
import com.aisino.tools.ExcelOperator;
import com.aisino.tools.HttpClientUtils;
import com.aisino.tools.Utills;
import com.alibaba.fastjson.JSONObject;

public class BigDataClass {
	 private static List<String> typesinfoStrings=new ArrayList<>();
	 private static HashMap<String, String> bigdatainfoHashMap=new HashMap<String, String>();
  @Test
  public void main() {
	  System.out.println("测试执行开始=========");
	  RequireInfoReport("哈尔滨卓达菲勒酒店有限公司");
  }
  @BeforeClass
  public void beforeClass() {
	 System.out.println("测试执行前=========");
	  
	  
  }

  @AfterClass
  public void afterClass() {
	  System.out.println("测试执行结束输出excel结果=======");
	  List<String> keysStrings= new ArrayList<String>();
	  List<String> valuesString= new ArrayList<String>();
	 for (String key:bigdatainfoHashMap.keySet()){
		 keysStrings.add(key);
		 valuesString.add(bigdatainfoHashMap.get(key));
	 }
	 /**
	  * 输出内容到指定的excel文件
	  */
	 ExcelOperator.CreatExcel(Common.EXCELOUTPUTPATH,keysStrings);
	 ExcelOperator.addDataToExcel(valuesString);
	System.out.println("读取excel获取的内容============="+ExcelOperator.readExcel(Common.excelout));
	
  }
  public static void RequireInfoReport(String qymc){
	   BigData bigData=new BigData();
	   String typeinfoString="hongchongfapiao";
	   bigdatainfoHashMap.put("企业名称", qymc);
		switch (typeinfoString) {
		  case "BASIC":
			  typesinfoStrings.add("BASIC");
			   System.out.println("BASIC信息---001---"+JSONObject.parseObject(
					   HttpClientUtils.requestByPostForm
					   (Common.TESTURL+Common.getchangeinfourl, bigData.getParams(Utills.interfaceid002,qymc,typesinfoStrings),1000))
					   );
			   bigdatainfoHashMap.put("BASIC信息", JSONObject.parseObject(
					   HttpClientUtils.requestByPostForm
					   (Common.TESTURL+Common.getchangeinfourl, bigData.getParams(Utills.interfaceid002,qymc,typesinfoStrings),1000)).toJSONString());
			 break;
		  case "employments":
			  typesinfoStrings.add("employments");
			   System.out.println("employments招聘信息---001---"+JSONObject.parseObject(
					   HttpClientUtils.requestByPostForm
					   (Common.TESTURL+Common.getchangeinfourl, bigData.getParams(Utills.interfaceid002,qymc,typesinfoStrings),1000)));
			   bigdatainfoHashMap.put("employments招聘信息", JSONObject.parseObject(
					   HttpClientUtils.requestByPostForm
					   (Common.TESTURL+Common.getchangeinfourl, bigData.getParams(Utills.interfaceid002,qymc,typesinfoStrings),1000)).toJSONString());
			   break;
		  case "findTzanli":
			  typesinfoStrings.add("findTzanli");
			   System.out.println("findTzanli投资事件---001---"+JSONObject.parseObject(
					   HttpClientUtils.requestByPostForm
					   (Common.TESTURL+Common.getchangeinfourl, bigData.getParams(Utills.interfaceid081,qymc,typesinfoStrings),1000)));
			   bigdatainfoHashMap.put("findTzanli投资事件", JSONObject.parseObject(
					   HttpClientUtils.requestByPostForm
					   (Common.TESTURL+Common.getchangeinfourl, bigData.getParams(Utills.interfaceid081,qymc,typesinfoStrings),1000)).toJSONString());
			 break;
		  case "LAWINFO":
			  typesinfoStrings.add("LAWINFO");
			   System.out.println("LAWINFO法律事件---001---"+JSONObject.parseObject(
					   HttpClientUtils.requestByPostForm
					   (Common.TESTURL+Common.getchangeinfourl, bigData.getParams(Utills.interfaceid002,qymc,typesinfoStrings),1000)));  
			   bigdatainfoHashMap.put("LAWINFO法律事件",JSONObject.parseObject(
					   HttpClientUtils.requestByPostForm
					   (Common.TESTURL+Common.getchangeinfourl, bigData.getParams(Utills.interfaceid002,qymc,typesinfoStrings),1000)).toJSONString());
				break;	   
		  case "yuqing":
			  typesinfoStrings.add("yuqing");
			   System.out.println("yuqing舆情事件---001---"+JSONObject.parseObject(
					   HttpClientUtils.requestByPostForm
					   (Common.TESTURL+Common.getchangeinfourl, bigData.getParams(Utills.interfaceid002,qymc,typesinfoStrings),1000)));
			   bigdatainfoHashMap.put("yuqing舆情事件",JSONObject.parseObject(
					   HttpClientUtils.requestByPostForm
					   (Common.TESTURL+Common.getchangeinfourl, bigData.getParams(Utills.interfaceid002,qymc,typesinfoStrings),1000)).toJSONString());
			   break;
		  case "hongchongfapiao":
			  typesinfoStrings.add("hongchongfapiao");
			   System.out.println("hongchongfapia红冲发票---001---"+JSONObject.parseObject(
					   HttpClientUtils.requestByPostForm
					   (Common.TESTURL+Common.getchangeinfourl, bigData.getParams(Utills.interfaceid002,qymc,typesinfoStrings),1000))); 
			   bigdatainfoHashMap.put("hongchongfapia红冲发票",JSONObject.parseObject(
					   HttpClientUtils.requestByPostForm
					   (Common.TESTURL+Common.getchangeinfourl, bigData.getParams(Utills.interfaceid002,qymc,typesinfoStrings),1000)).toJSONString());
			   break;
		 }
		   System.out.println("=========大数据接口==================");
		   String basicinfoplusString=HttpClientUtils.requestByGet(Common.TESTURL+Common.getbasicinfourl+URLEncoder.encode(qymc), 1000);
		   System.out.println("基本信息---002----"+basicinfoplusString);
		   bigdatainfoHashMap.put("基本信息", basicinfoplusString);
		   String scoreString=HttpClientUtils.requestByPostForm(Common.TESTURL+Common.getchangeinfourl, bigData.getParams(Utills.interfaceid066,qymc,"v4"), 1000);
		   JSONObject scorejson=JSONObject.parseObject(scoreString);
		   System.out.println("五维评分---003---"+scorejson);
		   bigdatainfoHashMap.put("五维评分", scoreString);
		   String relevateString=HttpClientUtils.requestByPostForm(Common.TESTURL+Common.getchangeinfourl, bigData.getParams(Utills.interfaceid080, qymc,""), 1000);
		   System.out.println("关联交易及风险---004---"+relevateString);
		   bigdatainfoHashMap.put("关联交易及风险", relevateString);
		   String qiyebasichuaxiang=HttpClientUtils.requestByPostForm(Common.TESTURL+Common.getchangeinfourl, bigData.getParams(Utills.interfaceid046,qymc,""), 1000);
		   System.out.println("企业基本画像---005---"+qiyebasichuaxiang);
		   bigdatainfoHashMap.put("企业基本画像", qiyebasichuaxiang);
		   String hongchongfapiao=HttpClientUtils.requestByPostForm(Common.TESTURL+Common.getchangeinfourl, bigData.getParams(Utills.interfaceid055,qymc,""), 1000);
		   System.out.println("红冲发票查询---005---"+hongchongfapiao);
		   bigdatainfoHashMap.put("红冲发票查询",hongchongfapiao);
}
  public List<NameValuePair> getParams(String intercaceid,String qymc,List<String> infotypes){
		List<NameValuePair> list=new ArrayList<>();
	    JSONObject jsonParam = new JSONObject();
	    JSONObject nameParam = new JSONObject();
	    nameParam.put("qyxx", qymc);
	    nameParam.put("list",infotypes);
	    jsonParam.put("INTERFACEID", intercaceid);
	    jsonParam.put("DATA", nameParam);
	    list.add(new BasicNameValuePair("queryParam", jsonParam.toString()));
	    return list;
	}
	public  List<NameValuePair> getParams(String intercaceid,String qymc,String versionString){
		List<NameValuePair> list=new ArrayList<>();
	    JSONObject jsonParam = new JSONObject();
	    JSONObject nameParam = new JSONObject();
	    nameParam.put("qyxx", qymc);
	    if(versionString!=null&&!versionString.endsWith("")){
	    	 nameParam.put("version",versionString);
	    }
	    jsonParam.put("INTERFACEID", intercaceid);
	    jsonParam.put("DATA",nameParam);
	    list.add(new BasicNameValuePair("queryParam", jsonParam.toString()));
	    return list;
	}
}
