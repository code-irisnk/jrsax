package jrsax.core;

import jrsax.key.RSAPrivateKey;
import jrsax.key.RSAPublicKey;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Generates an RSA key pair (public and private keys).
 * <p>
 * The RSA algorithm relies on the mathematical properties of large prime numbers.
 * This class generates two large prime numbers, computes their product, and uses them to create the modulus for the keys.
 * The public key consists of the modulus and an exponent. The private key consists of the modulus and a different exponent.
 */
public class RSAKeypair {
    private final RSAPublicKey publicKey;
    private final RSAPrivateKey privateKey;

    /**
     * Generates an RSA key pair.
     * <p>
     * This constructor generates two random 512-bit prime numbers and uses them to create the RSA public and private keys.
     * The public exponent is fixed at 65537, which is a common choice for RSA.
     */
    public RSAKeypair() {
        SecureRandom random = new SecureRandom();

        // Generate two random large primes, p and q
        BigInteger p = BigInteger.probablePrime(4096, random);
        BigInteger q = BigInteger.probablePrime(4096, random);
        BigInteger n = p.multiply(q);

        // Calculate Euler's Totient function (φ(n)) = (p - 1)(q - 1)
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        BigInteger e = BigInteger.valueOf(65537); // Common public exponent
        BigInteger d = e.modInverse(phi); // Calculate private exponent

        // Create public and private keys
        publicKey = new RSAPublicKey(n, e);
        privateKey = new RSAPrivateKey(n, d);

    }

    /**
     * Returns the generated RSA public key.
     *
     * @return The {@link RSAPublicKey} object containing the modulus and public exponent.
     */
    public RSAPublicKey getPublicKey() {
        return publicKey;
    }

    /**
     * Returns the generated RSA private key.
     *
     * @return The {@link RSAPrivateKey} object containing the modulus and private exponent.
     */
    public RSAPrivateKey getPrivateKey() {
        return privateKey;
    }
}
