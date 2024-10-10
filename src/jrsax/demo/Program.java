package jrsax.demo;

import jrsax.core.RSAKeypair;
import jrsax.message.RSAMessage;

import java.util.logging.Logger;

/**
 * Entry point for demonstrating RSA encryption and decryption.
 * <p>
 * This class demonstrates the generation of RSA keys, the encryption of a message using a public key,
 * and the decryption of that message using a private key.
 */
public class Program {
    private static final Logger logger = Logger.getLogger(Program.class.getName());

    /**
     * The main method, which starts the RSA encryption/decryption demonstration.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        logger.info("Starting RSA encryption/decryption demo.");
        System.out.println("Welcome to jRSAx!");

        // Generate RSA keypair
        RSAKeypair keypair = new RSAKeypair();

        // Log keypair information
        logger.info("Public Key: " + keypair.getPublicKey().toString());
        logger.info("Private Key: " + keypair.getPrivateKey().toString());

        // Create a message and encrypt it
        RSAMessage message = new RSAMessage("Hello World");
        message.encrypt(keypair.getPublicKey().n(), keypair.getPublicKey().e());
        System.out.println("Encrypted message: " + message.getEncryptedMessageAsString());

        // Decrypt the message
        message.decrypt(keypair.getPrivateKey().n(), keypair.getPrivateKey().d());
        System.out.println("Decrypted message: " + message.getDecryptedMessageAsString());
    }
}
