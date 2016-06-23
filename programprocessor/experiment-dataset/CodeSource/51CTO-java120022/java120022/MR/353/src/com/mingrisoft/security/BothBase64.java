/**
 * @jdk�汾:1.6
 * @����ʱ��:2010-3-20
 */
package com.mingrisoft.security;

import java.io.IOException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @author bwm
 * 
 */
public class BothBase64 {

	/**
	 * ����
	 * 
	 * @param data
	 * @return
	 */
	public static String encryptBASE64(byte[] data) {
		return (new BASE64Encoder()).encodeBuffer(data);
	}

	/**
	 * ����
	 * 
	 * @param data
	 * @return
	 * @throws IOException
	 */
	public static byte[] decryptBASE64(String data) throws IOException {
		return (new BASE64Decoder()).decodeBuffer(data);
	}

	public static void main(String[] avg) {
		String data = "���տƼ�";
		System.out.println("����ǰ��" + data);
		String data1 = BothBase64.encryptBASE64(data.getBytes());
		System.out.println("���ܺ�" + data1);
		byte[] data2 = null;
		try {
			data2 = BothBase64.decryptBASE64(data1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("���ܺ�" + new String(data2));
	}
}
