package jrsax;

import java.math.BigInteger;
import java.util.ArrayList;

public class RSAMessage {
    private final String message;
    private ArrayList<BigInteger> encryptedMessage;
    private ArrayList<BigInteger> decryptedMessage;
    private boolean isDecrypted = false;

    public RSAMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<BigInteger> getEncryptedMessage() {
        return encryptedMessage;
    }

    public ArrayList<BigInteger> getDecryptedMessage() {
        if (!isDecrypted) {
            throw new IllegalStateException("Message is not decrypted yet");
        }
        return decryptedMessage;
    }

    public void encrypt(BigInteger n, BigInteger e) {
        ArrayList<BigInteger> messageBigInts = messageToBigIntegers(message);
        encryptedMessage = new ArrayList<>();

        for (BigInteger messageBigInt : messageBigInts) {
            BigInteger encryptedBigInt = messageBigInt.modPow(e, n);
            encryptedMessage.add(encryptedBigInt);
        }
    }

    public void decrypt(BigInteger n, BigInteger d) {
        if (encryptedMessage == null) {
            throw new IllegalStateException("Message is not encrypted yet");
        }

        ArrayList<BigInteger> decryptedMessageBigInts = new ArrayList<>();
        for (BigInteger encryptedBigInt : encryptedMessage) {
            BigInteger decryptedBigInt = encryptedBigInt.modPow(d, n);
            decryptedMessageBigInts.add(decryptedBigInt);
        }

        decryptedMessage = decryptedMessageBigInts;
        isDecrypted = true;
    }

    public ArrayList<BigInteger> messageToBigIntegers(String message) {
        ArrayList<BigInteger> messageBigInts = new ArrayList<>();
        for (int i = 0; i < message.length(); i++) {
            messageBigInts.add(BigInteger.valueOf(message.charAt(i)));
        }
        return messageBigInts;
    }

    public String bigIntegersToMessage(ArrayList<BigInteger> messageBigInts) {
        StringBuilder message = new StringBuilder();
        for (BigInteger messageBigInt : messageBigInts) {
            message.append((char) messageBigInt.intValue());
        }
        return message.toString();
    }

    public String getDecryptedMessageAsString() {
        return bigIntegersToMessage(getDecryptedMessage());
    }

    public String getEncryptedMessageAsString() {
        return bigIntegersToMessage(getEncryptedMessage());
    }
}
