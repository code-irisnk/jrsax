package jrsax.message;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Represents a message that can be encrypted and decrypted using RSA.
 * <p>
 * This class provides methods to encrypt and decrypt a given string message using RSA public and private keys.
 * The message is first converted into a series of {@link BigInteger} values, which can then be processed using
 * the RSA algorithm.
 */
public class RSAMessage {
    private final String message;
    private @Nullable ArrayList<BigInteger> encryptedMessage;
    private @Nullable ArrayList<BigInteger> decryptedMessage;
    private @NotNull MessageState state;
    /**
     * Creates a new RSAMessage with the given plain text.
     *
     * @param message The plain text message to be encrypted or decrypted.
     */
    public RSAMessage(@NotNull String message) {
        this.message = message;
        this.state = MessageState.UNENCRYPTED;
    }

    /**
     * Returns the encrypted message as an {@link ArrayList} of {@link BigInteger}.
     *
     * @return The encrypted message in its numeric form.
     * @throws IllegalStateException if the message is not encrypted.
     */
    public @NotNull ArrayList<BigInteger> getEncryptedMessage() {
        if (state != MessageState.ENCRYPTED || encryptedMessage == null) {
            throw new IllegalStateException("Message is not encrypted.");
        }
        return encryptedMessage;
    }

    /**
     * Returns the decrypted message as an {@link ArrayList} of {@link BigInteger}.
     * <p>
     * This method should only be called after the message has been decrypted. If the message is not decrypted, it
     * throws an {@link IllegalStateException}.
     *
     * @return The decrypted message in its numeric form.
     * @throws IllegalStateException if the message has not been decrypted yet.
     */
    public @NotNull ArrayList<BigInteger> getDecryptedMessage() {
        if (state != MessageState.DECRYPTED || decryptedMessage == null) {
            throw new IllegalStateException("Message is not decrypted yet.");
        }
        return decryptedMessage;
    }

    /**
     * Encrypts the message using the given RSA public key.
     *
     * @param n The modulus of the RSA public key.
     * @param e The public exponent of the RSA public key.
     */
    public void encrypt(@NotNull BigInteger n, @NotNull BigInteger e) {
        if (state != MessageState.UNENCRYPTED) {
            throw new IllegalStateException("Message is already encrypted or decrypted.");
        }

        ArrayList<BigInteger> messageBigInts = messageToBigIntegers(message);
        encryptedMessage = new ArrayList<>();

        for (BigInteger messageBigInt : messageBigInts) {
            BigInteger encryptedBigInt = messageBigInt.modPow(e, n);
            encryptedMessage.add(encryptedBigInt);
        }

        state = MessageState.ENCRYPTED;
    }

    /**
     * Decrypts the encrypted message using the given RSA private key.
     *
     * @param n The modulus of the RSA private key.
     * @param d The private exponent of the RSA private key.
     * @throws IllegalStateException if the message is not encrypted.
     */
    public void decrypt(@NotNull BigInteger n, @NotNull BigInteger d) {

        if (state != MessageState.ENCRYPTED || encryptedMessage == null) {
            throw new IllegalStateException("Message is not encrypted yet.");
        }

        decryptedMessage = new ArrayList<>();
        for (BigInteger encryptedBigInt : encryptedMessage) {
            BigInteger decryptedBigInt = encryptedBigInt.modPow(d, n);
            decryptedMessage.add(decryptedBigInt);
        }

        state = MessageState.DECRYPTED;
    }

    /**
     * Converts a plain text message into a list of {@link BigInteger} values.
     *
     * @param message The plain text message to be converted.
     * @return A list of {@link BigInteger} values representing the characters in the message.
     */
    private @NotNull ArrayList<BigInteger> messageToBigIntegers(@NotNull String message) {
        ArrayList<BigInteger> messageBigInts = new ArrayList<>();
        for (int i = 0; i < message.length(); i++) {
            messageBigInts.add(BigInteger.valueOf(message.charAt(i)));
        }
        return messageBigInts;
    }

    /**
     * Converts a list of {@link BigInteger} values back to the original plain text message.
     *
     * @param messageBigInts A list of {@link BigInteger} values representing the characters in the message.
     * @return The plain text message.
     */
    private @NotNull String bigIntegersToMessage(@NotNull ArrayList<BigInteger> messageBigInts) {
        StringBuilder result = new StringBuilder();
        for (BigInteger messageBigInt : messageBigInts) {
            result.append((char) messageBigInt.intValue());
        }
        return result.toString();
    }

    /**
     * Returns the decrypted message as a plain text string.
     *
     * @return The decrypted message in its original form.
     * @throws IllegalStateException if the message is not decrypted.
     */
    public String getDecryptedMessageAsString() {
        if (state != MessageState.DECRYPTED) {
            throw new IllegalStateException("Message is not decrypted.");
        }
        return bigIntegersToMessage(getDecryptedMessage());
    }

    /**
     * Returns the encrypted message as a plain text string (for demonstration purposes).
     * <p>
     * Note that this will return a string representation of the numeric encrypted values, which is typically not
     * how encrypted messages are handled in real-world applications.
     *
     * @return The encrypted message as a string.
     * @throws IllegalStateException if the message is not encrypted.
     */
    public String getEncryptedMessageAsString() {
        if (state != MessageState.ENCRYPTED) {
            throw new IllegalStateException("Message is not encrypted.");
        }
        return bigIntegersToMessage(getEncryptedMessage());
    }

    private enum MessageState {
        UNENCRYPTED,
        ENCRYPTED,
        DECRYPTED
    }
}
