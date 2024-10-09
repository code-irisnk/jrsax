package jrsax;

import java.math.BigInteger;
import java.security.SecureRandom;

public class RSAKeypair {
    private final RSAPublicKey publicKey;
    private final RSAPrivateKey privateKey;

    public RSAKeypair() {
        SecureRandom random = new SecureRandom();

        BigInteger p = BigInteger.probablePrime(512, random);
        BigInteger q = BigInteger.probablePrime(512, random);

        BigInteger n = p.multiply(q);

        BigInteger f = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        BigInteger e = BigInteger.valueOf(65537);
        BigInteger d = e.modInverse(f);

        publicKey = new RSAPublicKey(n, e);
        privateKey = new RSAPrivateKey(n, d);
    }

    public RSAPublicKey getPublicKey() {
        return publicKey;
    }

    public RSAPrivateKey getPrivateKey() {
        return privateKey;
    }
}
