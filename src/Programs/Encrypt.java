package Programs;

import java.util.Scanner;

public class Encrypt {

    public static void main (String[] args) {

        Scanner keyboard = new Scanner(System.in);

        System.out.print("Type what you want to encrypt:>");
        String input = keyboard.nextLine();
        char[] chars = input.toCharArray();

        for (char c : chars) {
            c *= 2;
            c += 15;
            c -= 27;
            System.out.print(c);
        }

    }

}
