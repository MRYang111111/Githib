package cn.yxd.webservice01;

import java.util.List;

/**
 * 远程调用WebService服务
 * @author zhaoqx
 *
 */
public class App {
	public static void main(String[] args) {
		MobileCodeWS ss = new MobileCodeWS();
		MobileCodeWSSoap soap = ss.getMobileCodeWSSoap();
		String ret = soap.getMobileCodeInfo("13560318380", null);
		System.out.println(ret);
//		ArrayOfString aos = soap.getDatabaseInfo();
//		List<String> list = aos.getString();
//		for (String string : list) {
//			System.out.println(string);
//		}
	}
}
