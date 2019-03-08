package com.jdui.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

public class MD5Encrypt {
	public static byte[] encryptByMD5(String password) {
		byte[] randomBytes = new byte[12];
		SecureRandom random = new SecureRandom();
		random.nextBytes(randomBytes);
		System.out.println("随机值=" + Arrays.toString(randomBytes));
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		md.update(randomBytes);
		md.update(password.getBytes());
		// 获得加密之后的密码
		byte[] passwordBytes = md.digest();
		System.out.println("加密之后的密码=" + Arrays.toString(passwordBytes));
		byte[] resultPassword = new byte[randomBytes.length + passwordBytes.length];
		System.arraycopy(randomBytes, 0, resultPassword, 0, randomBytes.length);
		System.arraycopy(passwordBytes, 0, resultPassword, randomBytes.length, passwordBytes.length);
		System.out.println("final密码=" + Arrays.toString(resultPassword));
		return resultPassword;
	}
	
	public static boolean validatePassword(String password, byte[] resultPassword) {
		byte[] randomBytes = new byte[12];
		System.arraycopy(resultPassword, 0, randomBytes, 0, randomBytes.length);
		System.out.println("密码校验随机值=" + Arrays.toString(randomBytes));
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		md.update(randomBytes);
		md.update(password.getBytes());
		// 获得加密之后的密码
		byte[] passwordBytes = md.digest();
		System.out.println("密码校验加密之后的密码=" + Arrays.toString(passwordBytes));
		byte[] passwordBytesInDB = new byte[resultPassword.length - randomBytes.length];
		System.arraycopy(resultPassword, randomBytes.length, passwordBytesInDB, 0, passwordBytesInDB.length);
		System.out.println("数据库中保存的加密之后的密码=" + Arrays.toString(passwordBytesInDB));
		if (Arrays.equals(passwordBytes, passwordBytesInDB)) {
			return true;
		} else {
			System.out.println("密码错误");
			return false;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		byte[] resultPassword = MD5Encrypt.encryptByMD5("123123");
		MD5Encrypt.validatePassword("123aaa", resultPassword);
	}

}
