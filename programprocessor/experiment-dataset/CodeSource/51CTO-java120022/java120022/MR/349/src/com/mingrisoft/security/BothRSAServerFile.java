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
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * @author baiweiming
 * 
 */
public class BothRSAServerFile {

	private String keyAlgorithm = "RSA";
	private String singAlgorithm = "MD5withRSA";
	// ����������ļ�
	private String serverdataFile = "fileServerData.dat";
	// �ͻ��������ļ�
	private String clientdataFile = "fileClientData.dat";
	// ǩ���ļ�
	private String signdataFile = "fileSignData.dat";
	// ˽Կ�ļ�
	private String privatekeyFile = "keyPrivateData.dat";
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
	 * ������Կ��
	 */
	public void generateKeyFile() {
		KeyPairGenerator keyPairGen = null;
		try {
			keyPairGen = KeyPairGenerator.getInstance(keyAlgorithm);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		KeyPair keyPair = keyPairGen.generateKeyPair();

		// ��Կ
		PublicKey publicKey = keyPair.getPublic();
		writeFile(publicKey.getEncoded(), publickeyFile);

		// ˽Կ
		PrivateKey privateKey = keyPair.getPrivate();
		writeFile(privateKey.getEncoded(), privatekeyFile);
	}

	/**
	 * ��˽Կ����
	 * 
	 * @param data
	 * @return
	 */
	public void encryptByPrivateKey(byte[] data) {

		// ȡ��˽Կ
		byte[] key = readFile(privatekeyFile);
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);
		KeyFactory keyFactory = null;
		Key privateKey = null;
		try {
			keyFactory = KeyFactory.getInstance(keyAlgorithm);
			privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// �����ݼ���
		try {
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.ENCRYPT_MODE, privateKey);
			writeFile(cipher.doFinal(data), serverdataFile);
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ��˽Կ����Ϣ��������ǩ��
	 * 
	 * @param data
	 *            ��������
	 * @param privateKey
	 *            ˽Կ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void generateSign() {

		byte[] privateKey = readFile(privatekeyFile);
		byte[] serverData = readFile(serverdataFile);
		// ����PKCS8EncodedKeySpec����
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(privateKey);

		// KEY_ALGORITHM ָ���ļ����㷨
		KeyFactory keyFactory = null;
		PrivateKey priKey = null;
		try {
			keyFactory = KeyFactory.getInstance(keyAlgorithm);
			priKey = keyFactory.generatePrivate(pkcs8KeySpec);
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			Signature signature = Signature.getInstance(singAlgorithm);
			signature.initSign(priKey);
			signature.update(serverData);
			writeFile(signature.sign(), signdataFile);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ��˽Կ����
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public byte[] decryptByPrivateKey() {
		byte[] data = readFile(clientdataFile);
		byte[] key = readFile(privatekeyFile);
		// ȡ��˽Կ
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);
		KeyFactory keyFactory = null;
		Key privateKey = null;
		try {
			keyFactory = KeyFactory.getInstance(keyAlgorithm);
			privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			return cipher.doFinal(data);
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] arg) {
		String data = "�ͻ�����ã����Ƿ����";
		// ����˲���
		BothRSAServerFile bothRSAServerFile = new BothRSAServerFile();
		// ������Կ��
		bothRSAServerFile.generateKeyFile();
		// ��������
		bothRSAServerFile.encryptByPrivateKey(data.getBytes());
		// ����ǩ��
		bothRSAServerFile.generateSign();

		// �ͻ��˲���
		BothRSAClientFile bothRSAClientFile = new BothRSAClientFile();

		byte[]  data1 =null;
		if(bothRSAClientFile.verifySign()){
			data1= bothRSAClientFile.decryptByPublicKey();
		}
		System.out.println("Servetԭʼ���ݣ�"+data);
		System.out.println("Client�������ݣ�"+new String (data1));
		
	}

}
