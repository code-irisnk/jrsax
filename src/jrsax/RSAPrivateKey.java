package jrsax;
import java.math.BigInteger;
import java.util.Base64;

public class RSAPrivateKey implements RSAKey {
    private final BigInteger n;
    private final BigInteger d;

    public RSAPrivateKey(BigInteger n, BigInteger d) {
        this.n = n;
        this.d = d;
    }

    public BigInteger getN() {
        return n;
    }

    public BigInteger getD() {
        return d;
    }

    public String armorRepresentation() {
        String keyData = n + "," + d;
        String base64EncodedKey = Base64.getEncoder().encodeToString(keyData.getBytes());
        return "-----BEGIN RSA PRIVATE KEY-----\n" +
                base64EncodedKey + "\n" +
                "-----END RSA PRIVATE KEY-----";
    }
}