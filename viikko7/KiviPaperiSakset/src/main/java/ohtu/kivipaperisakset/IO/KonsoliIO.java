package ohtu.kivipaperisakset.IO;

import java.util.Scanner;

public class KonsoliIO implements IO {

    private static final Scanner scanner = new Scanner(System.in);

    @Override
    public String lueRivi() {
        return scanner.nextLine();
    }
}
