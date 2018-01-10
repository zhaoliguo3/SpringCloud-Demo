package com.xzl.boilerplate.common.security;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.security.KeyStore;
import java.security.PublicKey;
import java.security.cert.Certificate;

@Component
public class RSAKeyHelper {
    public static PublicKey PUBLICKEY;
    public static Key KEY = null;

    static {
        ClassPathResource resource = new ClassPathResource("mytest.jks");
        KeyStore keystore;
        Certificate cert = null;
        try {
            keystore = KeyStore.getInstance(KeyStore.getDefaultType());
            keystore.load(resource.getInputStream(), "mypass".toCharArray());
            KEY = keystore.getKey("mytest", "mypass".toCharArray());
            cert = keystore.getCertificate("mytest");
        } catch (Exception e) {
            e.printStackTrace();
        }

        PUBLICKEY = cert.getPublicKey();
    }
}
