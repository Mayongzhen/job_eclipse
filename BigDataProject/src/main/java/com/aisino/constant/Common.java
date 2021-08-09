package com.aisino.constant;

public class Common {
	/**
	 * 生产环境
	 * 测试环境
	 */
	public static final String BAIDU_STRING="http://www.baidu.com";
	public static final String NORMALURL="http://172.16.16.83:8000";
	public static final String TESTURL="http://172.16.16.83:8981";
	public static final String EXCELOUTPUTPATH="d:/biddata2.xls";
	public static final String excelout="d:/大数据接口.xls";
	/*
	 * 获取基本信息和信用等級接口
	 */
	public  static final String getbasicinfourl="/api/v2/getBasicAndXydj/";
	/*
	 * 获取司法信息接口地址
	 */
	public static final String getSifainfourl="/api/v2/sf/getSfAllInfo";
	/*
	 * 获取变更信息接口地址
	 */
	public static final  String getchangeinfourl="/api/v2/common/chooseInterface";
	/*
	 * 大数据模块更新接口非实时
	 */
	public static final String getupdatenfoStringovertimeurl="/api/v4/etr/moduleUpdate";
	/*
	 * 大数据更新接口实时
	 */
	public static final String GetBasicInfoIntimeurl="/api/v4/etr/updateOt";
	public static final String GetUpdateUrl="/api/v2/etr/update";		
	public static final String GetFapiaoUrl="/api/v2/fpxx/getFpxx";	
	public static final String GetTabInfoUrl="/api/v2/hbase/getTabInfo";
	public static final String GetRegisterUrl="/api/v4/etr/register";
	public static final String GetIndexinfoUrl="/api/v2/index/getIndexInfo";
	public static final String GETETRALLINFOURL="/api/v2/etr/getEtrAllInfo";
	public static final String GetFinancesolrfieldUrl="/api/v1/finance/getSolrFields";
	public static final String GETJINSHUISHUIHAOURL="/api/v1/jinshui/sh";
	public static final String QUERYDBURL_STRING="/api/v2/jdbc/queryDB";
	public static final String GETSFDHURL_STRING="/api/v2/sf/getSfdh";
	public static final String getrzpgSfAllInfoURL="/api/v2/sf/rzpg/getSfAllInfo";
	public static final String getSfAllInfoURL="/api/v2/sf/getSfAllInfo";
	public static final String getSolrFieldsURL="/api/v2/etr/getSolrFields";
}
