/**
 * @jdk�汾:1.6
 * @����ʱ��:2010-3-20
 */
package com.mingrisoft.security;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author bwm
 * 
 */
public class SingleHmacServerFile {

	static String algorithm = "HmacMD5";

	static String keyFile = "keyData.dat";

	/**
	 * ������Կ
	 * 
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public void initMacKey() throws NoSuchAlgorithmException {
		KeyGenerator generator = KeyGenerator.getInstance(algorithm);
		SecretKey key = generator.generateKey();

		writeFile(key.getEncoded(), keyFile);
	}

	/**
	 * HMAC����
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 */
	public byte[] encryptHMAC(byte[] data) throws NoSuchAlgorithmException,
			InvalidKeyException {
		byte key[] = readFile(keyFile);
		SecretKey secretKey = new SecretKeySpec(key, algorithm);
		Mac mac = Mac.getInstance(secretKey.getAlgorithm());
		mac.init(secretKey);
		return mac.doFinal();

	}

	/**
	 * ������д��ָ�����ļ���
	 * 
	 * @param data
	 *            ����
	 * @param fileName
	 *            �ļ�����
	 */
	public void writeFile(byte[] data, String fileName) {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(fileName);
			fileOutputStream.write(data);
			fileOutputStream.close();
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ����fileName��ȡ�����ļ�
	 * 
	 * @param fileName
	 * @return
	 */
	public byte[] readFile(String fileName) {

		// ��ȡ
		try {
			File file = new File(fileName);
			FileInputStream fileInputStream = new FileInputStream(file);
			byte[] data = new byte[(int) file.length()];
			fileInputStream.read(data);
			fileInputStream.close();
			return data;
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public static void main(String[] avg) throws NoSuchAlgorithmException, InvalidKeyException {
		SingleHmacServerFile singleHmacServerFile = new SingleHmacServerFile();
		SingleHmacClientFile singleHmacClientFile = new SingleHmacClientFile();
		String data = "���տƼ�";
		System.out.println("����ǰ��" + data);
		String strData = null;
		String strDataClient = null;

		singleHmacServerFile.initMacKey();
		strData = BothBase64.encryptBASE64(singleHmacServerFile.encryptHMAC(data.getBytes()));
		strDataClient = BothBase64.encryptBASE64(singleHmacClientFile.encryptHMAC(data.getBytes()));
		
		System.out.println("����˼��ܺ�" + strData);
		System.out.println("�ͻ��˼��ܺ�" + strDataClient);
		if (strData.equals(strDataClient)) {
			System.out.println("��֤ͨ��");
		} else {
			System.out.println("��֤��ͨ��");
		}
	}
}
