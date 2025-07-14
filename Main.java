import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception
{
        // Load the FXML file and set the scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login.fxml"));
        
        Parent root = loader.load();
        
        // Set the title of the stage
        stage.setTitle("Login");
        
        // Set the scene with the loaded root
        stage.setScene(new Scene(root));
        
        // Show the stage
        stage.show();
}    public static void main(String[] args) {
        launch(args);
    }
}
