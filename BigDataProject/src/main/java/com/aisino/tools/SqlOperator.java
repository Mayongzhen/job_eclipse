package com.aisino.tools;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.aisino.dao.IRelevant;





public class SqlOperator {

	private static SqlSessionFactory sqlSessionFactory;
	private static Reader reader;
	private static void construtor(){
		try {
			

		} catch (Exception e) {
		}
	}
		
	
	public static SqlSessionFactory getInstance(){
		if(sqlSessionFactory==null){
			SqlOperator.construtor();
		}
		
		return sqlSessionFactory;
	}
	
}
