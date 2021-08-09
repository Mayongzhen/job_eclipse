package com.aisino.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.aisino.constant.Common;
import com.aisino.tools.Arith;
import com.aisino.tools.DateHelper;
import com.aisino.tools.HttpClientUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class SIfaClass {
	/**
	 * http://172.16.16.83:8981/api/v2/sf/rzpg/getSfAllInfo
queryParam={"companyName":"航天信息股份有限公司", "dataType":"fygg"}
ajlc  cpws zxgg ktgg sxgg
	 */
String aaaaString="广东证券股份有限公司";
String bbbString="乐视控股(北京)有限公司";
 private String[] companysStrings={"淘宝（中国）软件有限公司","淘宝中国控股有限公司","杭州同欣网络技术有限公司","北京淘宝科技有限公司","阿里巴巴（海南）数娱有限公司"
, "浙江阿里巴巴通信技术有限公司","杭州心选电子商务有限公司","阿里未来酒店管理（浙江）有限公司","南京淘宝软件有限公司","阿里巴巴（中国）网络技术有限公司" };
 private int sums=0;
 public void modifydata(String qyname,String sifatype) throws ParseException {
    String sifadatajsonString=HttpClientUtils.requestByPostForm(Common.TESTURL+Common.getrzpgSfAllInfoURL,getParams(qyname,sifatype) , 10000); 
	 System.out.println(sifadatajsonString);
	 Map map,map1;
	 int num=0;
	 map=JSONObject.parseObject(sifadatajsonString);
	 if(map!=null){
		 int errnum=(Integer) map.get("errNum");
		 if(errnum==0){
				map1= (Map) map.get("retData");	
				JSONArray jsonArray=(JSONArray) map1.get(sifatype+"List");
				for(int i=0;i<jsonArray.size();i++){
					JSONObject jsonObject=jsonArray.getJSONObject(i);
					long date=jsonObject.getLong("sortTime");
				    String result1 = new SimpleDateFormat("yyyy-MM-dd").format(new Date(date));
				  //System.out.println("1long:" + result1);
					 if(DateHelper.compareEachOther(DateHelper.parseDateFromStr(result1), DateHelper.parseDateFromStr("2016-01-01"))>=0){
						 num=num+1;
						 System.out.println(jsonObject.get("sortTimeString")+"----"+i);
					 }
					 
				}
			}else{
			 num=0;
			}
	 } 
	 sums=(int) Arith.add(sums, num);
	 System.out.println("aaaaaaaa=="+num);
	 System.out.println("sums==="+sums);
}
  @Test
  public void sifacalss() throws ParseException{
	modifydata(bbbString,"sxgg");
	 //modifydata(aaaaString, "cpws");
	  //modifydata(companysStrings[i], "ktgg");
	  //modifydata(qyname, "zxgg");
	  //modifydata(qyname, "sxgg");
	 /* for(int i=0;i<companysStrings.length;i++){
		  System.out.println("==============="+i);
		  modifydata(companysStrings[i], "sxgg");
	  }*/
  }
  @BeforeTest
  public void beforeTest() throws ParseException {
  }

  @AfterTest
  public void afterTest() {
  }
  
  
  public List<NameValuePair> getParams(String qymc,String sifaparams){
		List<NameValuePair> list=new ArrayList<>();
	    JSONObject nameParam = new JSONObject();
	    nameParam.put("companyName", qymc);
	    nameParam.put("dataType",sifaparams);
	    list.add(new BasicNameValuePair("queryParam", nameParam.toString()));
	    return list;
}
}
