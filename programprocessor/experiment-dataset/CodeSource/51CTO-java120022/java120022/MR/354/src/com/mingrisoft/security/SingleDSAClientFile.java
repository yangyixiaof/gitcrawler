/**
 * @jdk�汾:1.6
 * @����ʱ��:2010-3-20
 */
package com.mingrisoft.security;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author bwm
 * 
 */
public class SingleDSAClientFile {

	static String algorithm = "DSA";

	static String signdataFile = "fileSignData.dat";
	static String publickeyFile = "keyPublicData.dat";

	/**
	 * ������ǩ��������֤
	 * 
	 * @param data
	 * @return
	 */
	public boolean verifySign(byte[] data) {

		byte[] key = readFile(publickeyFile);
		byte[] sign = readFile(signdataFile);

		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(key);
		KeyFactory keyFactory = null;
		PublicKey publicKey = null;

		try {
			// ��ȡ��Կ��
			keyFactory = KeyFactory.getInstance(algorithm);
			publicKey = keyFactory.generatePublic(keySpec);
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			// ��֤����ǩ��
			Signature signature = Signature.getInstance(keyFactory
					.getAlgorithm());
			signature.initVerify(publicKey);
			signature.update(data);
			return signature.verify(sign);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
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

}
