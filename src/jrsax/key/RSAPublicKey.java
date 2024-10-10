package jrsax.key;

import java.math.BigInteger;

/**
 * Represents the RSA public key.
 * <p>
 * This key consists of two components:
 * <ul>
 *   <li><b>n</b>: The modulus.</li>
 *   <li><b>e</b>: The public exponent.</li>
 * </ul>
 */
public record RSAPublicKey(BigInteger n, BigInteger e) implements RSAKey {
}
