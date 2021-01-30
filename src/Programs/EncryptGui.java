package Programs;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

public class EncryptGui extends Application {

    Button encButton;

    public static void main (String[] args) {
            launch(args);
        }

    @Override
    public void start(Stage primaryStage) {
    primaryStage.setTitle("Encryption");

    // UPPER TEXT FIELD
    TextField input = new TextField();
    input.setStyle("-fx-control-inner-background: #03fc28; -fx-faint-focus-color: #03fc28");

    // BUTTON
    encButton = new Button("Encrypt");
    encButton.setStyle("-fx-background-color: #03fc28");
    Font font = Font.font("Courier New", FontWeight.EXTRA_BOLD, 30);
    encButton.setFont(font);
    encButton.setOnAction(e -> enc(input));

    // LAYOUT
    VBox layout = new VBox(10);
    layout.styleProperty().set("-fx-background-color: #000000");
    layout.setAlignment(Pos.BASELINE_CENTER);
    layout.setPadding(new Insets(20, 20, 20, 20));
    layout.getChildren().addAll(input, encButton);

    // WINDOW
    Scene scene = new Scene(layout, 300, 200);
    primaryStage.setScene(scene);
    primaryStage.show();
    }

    private void enc(TextField input) {
        String extract = input.getText();
        char[] chars = extract.toCharArray();

        int n = 0;
        char[] output = new char[chars.length];

        for (char c : chars) {
            c *= 2;
            c += 15;
            c -= 27;
            output[n] = c;
            n++;
        }


        String converted = String.valueOf(output);
        //System.out.println(converted);

        if (output.length > 0) {
            TextField text = new TextField(converted);
            text.setStyle("-fx-control-inner-background: #03fc28");

            VBox layout = new VBox(10);
            layout.styleProperty().set("-fx-background-color: #000000");
            layout.setAlignment(Pos.BASELINE_CENTER);
            layout.setPadding(new Insets(20, 20, 20, 20));
            layout.getChildren().addAll(text);

            Stage stage = new Stage();

            Scene scene = new Scene(layout, 200, 80);
            stage.setScene(scene);
            stage.show();
        } else {
            System.out.println("Nothing was typed");
        }
    }

    public static void encryptDecrypt(String key, int cipherMode, File in, File out)
            throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IOException {

        FileInputStream fis = new FileInputStream(in);
        FileOutputStream fos = new FileOutputStream(out);

        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes());

        SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = skf.generateSecret(desKeySpec);

        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

        if (cipherMode == Cipher.ENCRYPT_MODE) {
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, SecureRandom.getInstance("SHA1PRNG"));
            CipherInputStream cis = new CipherInputStream(fis, cipher);
            write(cis, fos);

        } else if (cipherMode == Cipher.DECRYPT_MODE) {
            cipher.init(Cipher.DECRYPT_MODE, secretKey, SecureRandom.getInstance("SHA1PRNG"));
            CipherOutputStream cos = new CipherOutputStream(fos, cipher);
            write(fis, cos);
        }
    }

    private static void write(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[64];
        int numOfBytesRead;

        while((numOfBytesRead = in.read(buffer)) != -1) {
            out.write(buffer, 0, numOfBytesRead);
        }
        out.close();
        in.close();
    }
}
