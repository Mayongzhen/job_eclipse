package emojDemo;

import java.util.HashMap;

public class TestCase {

	private static String url="http://172.16.16.58/talkplatform_evaluate_consumer/User/addUserTeacherGrading";
	private static HashMap<String , String > params;
	public static void main(String[] args) {
	    params = new HashMap();
		params.put("selectOne", "no");
		params.put("selectTwo", "no");
		params.put("selectThree", "1");
		params.put("answerSix", "2");
		params.put("answerEight", "3");
		params.put("answerTen", "4");
		params.put("content", "\uD83E\uDD92");
		params.put("userId", "130851841");
		params.put("teacherId", "674428");
		params.put("appointId", "4093182"); // appointId长度10，7-9位？？
		params.put("tagId", "84461140552314581378");
		params.put("occup", "100");
		params.put("appType", "web");
		params.put("courseType", "1");
		params.put("startTime", "2015-12-03 12:00:00");
		params.put("ip", "178.90.9.0");
	 System.out.println(HttpRequestUtil.doPost(url, params, "utf-8"));
	}
}
