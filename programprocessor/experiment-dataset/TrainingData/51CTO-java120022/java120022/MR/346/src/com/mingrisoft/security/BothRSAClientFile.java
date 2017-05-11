/**
 *second 
 */
package com.mingrisoft.security;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * @author baiweiming
 * 
 */
public class BothRSAClientFile {

	private String keyAlgorithm = "RSA";
	private String singAlgorithm = "MD5withRSA";
	// ����������ļ�
	private String serverdataFile = "fileServerData.dat";
	// �ͻ��������ļ�
	private String clientdataFile = "fileClientData.dat";
	// ǩ���ļ�
	private String signdataFile = "fileSignData.dat";
	// ��Կ�ļ�
	private String publickeyFile = "keyPublicData.dat";

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

	/**
	 * У������ǩ��
	 * 
	 * @return У��ɹ�����true ʧ�ܷ���false
	 */
	public boolean verifySign() {
		byte[] data = readFile(serverdataFile);
		byte[] publicKey = readFile(publickeyFile);
		byte[] sign = readFile(signdataFile);
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKey);
		KeyFactory keyFactory = null;
		PublicKey pubKey = null;
		try {
			keyFactory = KeyFactory.getInstance(keyAlgorithm);
			pubKey = keyFactory.generatePublic(keySpec);
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			// ��֤ǩ���Ƿ�����
			Signature signature = Signature.getInstance(singAlgorithm);
			signature.initVerify(pubKey);
			signature.update(data);
			return signature.verify(sign);
		} catch (SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * �ù�Կ����
	 * 
	 * @return
	 */
	public byte[] decryptByPublicKey() {
		byte[] data = readFile(serverdataFile);
		byte[] key = readFile(publickeyFile);
		// ȡ�ù�Կ
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);
		KeyFactory keyFactory = null;
		Key publicKey = null;
		try {
			keyFactory = KeyFactory.getInstance(keyAlgorithm);
			publicKey = keyFactory.generatePublic(x509KeySpec);
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// �����ݽ���
		try {
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.DECRYPT_MODE, publicKey);
			return cipher.doFinal(data);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * �ù�Կ����
	 */
	public void encryptByPublicKey(byte[] data) {
		byte[] key = readFile(publickeyFile);
		// ȡ�ù�Կ
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);
		KeyFactory keyFactory = null;
		Key publicKey = null;
		try {
			keyFactory = KeyFactory.getInstance(keyAlgorithm);
			publicKey = keyFactory.generatePublic(x509KeySpec);
		} catch (InvalidKeySpecException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// �����ݼ���
		try {
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			writeFile(cipher.doFinal(data), clientdataFile);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchPaddingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] arg) {
		BothRSAClientFile bothRSAClientFile = new BothRSAClientFile();
		BothRSAServerFile bothRSAServerFile = new BothRSAServerFile();

		String cdata = "�������ã������ǿͻ���";
		bothRSAClientFile.encryptByPublicKey(cdata.getBytes());
		byte [] cdata1 = bothRSAServerFile.decryptByPrivateKey();
		System.out.println("Clientԭʼ���ݣ�"+cdata);
		System.out.println("Servet�������ݣ�"+new String (cdata1));
		
	}

}
