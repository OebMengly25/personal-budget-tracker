
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class SceneController {

    private Stage stage;
    private HashMap<String, Scene> scenes = new HashMap<>();

    public SceneController(Stage stage) {
        this.stage = stage;
    }

    public void addScene(String name, String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scenes.put(name, scene);
    }

    public void activate(String name) {
        Scene scene = scenes.get(name);
        if (scene != null) {
            stage.setScene(scene);
            stage.show();
        } else {
            System.out.println("Scene " + name + " not found.");
        }
    }

    public void switchScene(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'switchScene'");
    }
}
