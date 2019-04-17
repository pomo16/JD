package test;

import com.ssm.common.util.MD5Encrypt;

public class UtilTest {
	
	public static void main(String[] args) {	
		byte[] resultPassword = MD5Encrypt.encryptByMD5("12aa****");
		MD5Encrypt.validatePassword("12aa****", resultPassword);
	}
}
