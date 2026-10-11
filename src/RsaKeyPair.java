import java.math.BigInteger;

record RsaKeyPair(
        BigInteger n,
        BigInteger e,
        BigInteger d
) {}

record RsaKey(
        BigInteger n,
        BigInteger ed
) {}

