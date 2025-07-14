import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;

public class LoginController {

    @FXML private Button Button_Signup;
    @FXML private CheckBox Checkbox_Button_Show_Password;
    @FXML private PasswordField Password_Field;
    @FXML private Button SignIn_Button;
    @FXML private TextField Textfilepassword;
    @FXML private TextField Username_Field;
    @FXML private Label label_Status;

    @FXML
    void Button_Signup_action(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Signup.fxml"));
            Stage stage = (Stage) Button_Signup.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Sign Up");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void Checkbox_Button_showpassword_action(ActionEvent event) {
        if (Checkbox_Button_Show_Password.isSelected()) {
            Textfilepassword.setText(Password_Field.getText());
            Password_Field.setVisible(false);
            Textfilepassword.setVisible(true);
        } else {
            Password_Field.setText(Textfilepassword.getText());
            Password_Field.setVisible(true);
            Textfilepassword.setVisible(false);
        }
    }

    @FXML
    void SignIn_Button_action(ActionEvent event) {
        String username = Username_Field.getText();
        String password = Checkbox_Button_Show_Password.isSelected() ? Textfilepassword.getText() : Password_Field.getText();

        try (BufferedReader br = new BufferedReader(new FileReader("database.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2 && parts[0].equals(username) && parts[1].equals(password)) {
                    Session.currentUser = username; // set current user
                    label_Status.setText("Sign in successful!");
                    PauseTransition pause = new PauseTransition(Duration.seconds(1));
                    pause.setOnFinished(e -> {
                        try {
                            Parent root = FXMLLoader.load(getClass().getResource("ViewDisbord.fxml"));
                            Stage stage = (Stage) SignIn_Button.getScene().getWindow();
                            stage.setScene(new Scene(root));
                            stage.setTitle("Dashboard");
                            stage.show();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    });
                    pause.play();
                    return;
                }
            }
        } catch (IOException e) {
            label_Status.setText("Error reading database.");
            e.printStackTrace();
            return;
        }

        label_Status.setText("Invalid username or password.");
    }
}
