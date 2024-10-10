package jrsax.key;

import java.math.BigInteger;

/**
 * Represents the RSA private key.
 * <p>
 * This key consists of two components:
 * <ul>
 *   <li><b>n</b>: The modulus.</li>
 *   <li><b>d</b>: The private exponent.</li>
 * </ul>
 */
public record RSAPrivateKey(BigInteger n, BigInteger d) implements RSAKey {
}
