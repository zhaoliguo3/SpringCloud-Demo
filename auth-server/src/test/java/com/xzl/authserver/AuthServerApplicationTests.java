package com.xzl.authserver;

import io.jsonwebtoken.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthServerApplicationTests {

	@Test
	public void contextLoads() throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException, UnrecoverableKeyException {
		ClassPathResource resource = new ClassPathResource("mytest.jks");
		KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
		keystore.load(resource.getInputStream(), "mypass".toCharArray());

		Key key = keystore.getKey("mytest", "mypass".toCharArray());
		Certificate cert = keystore.getCertificate("mytest");
		PublicKey publicKey = cert.getPublicKey();
		Map<String, Object> claims = new HashMap<>();
		claims.put("user", "cope");
		Calendar expires = Calendar.getInstance();
		expires.roll(Calendar.HOUR, 2);
		String s = Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(new Date())
				.setExpiration(expires.getTime())
				.signWith(SignatureAlgorithm.RS256, key)
				.compact();
		System.out.println(s);
		Jwt sdf = Jwts.parser().setSigningKey(publicKey).require("user", "cope").parse(s);
		Claims c2 = (Claims) sdf.getBody();
		sdf.toString();
	}

}
