
import FiboTree.FiboTree;
import controller.GraphicsTree;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;

public class FiboTestGUI extends Application {

    public static void main(String[] args) {
        // GUI
        launch(args);
    }

    public void start(Stage stage) {
        stage.setTitle("Drawing the BST");
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 500, 500);

        int n = 3; // nivel.
        FiboTree myTree = new FiboTree(n);
        GraphicsTree<Integer> c = new GraphicsTree<Integer>(myTree);

        c.widthProperty().bind(scene.widthProperty());
        c.heightProperty().bind(scene.heightProperty());
        root.getChildren().add(c);
        stage.setScene(scene);
        stage.show();
    }
}