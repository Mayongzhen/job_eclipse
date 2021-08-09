package main;

import java.util.UUID;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import com.asino.hebei.utils.MD5;

@Test
public class AsinoApi {
	
	
	
/*	• 请求协议：http
	• 请求方法：post
	• 数据编码格式：UTF-8
	• 请求前缀：http://api.i-xinnuo.com
	• 请求参数：包括协议参数和业务参数
	o 协议参数：通过 http 的 header 传输，访问任何服务都需要提供的参数
	▪ username：由用户申请，平台进行分配
	▪ businessNum：请求方流水号，建议每次请求唯一，其作为待签名的
	数据的一部分，也方便接口调试或者使用中进行问题定位
	▪ sign：签名数据，根据一定的规则以及 token 计算所得，具体方法，
	可参见代码
	o 业务参数：通过 json 格式传输，具体参数根据具体请求的服务有所不同
	o 密钥：token，此参数不在网络上传输，由平台分配，接口调用方保存，参
	与计算签名（sign）*/
	
	 public static String username = "申请的账号";
	 public static String token = "申请的 token";
	
	 public void testService() throws Exception {
	 String url = "http://api.i-xinnuo.com";
	 String businessNum = UUID.randomUUID().toString();
	 String paramJsonStr = "json 格式的业务数据 ";
	 System.out.println(httpPostWithJSON(url, username, businessNum,
	paramJsonStr, token));
	 }
	 public static String httpPostWithJSON(String url, String username, String
	 businessNum, String paramJsonStr, String token) throws Exception {
	 HttpPost httpPost = new HttpPost(url);
	 CloseableHttpClient client = HttpClients.createDefault();
	 String respContent = null;
	 // json 方式
	 StringEntity entity = new StringEntity(paramJsonStr,"utf-8");
	 entity.setContentEncoding("UTF-8");
	 entity.setContentType("application/json");
	 httpPost.setEntity(entity);
	 httpPost.setHeader("username", username);
	 httpPost.setHeader("businessNUm", businessNum);
	 String text = username + businessNum.toUpperCase() + paramJsonStr;
	 String sign = MD5.sign(text, token, "UTF-8");
	 httpPost.setHeader("sign", sign);
	 HttpResponse resp = client.execute(httpPost);
	 if(resp.getStatusLine().getStatusCode() == 200) {
	 HttpEntity he = resp.getEntity();
	 respContent = EntityUtils.toString(he,"UTF-8");
	 }
	 	return respContent;
	 }
	
	public static void main(String[] args) {
		
		AsinoApi asinoApi=new AsinoApi();
		try {
		  asinoApi.testService();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	


}
