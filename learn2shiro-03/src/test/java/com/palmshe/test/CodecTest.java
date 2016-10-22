package com.palmshe.test;

import java.io.UnsupportedEncodingException;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.CodecSupport;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Assert;
import org.junit.Test;


/**
 * 加密解密测试
 * @author xiong.song
 */
public class CodecTest {

	/**
	 * Base64加密解密
	 * @throws UnsupportedEncodingException
	 */
	@Test
	public void testBase64Codec(){
		String userName= "中国";
		//String encodeName= Base64.encodeToString(userName.getBytes("UTF-8"));
		//使用CodecSupport工具类
		String encodeName= Base64.encodeToString(CodecSupport.toBytes(userName, "UTF-8"));
		System.out.println(encodeName);
		Assert.assertEquals("中国", Base64.decodeToString(encodeName));
	}
	
	/**
	 * Hex加密解密
	 * @throws UnsupportedEncodingException
	 */
	@Test
	public void testHexCodec() throws UnsupportedEncodingException{
		String userName= "中国";
		//String encodeName= Hex.encodeToString(userName.getBytes("UTF-8"));
		//使用CodecSupport工具类
		String encodeName= Hex.encodeToString(CodecSupport.toBytes(userName, "UTF-8"));
		System.out.println(encodeName);
		//Assert.assertEquals("中国", new String(Hex.decode(encodeName)));
		Assert.assertEquals("中国", CodecSupport.toString(Hex.decode(encodeName), "UTF-8"));
	}
	
	/**
	 * Md5散列
	 */
	@Test
	public void testMd5Hash(){
	    String data= "中国";
	    String salt= "123";
	    String md5Result= new Md5Hash(data, salt, 2).toString();
	    System.out.println(md5Result);
	    System.out.println(md5Result.length());
	}
	
	/**
	 * Sha512散列
	 */
	@Test
	public void testSha512Hash(){
		String data= "中国";
	    String salt= "123";
	    String sha512Result= new Sha512Hash(data, salt, 1).toString();
	    System.out.println(sha512Result);
	    System.out.println(sha512Result.length());
	}
	
	/**
	 * 通用散列
	 */
	@Test
	public void testSimpleHash(){
		String data= "中国";
	    String salt= "123";
	    //传入算法名称即可
	    String simpleResult= new SimpleHash(Md5Hash.ALGORITHM_NAME, data, salt, 2).toString();
	    System.out.println(simpleResult);
	    System.out.println(simpleResult.length());
	}
}
