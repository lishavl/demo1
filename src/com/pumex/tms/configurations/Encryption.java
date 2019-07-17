package com.pumex.tms.configurations;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Encryption {
	private static final String ALGORITHM = "AES";
	private static final String KEY = "$keyfor$quoteapp";

	public static void main(String[] args) {
		try {

			System.out.println(Encryption.encrypt("demo"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/*
	 * Method to do AES encryption and BASE64 encode the text
	 * 
	 * @return AES encrypted, BASE64 encoded cipher text
	 */
	public static String encryptAndEncode(String value) throws Exception {
		Key key = generateKey();
		Cipher cipher = Cipher.getInstance(Encryption.ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] encryptedByteValue = cipher.doFinal(value.getBytes("utf-8"));
		String encryptedValue64 = new BASE64Encoder()
				.encode(encryptedByteValue);
		String encoded = Base64.encodeBase64String(encryptedValue64.getBytes());
		return encoded;
	}

	/*
	 * Method to decode/decrypt an AES encrypted and BASE64 encoded cipher text
	 * 
	 * @return decrypted plain text
	 */
	public static String decodeAndDecrypt(String value) throws Exception {
		String decoded = new String(Base64.decodeBase64(value));
		Key key = generateKey();
		Cipher cipher = Cipher.getInstance(Encryption.ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] decryptedValue64 = new BASE64Decoder().decodeBuffer(decoded);
		byte[] decryptedByteValue = cipher.doFinal(decryptedValue64);
		String decryptedValue = new String(decryptedByteValue, "utf-8");
		return decryptedValue;

	}

	/*
	 * Method to do AES encryption
	 * 
	 * @return AES encrypted cipher text
	 */
	public static String encrypt(String value) throws Exception {
		Key key = generateKey();
		Cipher cipher = Cipher.getInstance(Encryption.ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] encryptedByteValue = cipher.doFinal(value.getBytes("utf-8"));
		String encryptedValue64 = new BASE64Encoder()
				.encode(encryptedByteValue);
		return encryptedValue64;

	}

	/*
	 * Method to decode/decrypt an AES encrypted cipher text
	 * 
	 * @return decrypted plain text
	 */
	public static String decrypt(String value) throws Exception {
		Key key = generateKey();
		Cipher cipher = Cipher.getInstance(Encryption.ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] decryptedValue64 = new BASE64Decoder().decodeBuffer(value);
		byte[] decryptedByteValue = cipher.doFinal(decryptedValue64);
		String decryptedValue = new String(decryptedByteValue, "utf-8");
		return decryptedValue;

	}

	/*
	 * Method to generate key for encryption
	 * 
	 * @return decrypted plain text
	 */
	private static Key generateKey() throws Exception {
		Key key = new SecretKeySpec(Encryption.KEY.getBytes(),
				Encryption.ALGORITHM);
		return key;
	}
}