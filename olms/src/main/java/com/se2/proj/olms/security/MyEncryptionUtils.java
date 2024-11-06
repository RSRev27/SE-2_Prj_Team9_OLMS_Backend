package com.se2.proj.olms.security;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

//import com.assignment2.selenium_test.MyTestCase;

public class MyEncryptionUtils {
	
	private static final String ALGORITHM = "AES";
	private static final byte[] keyValue = "Please Don't ask me Okay".getBytes();

    public static String encrypt(String encryptValue) throws Exception {
        SecretKeySpec key = new SecretKeySpec(keyValue, ALGORITHM);
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.ENCRYPT_MODE, key);
        String encodedValue = Base64.getEncoder().encodeToString(encryptValue.getBytes());
        //byte[] encValue = c.doFinal(encodedValue);
        return new String(encodedValue);
    }
	
}
