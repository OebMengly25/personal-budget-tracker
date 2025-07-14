import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DashboardController {
   
    @FXML
    private Button ButtonCreate;

    @FXML
    private Button ButtonOpen;

    @FXML
    void ButtonCreate_Action(ActionEvent event) {
        System.out.println("Create Account clicked...");
         // Load the Signup.fxml file and switch to the Sign Up scene
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AddExpens.fxml"));
            Stage stage = (Stage) ButtonCreate.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Create Account");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void ButtonOpen_Action(ActionEvent event) {
        System.out.println("Open clicked...");
         // Load the Signup.fxml file and switch to the Sign Up scene
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AddBudget.fxml"));
            Stage stage = (Stage) ButtonCreate.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("OPEN");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
