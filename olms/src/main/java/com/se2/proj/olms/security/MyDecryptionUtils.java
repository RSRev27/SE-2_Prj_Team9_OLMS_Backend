package com.se2.proj.olms.security;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

//import com.assignment2.selenium_test.MyTestCase;

public class MyDecryptionUtils {
	
	private static final String ALGORITHM = "AES";
	private static final byte[] keyValue = "Please Don't ask me Okay".getBytes();

    public static String decrypt(String encryptedValue) throws Exception {
        SecretKeySpec key = new SecretKeySpec(keyValue, ALGORITHM);
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedValue = Base64.getDecoder().decode(encryptedValue);
        byte[] decValue = c.doFinal(decodedValue);
        return new String(decValue);
    }
	
}
