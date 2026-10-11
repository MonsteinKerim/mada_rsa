import java.math.BigInteger;

public class Euklid {

    public BigInteger calculate(BigInteger a, BigInteger b) {
        BigInteger x0 = BigInteger.ONE;
        BigInteger y0 = BigInteger.ZERO;
        BigInteger x1 = BigInteger.ZERO;
        BigInteger y1 = BigInteger.ONE;
        BigInteger phi = a;

        while (!b.equals(BigInteger.ZERO)) {
            BigInteger q = a.divide(b);
            BigInteger r = a.remainder(b);

            BigInteger oldX0 = x0;
            BigInteger oldY0 = y0;
            x0 = x1;
            y0 = y1;

            x1 = oldX0.subtract(q.multiply(x1));
            y1 = oldY0.subtract(q.multiply(y1));

            a = b;
            b = r;
        }

        if (!a.equals(BigInteger.ONE)) {
            throw new IllegalArgumentException("Es existiert kein multiplikatives Inverses.");
        }

        return y0.mod(phi); //y0 mod phi weil y0 negativ sein kann.

    }

}
