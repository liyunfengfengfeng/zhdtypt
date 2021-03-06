package com.newage.iep.util;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.crypto.digests.MD4Digest;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.MessageDigest;
import java.security.Security;

/**
 * 用户注册时将密码进行MD5加密，存储在数据库里面，也就是说数据库中，用户名是明文，密码是加密后的
 * 登录时，将用户的密码进行MD5加密，根据用户名和加密后的密码进行查询
 * 因为密码经过MD5加密后，结果是一样的
 */
public class MD5Util {

	public static final String src = "md5 test";
//	public static void main(String[] args) 
//	{
//		jdkMD5();
//		jdkMD2();
//		
//		bcMD4();
//		bcMD5();
//		
//		bc2jdkMD4();
//		
//		ccMD5("123456");
//		ccMD2();
//
//	}
	
	// 用jdk实现:MD5
	public static void jdkMD5()
	{
		try 
		{
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] md5Bytes = md.digest(src.getBytes());
			System.out.println("JDK MD5:" + Hex.encodeHexString(md5Bytes));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 用jdk实现:MD2
	public static void jdkMD2()
	{
		try 
		{
			MessageDigest md = MessageDigest.getInstance("MD2");
			byte[] md2Bytes = md.digest(src.getBytes());
			System.out.println("JDK MD2:" + Hex.encodeHexString(md2Bytes));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// 用bouncy castle实现:MD5
	public static void bcMD5()
	{
		MD5Digest digest = new MD5Digest();
		digest.update(src.getBytes(),0,src.getBytes().length);
		byte[] md5Bytes = new byte[digest.getDigestSize()];
		digest.doFinal(md5Bytes, 0);
		System.out.println("bouncy castle MD5:" + org.bouncycastle.util.encoders.Hex.encode(md5Bytes));
		
	}
	

	// 用bouncy castle实现:MD4
	public static void bcMD4()
	{
		MD4Digest digest = new MD4Digest();
		digest.update(src.getBytes(),0,src.getBytes().length);
		byte[] md4Bytes = new byte[digest.getDigestSize()];
		digest.doFinal(md4Bytes, 0);
		System.out.println("bouncy castle MD4:" + org.bouncycastle.util.encoders.Hex.encode(md4Bytes));
	}
	
	// 用bouncy castle与jdk结合实现:MD4
	public static void bc2jdkMD4()
	{
		try 
		{
			Security.addProvider(new BouncyCastleProvider());
			MessageDigest md = MessageDigest.getInstance("MD4");
			byte[] md4Bytes = md.digest(src.getBytes());
			System.out.println("bc and JDK MD4:" + Hex.encodeHexString(md4Bytes));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 用common codes实现实现:MD5
	public static String ccMD5(String msg)
	{
//		System.out.println("common codes MD5:" + DigestUtils.md5Hex(src.getBytes()));
		return DigestUtils.md5Hex(msg);
	}
	
	// 用common codes实现实现:MD2
//	public static void ccMD2()
//	{
//		System.out.println("common codes MD2:" + DigestUtils.md2Hex(src.getBytes()));
//	}
	public static void main(String[]args){
		String msg = "lyf";
		System.out.println(MD5Util.ccMD5(msg));
	}
}
