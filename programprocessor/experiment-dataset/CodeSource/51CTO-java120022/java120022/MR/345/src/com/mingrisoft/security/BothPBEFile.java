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
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

/**
 * @author bwm
 * 
 */
public class BothPBEFile {

	String algorithm = "PBEWithMD5AndDES";

	String saltFile = "saltData.dat";
	String dataFile = "fileData.dat";

	/**
	 * ��ȡ��ֵ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void initSalt() {
		byte[] salt = new byte[8];
		Random random = new Random();
		//���������
		random.nextBytes(salt);
		//������ֵ
		writeFile(salt, saltFile);
	}

	/**
	 * ��ȡԿ��
	 * 
	 * @param password
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	private Key toKey(String password) throws NoSuchAlgorithmException,
			InvalidKeySpecException {
		PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(algorithm);
		SecretKey secretKey = keyFactory.generateSecret(keySpec);
		return secretKey;
	}

	/**
	 * ����
	 * 
	 * @param data
	 * @param password
	 * @throws InvalidKeySpecException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidAlgorithmParameterException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
	public void encrypt(byte[] data, String password)
			throws NoSuchAlgorithmException, InvalidKeySpecException,
			NoSuchPaddingException, InvalidKeyException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException,
			BadPaddingException {
		Key key = toKey(password);
		byte[] salt = readFile(saltFile);
		PBEParameterSpec paramSpec = new PBEParameterSpec(salt, 100);
		Cipher cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
		writeFile(cipher.doFinal(data), dataFile);

	}

	/**
	 * ����
	 * 
	 * @param data
	 * @param password
	 * @return
	 * @throws InvalidKeySpecException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidAlgorithmParameterException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
	public String decrypt(String password) throws NoSuchAlgorithmException,
			InvalidKeySpecException, NoSuchPaddingException,
			InvalidKeyException, InvalidAlgorithmParameterException,
			IllegalBlockSizeException, BadPaddingException {
		Key key = toKey(password);
		byte[] salt = readFile(saltFile);
		byte[] data = readFile(dataFile);
		PBEParameterSpec paramSpec = new PBEParameterSpec(salt, 100);
		Cipher cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
		return new String(cipher.doFinal(data));
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

	public static void main(String[] avg) {
		BothPBEFile bothPBEFile = new BothPBEFile();
		String data = "���տƼ�";
		String password = "123456";
		System.out.println("����ǰ��" + data);
		try {
			bothPBEFile.initSalt();
			bothPBEFile.encrypt(data.getBytes(), password);
			String tdata = bothPBEFile.decrypt(password);
			System.out.println("���ܺ�" + tdata);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
