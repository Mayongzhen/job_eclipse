package com.aisino.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.aisino.constant.Common;
import com.aisino.tools.DateHelper;
import com.aisino.tools.HttpClientUtils;
import com.aisino.tools.Utills;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class ForbiddenTrade {
	private List<String> typesinfostrings=new ArrayList<String>();
	private String qymcString="";
	private List<String> forbiddentradesString=new ArrayList<String>();
    private String[] trades={"民间借贷","民间融资","融资租赁","配资业务","小额理财","小额借贷","P2P/P2B","众筹","保理","担保","房地产开发","交易平台"};
    private String[] companysStrings={"淘宝（中国）软件有限公司","淘宝中国控股有限公司","杭州同欣网络技术有限公司","北京淘宝科技有限公司","阿里巴巴（海南）数娱有限公司"
    		, "浙江阿里巴巴通信技术有限公司","杭州心选电子商务有限公司","阿里未来酒店管理（浙江）有限公司","南京淘宝软件有限公司","阿里巴巴（中国）网络技术有限公司"};
 
    
    private String[] companysStrings1={"乐视控股(北京)有限公司","贾跃亭","乐视品牌文化传播（北京）有限公司","北京网酒网电子商务股份有限公司","乐禧文化（北京）有限公司"
    		,"乐视移动终端投资（北京）有限公司","上海达汶商贸有限公司","深圳市乐视鑫根垂直整合生态基金管理有限公司",
    		"乐乐创新智能科技（北京）有限公司","乐旭（宁波）投资管理合伙企业（有限合伙）","乐视移动互联信息技术（北京）有限公司","乐意互联智能科技（北京）有限公司","北京财富时代置业有限公司",
    "北京弘美文化传媒有限公司","乐视网文化发展（北京）有限公司","乐视生活（北京）互联网科技有限公司","北京宏城鑫泰置业有限公司","乐视云计算有限公司","宁波梅山保税港区乐卓尔投资管理有限公司","	北京百鼎新世纪商业管理有限公司",
    "乐视创景科技（北京）有限公司","上海乐往互联网科技发展有限公司","乐视嘉业（天津）投资有限公司","乐驰（宁波）投资管理合伙企业（有限合伙）","大圣科技股份有限公司","旭日天晟投资管理（北京）有限公司","乐视海韵文化传媒（北京）有限公司"
    ,"乐视品牌营销策划（北京）有限公司","汇智辰星信用服务有限公司","乐享视界信息技术（北京）有限公司","乐咖互娱信息技术（北京）有限公司","北京优宝科技有限公司"
    }; 
    
    @Test
  public void f(){
	  for (int i = 0; i < companysStrings1.length; i++) {
		    forbidden(companysStrings1[i].trim());
		 }
  }
  public void forbidden(String qymc) {
	  Map map,map1;
	  map=JSONObject.parseObject(
			   HttpClientUtils.requestByPostForm
			   (Common.TESTURL+Common.getchangeinfourl, Utills.getParams(Utills.interfaceid002,qymc,typesinfostrings),5000));
	  if(map!=null){
			 int errnum=(Integer) map.get("errNum");
			 if(errnum==0){
					map1= (Map) map.get("retData");	
				String jingyingtrade=map1.get("OPSCOPE").toString();
				for(int i=0;i<trades.length;i++){
			  		  if(jingyingtrade.contains(trades[i])){
			  			  forbiddentradesString.add(trades[i]);
			  		  }   
			  	   }
				if(forbiddentradesString.size()>0){
				
				System.err.println("涉及禁止行业的===="+qymc+"==行业个数:"+forbiddentradesString.size()+"==禁止行业类型:"+forbiddentradesString.toString());
				   forbiddentradesString.clear();
				}
			  	}
	  }
  }
		  
  @BeforeTest
  public void beforeTest() {
	  typesinfostrings.add("BASIC");
  }

  @AfterTest
  public void afterTest() {
	  
	  
  }

}
