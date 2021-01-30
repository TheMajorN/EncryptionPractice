package Programs;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

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
}
