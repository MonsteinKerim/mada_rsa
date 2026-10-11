void main() throws IOException {
    /* Euklid Test
    Euklid algo = new Euklid();

    BigInteger phi = BigInteger.valueOf(18);
    BigInteger e = BigInteger.valueOf(7);
    BigInteger d = algo.calculate(phi, e);

    System.out.println("d = " + d);
    */
    Scanner scanner = new Scanner(System.in);

    boolean exit = false;

    while (!exit) {
        System.out.println("Was möchten Sie machen?");
        System.out.println("\t(1) Schlüsselpaar erstellen");
        System.out.println("\t(2) Verschlüsseln");
        System.out.println("\t(3) Entschlüsseln");
        System.out.println("\t(4) Abbrechen");

        String input = scanner.nextLine().trim();

        switch (input) {
            case "1":
                System.out.println("(1) Schlüsselpaar erstellen gewählt");
                generateRsaKey();
                break;

            case "2":
                System.out.println("(2) Verschlüsseln gewählt");
                encryptData();
                break;

            case "3":
                System.out.println("(3) Entschlüsseln gewählt");
                decryptData();
                break;

            case "4":
                System.out.println("(4) Abbruch...");
                exit = true;
                break;

            default:
                System.out.println("Ungültige Eingabe!");
        }
    }
}

private void generateRsaKey() throws IOException {
    RsaKeyPair keyPair = new RsaKeyGen().generate(2048);
    FileHandler fileHandler = new FileHandler();

    String pubKey = "(" + keyPair.n() + ", " + keyPair.e() + ")";
    String privKey = "(" + keyPair.n() + ", " + keyPair.d() + ")";

    fileHandler.writeFile("pk.txt", pubKey);
    fileHandler.writeFile("sk.txt", privKey);
    System.out.println("Schlüssel wurden gespeichert.");
}

private void encryptData() throws IOException {
    RsaHandler rsaHandler = new RsaHandler();
    rsaHandler.encryptData();
}

private void decryptData() throws IOException {
    RsaHandler rsaHandler = new RsaHandler();
    rsaHandler.decryptData();
}
