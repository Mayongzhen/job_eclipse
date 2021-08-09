package com.aisino.tools;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import com.alibaba.fastjson.JSONObject;
public class Utills {
	
public static final String interfaceid001="IXNZX_INTERFACE_OUT_00";
public static final  String interfaceid002="IXNZX_INTERFACE_OUT_02";	//工商税务信息
public static String interfaceid006="IXNZX_INTERFACE_OUT_06";	//企业五维评分
public static String interfaceid007="IXNZX_INTERFACE_OUT_07";
public static String interfaceid008="IXNZX_INTERFACE_OUT_08";	//企业发展指数接口
public static String interfaceid009="IXNZX_INTERFACE_OUT_09";
public static String interfaceid010="IXNZX_INTERFACE_OUT_10";
public static String interfaceid011="IXNZX_INTERFACE_OUT_11";  //	查询指定企业的第一张或最后一张发票
public static String interfaceid012="IXNZX_INTERFACE_OUT_12";//	给定日期范围内发票列表 （发票明细表）
public static String interfaceid013="IXNZX_INTERFACE_OUT_13";//给定日期范围内发票列表 （按月统计表）
public static String interfaceid014="IXNZX_INTERFACE_OUT_14";
public static String interfaceid015="IXNZX_INTERFACE_OUT_15";	//企业报告完成标志接口
public static String interfaceid016="IXNZX_INTERFACE_OUT_16";
public static String interfaceid017="IXNZX_INTERFACE_OUT_17";
public static String interfaceid018="IXNZX_INTERFACE_OUT_18";	
public static String interfaceid019="IXNZX_INTERFACE_OUT_19";
public static String interfaceid020="IXNZX_INTERFACE_OUT_20";	
public static String interfaceid021="IXNZX_INTERFACE_OUT_21";
public static String interfaceid022="IXNZX_INTERFACE_OUT_22";	//企业签到信息统计接口
public static String interfaceid023="IXNZX_INTERFACE_OUT_23";	//企业黑灰名单接口
public static String interfaceid024="IXNZX_INTERFACE_OUT_24";	//发票跑批完成标志
public static String interfaceid025="IXNZX_INTERFACE_OUT_25";  //企业授信额度接口
public static String interfaceid026="IXNZX_INTERFACE_OUT_26";	
public static String interfaceid027="IXNZX_INTERFACE_OUT_27";//基尼系数接口	
public static String interfaceid028="IXNZX_INTERFACE_OUT_28";
public static String interfaceid029="IXNZX_INTERFACE_OUT_29";
public static String interfaceid030="IXNZX_INTERFACE_OUT_30";
public static String interfaceid031="IXNZX_INTERFACE_OUT_31";	
public static String interfaceid032="IXNZX_INTERFACE_OUT_32";	
public static String interfaceid033="IXNZX_INTERFACE_OUT_33";//企业联盟总数
public static String interfaceid034="IXNZX_INTERFACE_OUT_34";  
public static String interfaceid035="IXNZX_INTERFACE_OUT_35";	
public static String interfaceid036="IXNZX_INTERFACE_OUT_36";	
public static String interfaceid037="IXNZX_INTERFACE_OUT_37";
public static String interfaceid040="IXNZX_INTERFACE_OUT_40";//主营商品分析
public static String interfaceid041="IXNZX_INTERFACE_OUT_41";//商品分类分析
public static String interfaceid043="IXNZX_INTERFACE_OUT_43";//企业上下游汇总	
public static String interfaceid046="IXNZX_INTERFACE_OUT_46";//企业画像	
public static String interfaceid047="IXNZX_INTERFACE_OUT_47";//新五维评分及信用评分
public static String interfaceid055="IXNZX_INTERFACE_OUT_55";//	红冲发票查询
public static String interfaceid066="IXNZX_INTERFACE_OUT_66";//五维评分
public static String interfaceid080="IXNZX_INTERFACE_OUT_80";
public static String interfaceid081="IXNZX_INTERFACE_OUT_81";//关联交易及风险	
/* @intercaceid 接口id
	 * @qymc 企业名称
	 * @type 信息类型
	 */
	public static List<NameValuePair> getParams(String intercaceid,String qymc,List<String> infotypes){
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
}
