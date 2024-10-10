package jrsax;

import java.math.BigInteger;
import java.util.Base64;

public record RSAPublicKey(BigInteger n, BigInteger e) implements RSAKey {

    public String armorRepresentation() {
        String keyData = n + "," + e;
        String base64EncodedKey = Base64.getEncoder().encodeToString(keyData.getBytes());
        return "-----BEGIN RSA PUBLIC KEY-----\n" +
                base64EncodedKey + "\n" +
                "-----END RSA PUBLIC KEY-----";
    }
}