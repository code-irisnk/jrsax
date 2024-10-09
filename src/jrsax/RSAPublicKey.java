package jrsax;
import java.math.BigInteger;
import java.util.Base64;

public class RSAPublicKey implements RSAKey {
    private final BigInteger n;
    private final BigInteger e;

    public RSAPublicKey(BigInteger n, BigInteger e) {
        this.n = n;
        this.e = e;
    }

    public BigInteger getN() {
        return n;
    }

    public BigInteger getE() {
        return e;
    }

    public String armorRepresentation() {
        String keyData = n + "," + e;
        String base64EncodedKey = Base64.getEncoder().encodeToString(keyData.getBytes());
        return "-----BEGIN RSA PUBLIC KEY-----\n" +
                base64EncodedKey + "\n" +
                "-----END RSA PUBLIC KEY-----";
    }
}