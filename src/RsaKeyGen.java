import java.math.BigInteger;
import java.security.SecureRandom;

public class RsaKeyGen {

    public RsaKeyPair generate(int keySize) {
        int primeSize = keySize / 2;
        // 1. p generieren
        SecureRandom rndm = new SecureRandom(); // SecureRandom anstatt Random nach Review Empfehlung von ChatGPT
        BigInteger p = BigInteger.probablePrime(primeSize, rndm);
        // 2. q generieren und sicherstellen, dass p != q
        BigInteger q = p;
        while (p.equals(q)) {
            q = BigInteger.probablePrime(primeSize, rndm);
        }
        // 3. n berechnen
        BigInteger n = p.multiply(q);
        // 4. phi berechnen
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        // 5. geeignetes e bestimmen
        BigInteger e = findE(phi);
        // 6. d mit deinem Euklid-Algorithmus berechnen
        Euklid algo = new Euklid();
        BigInteger d = algo.calculate(phi, e);
        // Überprüfen ob Bedingung erfüllt
        if (!e.multiply(d).mod(phi).equals(BigInteger.ONE)) {
            throw new IllegalStateException("Ungültiges Schlüsselpaar.");
        }
        // 7. Schlüsselpaar zurückgeben
        return new RsaKeyPair(n, e, d);
    }

    private BigInteger findE(BigInteger phi) {
        BigInteger e = BigInteger.valueOf(3);

        while (e.compareTo(phi) < 0) {
            // Wenn ggT==1 gefunden
            if (e.gcd(phi).equals(BigInteger.ONE)) {
                return e;
            }
            // sonst um 2 erhöhen.
            e = e.add(BigInteger.TWO);
        }
        throw new IllegalArgumentException("Kein passendes e gefunden.");
    }

}
