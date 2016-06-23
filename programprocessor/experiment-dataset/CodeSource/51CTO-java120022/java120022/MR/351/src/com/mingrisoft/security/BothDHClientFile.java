/**
 *second 
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
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyAgreement;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;

/**
 * @author baiweiming
 * 
 */
public class BothDHClientFile {

	private String keyAlgorithm = "DH";
	private String secretAlgorithm = "DES";
	// ����������ļ�
	private String serverdataFile = "fileServerData.dat";
	// �ͻ��������ļ�
	private String clientdataFile = "fileClientData.dat";
	// ����˹�Կ�ļ�
	private String publicServerkeyFile = "keyServerPublicData.dat";
	// �ͻ��˹�Կ�ļ�
	private String publicClientkeyFile = "keyClientPublicData.dat";
	// �ͻ���˽Կ�ļ�
	private String privateClientkeyFile = "keyClientPrivateData.dat";

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
	 * ���ɿͻ�����Կ��
	 */
	public void generateClientKeyFile() {
		KeyPairGenerator keyPairGen = null;

		try {
			byte[] publicServerkey = readFile(publicServerkeyFile);
			X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(
					publicServerkey);
			KeyFactory keyFactory = KeyFactory.getInstance(keyAlgorithm);
			PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
			DHParameterSpec dhParameterSpec = ((DHPublicKey) publicKey)
					.getParams();

			keyPairGen = KeyPairGenerator
					.getInstance(keyFactory.getAlgorithm());
			keyPairGen.initialize(dhParameterSpec);

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		KeyPair keyPair = keyPairGen.generateKeyPair();

		// ��Կ
		PublicKey publicKey = keyPair.getPublic();
		writeFile(publicKey.getEncoded(), publicClientkeyFile);

		// ˽Կ
		PrivateKey privateKey = keyPair.getPrivate();
		writeFile(privateKey.getEncoded(), privateClientkeyFile);
	}

	/**
	 * ���ɿͻ��˻�����Կ
	 * 
	 * @return
	 */
	private SecretKey getClientSecretKey() {
		byte[] privateClientKey = readFile(privateClientkeyFile);
		byte[] publicServerKey = readFile(publicServerkeyFile);

		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(publicServerKey);
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(
				privateClientKey);

		PublicKey publicKey = null;
		KeyFactory keyFactory = null;
		Key privateKey = null;
		KeyAgreement keyAgree = null;

		try {
			keyFactory = KeyFactory.getInstance(keyAlgorithm);
			publicKey = keyFactory.generatePublic(x509KeySpec);
			// privateKey = keyFactory.generatePrivate(x509KeySpec);
			privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
			// ������ԿЭ��
			keyAgree = KeyAgreement.getInstance(keyFactory.getAlgorithm());
			keyAgree.init(privateKey);
			keyAgree.doPhase(publicKey, true);
			return keyAgree.generateSecret(secretAlgorithm);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * �ͻ������ݽ���
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public byte[] decryptForClient() {
		SecretKey secretKey = getClientSecretKey();

		try {
			byte[] data = readFile(serverdataFile);
			Cipher cipher = Cipher.getInstance(secretKey.getAlgorithm());
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
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
	 * �ͻ������ݼ���
	 * 
	 * @param data
	 */
	public void encryptForClient(byte[] data) {

		SecretKey secretKey = getClientSecretKey();

		try {
			Cipher cipher = Cipher.getInstance(secretKey.getAlgorithm());
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			writeFile(cipher.doFinal(data), clientdataFile);
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
	}

	public static void main(String[] arg) {
		String data = "�������ã����ǿͻ���";
		BothDHServerFile bothDHServerFile = new BothDHServerFile();
		BothDHClientFile bothDHClientFile = new BothDHClientFile();

		// ���ɷ������Կ��
		bothDHServerFile.generateServerKeyFile();
		// ���ɿͻ�����Կ��
		bothDHClientFile.generateClientKeyFile();
		// �ͻ��˼���
		bothDHClientFile.encryptForClient(data.getBytes());
		// ����˽���
		byte[] data1 = bothDHServerFile.decryptForServer();
		System.out.println("Clientԭʼ���ݣ�" + data);
		System.out.println("Servet�������ݣ�" + new String(data1));

	}

}
