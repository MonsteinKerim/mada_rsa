import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class RsaHandler {
    private final String PublicKeyPath = "pk.txt";
    private final String PrivateKeyPath = "sk.txt";

    private final String TextFilePath = "text.txt";
    private final String ChiffreFilePath = "chiffre.txt";
    private final String TextDecryptedFilePath = "text-d.txt";

    FileHandler fileHandler = new FileHandler();

    public void encryptData() throws IOException {
        ArrayList<BigInteger> encrypted = new ArrayList<>();

        RsaKey publicKey = readRsaKey(PublicKeyPath);
        System.out.println("Public Key: " + publicKey);

        String decryptedText = fileHandler.readFile(TextFilePath);
        for(int i = 0; i < decryptedText.length(); i++) {
            encrypted.add(encryptChar(decryptedText.charAt(i), publicKey));
        }
        String result = encrypted.stream()
                .map(BigInteger::toString)
                .collect(Collectors.joining(","));
        fileHandler.writeFile(ChiffreFilePath, result);
    }

    public void decryptData() throws IOException {
        ArrayList<BigInteger> decrypted = new ArrayList<>();
        RsaKey privateKey = readRsaKey(PrivateKeyPath);
        System.out.println("Private Key: " + privateKey);

        String encryptedText = fileHandler.readFile(ChiffreFilePath);
        String[] encryptedChars = encryptedText.split(",");
        for(int i = 0; i < encryptedChars.length; i++) {
            decrypted.add(decryptChar(new BigInteger(encryptedChars[i]), privateKey));
        }

        String result = decrypted.stream()
                .map(bi -> String.valueOf((char) bi.intValue()))
                .collect(Collectors.joining());

        fileHandler.writeFile(TextDecryptedFilePath, result);
    }

    private BigInteger encryptChar(char x, RsaKey publicKey) throws IOException {
        return fastModPow(BigInteger.valueOf(x), publicKey.ed(), publicKey.n());
    }

    private BigInteger decryptChar(BigInteger x, RsaKey privateKey) throws IOException {
        return fastModPow(x, privateKey.ed(), privateKey.n());
    }

    private BigInteger fastModPow(BigInteger base, BigInteger exp, BigInteger mod) {
        BigInteger result = BigInteger.ONE;
        base = base.mod(mod);

        while (exp.signum() > 0) {
            if (exp.testBit(0)) {
                result = result.multiply(base).mod(mod);
            }

            base = base.multiply(base).mod(mod);
            exp = exp.shiftRight(1);
        }

        return result;
    }

    private RsaKey readRsaKey(String filename) throws IOException {
        String content = fileHandler.readFile(filename).trim();

        content = content.replace("(", "").replace(")", "");

        String[] parts = content.split(",");

        return new RsaKey(
                new BigInteger(parts[0].trim()),
                new BigInteger(parts[1].trim())
        );
    }
}
