package com.asino.hebei.utils;
import org.apache.commons.codec.digest.DigestUtils;
import java.io.UnsupportedEncodingException;


public class MD5 {

 /**
 * 签名字符串
 * @param text 需要签名的字符串
 * @param token 密钥
 * @param input_charset 编码格式
 * @return 签名结果
 */
 public static String sign(String text, String token, String input_charset)
{
 text = text + token;
 return DigestUtils.md5Hex(getContentBytes(text, input_charset));
 }
 /**
 * @param content
 * @param charset
 * @return
 * @throws java.security.SignatureException
 * @throws UnsupportedEncodingException
 */
 private static byte[] getContentBytes(String content, String charset) {
 if (charset == null || "".equals(charset)) {
	 return content.getBytes();
	 }
	 try {
	 return content.getBytes(charset);
	 } catch (UnsupportedEncodingException e) {
	 throw new RuntimeException("MD5 签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
	
	 }
	 }
	} 



