package com.pbph.shoppingmall.utils;
import java.security.MessageDigest;

public class MD5Utils {
	/**
	 * MD5���㷨��RFC1321 �ж��� ��RFC 1321�У������Test suite��4�������ʵ���Ƿ���ȷ�� MD5 ("") =
	 * d41d8cd98f00b204e9800998ecf8427e MD5 ("a") =
	 * 0cc175b9c0f1b6a831c399e269772661 MD5 ("abc") =
	 * 900150983cd24fb0d6963f7d28e17f72 MD5 ("message digest") =
	 * f96b697d7cb7938d525a2f31aaf161d0 MD5 ("abcdefghijklmnopqrstuvwxyz") =
	 * c3fcd3d76192e4007dfb496cca67e13b
	 * 
	 * @author LeoLee
	 * 
	 *         �������һ���ֽ����� ��������ֽ������ MD5 ����ַ�
	 */

	public static String encrypt(byte[] bytes) throws Exception {

		MessageDigest messageDigest = MessageDigest.getInstance("MD5");

		messageDigest.reset();
		messageDigest.update(bytes);
		byte[] byteArray = messageDigest.digest();

		StringBuffer md5StrBuff = new StringBuffer();

		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5StrBuff.append("0").append(
						Integer.toHexString(0xFF & byteArray[i]));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}
		return md5StrBuff.toString();

	}

	public static String encrypt2(byte[] source) {
		String s = null;

		char hexDigits[] = { // ��4���ֽ�ת���� 16 ���Ʊ�ʾ���ַ�
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
				'e', 'f' };

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(source);
			byte tmp[] = md.digest(); // MD5 �ļ�������һ�� 128 λ�ĳ�����
										// ���ֽڱ�ʾ���� 16 ���ֽ�
			char str[] = new char[16 * 2]; // ÿ���ֽ��� 16 ���Ʊ�ʾ�Ļ���ʹ��}���ַ�
											// ���Ա�ʾ�� 16 ������Ҫ 32 ���ַ�
			int k = 0; // ��ʾת������ж�Ӧ���ַ�λ��
			for (int i = 0; i < 16; i++) { // �ӵ�һ���ֽڿ�ʼ���� MD5 ��ÿһ���ֽ�
											// ת���� 16 �����ַ��ת��
				byte byte0 = tmp[i]; // ȡ�� i ���ֽ�
				str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // ȡ�ֽ��и� 4 λ������ת��,
															// >>>
															// Ϊ�߼����ƣ������λһ������
				str[k++] = hexDigits[byte0 & 0xf]; // ȡ�ֽ��е� 4 λ������ת��
			}

			s = new String(str); // ����Ľ��ת��Ϊ�ַ�

		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	public static void main(String[] args) throws Exception {
		System.out.println(encrypt("a".getBytes()));

	}
}
