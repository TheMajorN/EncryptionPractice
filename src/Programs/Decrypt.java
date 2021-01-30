package Programs;

import java.util.Scanner;

public class Decrypt {

        public static void main (String[] args) {

            Scanner keyboard = new Scanner(System.in);

            System.out.print("Type what you want to decrypt:>");
            String input = keyboard.nextLine();
            char[] chars = input.toCharArray();

            for (char c : chars) {
                c += 27;
                c -= 15;
                c /= 2;
                System.out.print(c);
            }
        }

    }
