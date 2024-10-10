package jrsax;

public class Program {
    public static void main(String[] args) {
        RSAKeypair keypair = new RSAKeypair();

        System.out.println("Public key: \n" + keypair.getPublicKey().armorRepresentation());
        System.out.println("Private key: \n" + keypair.getPrivateKey().armorRepresentation());

        RSAMessage helloWorld = new RSAMessage("Hello World");

        helloWorld.encrypt(keypair.getPublicKey().n(), keypair.getPublicKey().e());
        helloWorld.decrypt(keypair.getPrivateKey().n(), keypair.getPrivateKey().d());

        System.out.println("Encrypted message: " + helloWorld.getEncryptedMessageAsString());
        System.out.println("Decrypted message: " + helloWorld.getDecryptedMessageAsString());
    }
}
