package com.aisino.test;

import java.io.IOException;
import java.io.Reader;
import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.aisino.dao.IRelevant;
import com.aisino.model.RelevantData;
import com.aisino.tools.HttpClientUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class RelevantReport {
	private SqlSessionFactory sqlSessionFactory;
	private  Reader reader;
	private String urlString="http://172.16.16.37:9999/glffx/getGlfjbxx?1566887219905&qymc=";
	  private String name;
	  private List<String> glist;
	  public void modifydata(String name) {
		this.name = name;
		glist=new ArrayList<String>();
	  }
	  
  @Test
  public void Relevantabc() {
	 String string= HttpClientUtils.requestByGet(urlString+URLEncoder.encode(name), 10000);
	 System.out.println(string);
	 Map map=JSONObject.parseObject(string);
	 Map map1=(Map) map.get("detail");
	 JSONArray gdzgs =(JSONArray) map1.get("gdcg");
	 if(gdzgs!=null){
		for(int i=0;i<gdzgs.size();i++){
			JSONObject jsonObject=(JSONObject) gdzgs.get(i);
			String nameString=(String) jsonObject.get("qymc");
			if(nameString!=null){
				glist.add(nameString);
			}
		} 
	 }
	 JSONArray yijigd=(JSONArray) map1.get("gdlist");
	 if(yijigd!=null){
			for(int i=0;i<yijigd.size();i++){
				JSONObject jsonObject=(JSONObject) yijigd.get(i);
				String nameString=(String) jsonObject.get("gdmc");
				if(nameString!=null){
					glist.add(nameString);
				}
			} 
		 }
	 JSONArray ggjr=(JSONArray) map1.get("ggjr");
	 JSONArray ggljArray=(JSONArray) map1.get("gqlj");
	 if(ggljArray!=null){
			for(int i=0;i<ggljArray.size();i++){
				JSONObject jsonObject=(JSONObject) ggljArray.get(i);
				String nameString=(String) jsonObject.get("qymc");
				if(nameString!=null){
					glist.add(nameString);
				}
			} 
		 }
	     JSONArray qtArray=(JSONArray) map1.get("qt");
		 SqlSession sqlSession=sqlSessionFactory.openSession();
		  IRelevant iRelevant=sqlSession.getMapper(IRelevant.class);
	     System.out.println("======="+glist.toString()+"========="+glist.size());	
	     RelevantData relevantdata=new RelevantData();
	     relevantdata.setId(1);
	     relevantdata.setReleventdataString(glist.toString());
	     iRelevant.insertRelevantdata(relevantdata);
	     sqlSession.commit();
	     sqlSession.close();
  }
  @BeforeTest
  public void beforeTest() {
	  try {
		reader=Resources.getResourceAsReader("resources/mybatis-config.xml");
		sqlSessionFactory=new SqlSessionFactoryBuilder().build(reader);
		sqlSessionFactory.getConfiguration().addMapper(IRelevant.class);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	  modifydata("爱信诺基金管理机构");
  }
  @AfterTest
  public void afterTest() {
	  
  }

}
