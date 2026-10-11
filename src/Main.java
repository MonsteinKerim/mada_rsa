void main() throws IOException {
    /* Euklid Test
    Euklid algo = new Euklid();

    BigInteger phi = BigInteger.valueOf(18);
    BigInteger e = BigInteger.valueOf(7);
    BigInteger d = algo.calculate(phi, e);

    System.out.println("d = " + d);
    */

    RsaKeyPair keyPair = new RsaKeyGen().generate(2048);
    FileHandler fileHandler = new FileHandler();

    String pubKey = "(" + keyPair.n() + ", " + keyPair.e() + ")";
    String privKey = "(" + keyPair.n() + ", " + keyPair.d() + ")";

    fileHandler.writeFile("pk.txt", pubKey);
    fileHandler.writeFile("sk.txt", privKey);
    System.out.println("Schlüssel wurden gespeichert.");
}
