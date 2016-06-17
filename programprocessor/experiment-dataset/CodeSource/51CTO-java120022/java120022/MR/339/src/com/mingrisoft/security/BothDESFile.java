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
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * @author bwm
 * 
 */
public class BothDESFile {

	String algorithm = "DES";
	// key������ļ�����
	String keyFile = "keyData.dat";
	// ���ݱ�����ļ�����
	String dataFile = "fileData.dat";

	public BothDESFile() {
		try {
			initKey();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ������Կ����,���浽�ļ���
	 * 
	 * @throws NoSuchAlgorithmException
	 */
	private void initKey() throws NoSuchAlgorithmException {
		// ����һ�������Դ
		SecureRandom secureRandom = new SecureRandom();
		// ΪDES�㷨����һ��KeyGenerator
		KeyGenerator generator = KeyGenerator.getInstance(algorithm);
		generator.init(secureRandom);
		SecretKey key = generator.generateKey();
		//������Կ����,���浽�ļ���
		writeFile(key.getEncoded(), keyFile);

	}

	/**
	 * ת����Կ��Key���м��ܽ���
	 * 
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	private Key toKey() throws InvalidKeyException, NoSuchAlgorithmException,
			InvalidKeySpecException {
		byte[] key = readFile(keyFile);
		DESKeySpec keySpec = new DESKeySpec(key);
		SecretKeyFactory factory = SecretKeyFactory.getInstance(algorithm);
		SecretKey secretKey = factory.generateSecret(keySpec);
		return secretKey;
	}

	/**
	 * ���ܣ��Ѽ������ݱ������ļ���
	 * 
	 * @param data
	 * @param key
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws NoSuchPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public void encrypt(byte[] data) throws InvalidKeyException,
			NoSuchAlgorithmException, InvalidKeySpecException,
			NoSuchPaddingException, IllegalBlockSizeException,
			BadPaddingException {
		Key key = toKey();
		// ʹ��Cipherʵ����ɼ��ܲ���
		Cipher cipher = Cipher.getInstance(algorithm);
		// ʹ����Կ��ʼ��Cipher
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] f = cipher.doFinal(data);
		writeFile(f, dataFile);
	}

	/**
	 * ���ܰ����ݴ��ļ���ȡ�����ڽ���
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws NoSuchPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public String decrypt() throws InvalidKeyException,
			NoSuchAlgorithmException, InvalidKeySpecException,
			NoSuchPaddingException, IllegalBlockSizeException,
			BadPaddingException {
		Key key = toKey();
		Cipher cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] f = readFile(dataFile);
		return new String(cipher.doFinal(f));
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

	public static void main(String[] avg) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		BothDESFile bothDESFile = new BothDESFile();
		String data = "���տƼ�";

		// ���ݼ���
		System.out.println("����ǰ��" + data);
		bothDESFile.encrypt(data.getBytes());

		// ���ݽ���
		String b = bothDESFile.decrypt();
		System.out.println("���ܺ�" + b);

	}
}
