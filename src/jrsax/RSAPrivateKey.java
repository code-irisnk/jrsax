package jrsax;

import java.math.BigInteger;
import java.util.Base64;

public record RSAPrivateKey(BigInteger n, BigInteger d) implements RSAKey {

    public String armorRepresentation() {
        String keyData = n + "," + d;
        String base64EncodedKey = Base64.getEncoder().encodeToString(keyData.getBytes());
        return "-----BEGIN RSA PRIVATE KEY-----\n" +
                base64EncodedKey + "\n" +
                "-----END RSA PRIVATE KEY-----";
    }
}