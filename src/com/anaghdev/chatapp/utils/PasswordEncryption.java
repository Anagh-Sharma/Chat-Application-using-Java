package com.anaghdev.chatapp.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public interface PasswordEncryption {

	public static String encryptPassword(String plainPassword) throws NoSuchAlgorithmException
	{
		String encryptedPassword = null;
		
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(plainPassword.getBytes());
		byte[] byteEncryptedPassword = md.digest();
		
		StringBuffer sb = new StringBuffer();
		for(byte b : byteEncryptedPassword)
		{
			sb.append(b);
		}
		
		encryptedPassword = sb.toString();

		return encryptedPassword;
	}
}
