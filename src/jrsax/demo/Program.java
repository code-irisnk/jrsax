package jrsax.demo;

import jrsax.core.RSAKeypair;
import jrsax.message.RSAMessage;

import java.io.PrintStream;
import java.util.logging.Logger;

/**
 * Entry point for demonstrating RSA encryption and decryption.
 * <p>
 * This class demonstrates the generation of RSA keys, the encryption of a message using a public key,
 * and the decryption of that message using a private key.
 */
public class Program {

    /**
     * The main method, which starts the RSA encryption/decryption demonstration.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args){
        PrintStream out = System.out;
        out.println("Welcome to jRSAx!");

        RSAKeypair keypair1 = new RSAKeypair();

        out.println("Public Key of user 1: " + keypair1.getPublicKey().toString());

        RSAKeypair keypair2 = new RSAKeypair();

        out.println("Public Key of user 2: " + keypair2.getPublicKey().toString());

        RSAMessage message = new RSAMessage("Hi! I'm user 1! User 2, can you read this?");
        message.encrypt(keypair2.getPublicKey().n(), keypair2.getPublicKey().e());
        out.println("Encrypted message: " + message.getEncryptedMessageAsString());


        message.decrypt(keypair2.getPrivateKey().n(), keypair2.getPrivateKey().d());
        out.println("Decrypted message: " + message.getDecryptedMessageAsString());

        RSAMessage message2 = new RSAMessage("Yes user 1, I can!");
        message2.encrypt(keypair1.getPublicKey().n(), keypair1.getPublicKey().e());
        out.println("Encrypted message: " + message2.getEncryptedMessageAsString());
;
        message2.decrypt(keypair1.getPrivateKey().n(), keypair1.getPrivateKey().d());
        out.println("Decrypted message: " + message2.getDecryptedMessageAsString());
    }
}
