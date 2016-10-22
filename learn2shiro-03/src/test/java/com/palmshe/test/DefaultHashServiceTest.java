package com.palmshe.test;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.apache.shiro.crypto.hash.SimpleHashRequest;
import org.apache.shiro.util.SimpleByteSource;
import org.junit.Test;

public class DefaultHashServiceTest {

	/**
	 * 测试Shiro散列服务如何生成散列对象
	 */
	@Test
	public void testDefaultHashService(){
		//算法服务定义，配置后面的算法请求，得出散列对象
		DefaultHashService hashService= new DefaultHashService();
		//设置算法
		hashService.setHashAlgorithmName(Sha512Hash.ALGORITHM_NAME);
		//算法服务的私盐
		hashService.setPrivateSalt(new SimpleByteSource("private-salt"));
		//需要公盐
		hashService.setGeneratePublicSalt(true);
		//设置公盐随机数生成器
		hashService.setRandomNumberGenerator(new SecureRandomNumberGenerator());
		//迭代次数
		hashService.setHashIterations(1);
		
		//模拟散列请求
		//请求带有公盐，返回的公盐是一致的
		SimpleHashRequest hashRequest= new SimpleHashRequest(Md5Hash.ALGORITHM_NAME, new SimpleByteSource("123456"), new SimpleByteSource("public-salt"), 1);
		//请求不带公盐，返回的是随机的
		//SimpleHashRequest hashRequest= new SimpleHashRequest(Md5Hash.ALGORITHM_NAME, new SimpleByteSource("123456"), null, 1);
		Hash hashResult= hashService.computeHash(hashRequest);
		System.out.println(hashResult.getAlgorithmName());
		System.out.println(hashResult.getIterations());
		System.out.println(hashResult.getSalt().toString());
		//如果散列请求带有公盐，那么hash值将是一定的，如果没有公盐，那么hash值将是变动的
		System.out.println(hashResult.toString());
	}
}
