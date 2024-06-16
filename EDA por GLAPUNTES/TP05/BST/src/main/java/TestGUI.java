import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;

public class TestGUI<T extends Comparable<? super T>> extends Application {

    public static void main(String[] args) {
        // GUI
        launch(args);
    }

    public void start(Stage stage) {
        stage.setTitle("Drawing the BST");
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 500, 500);

        BST<Person> myTree = createModel();
        GraphicsTree<Person> c = new GraphicsTree<>(myTree);


        c.widthProperty().bind(scene.widthProperty());
        c.heightProperty().bind(scene.heightProperty());
        root.getChildren().add(c);
        stage.setScene(scene);
        stage.show();


    }

    private BST<Person> createModel() {
//		BST<Integer> myTree = new BST<>();
//		myTree.insert(50);
//		myTree.insert(60);
//		myTree.insert(80);
//		myTree.insert(20);
//		myTree.insert(70);
//		myTree.insert(40);
//		myTree.insert(44);
//		myTree.insert(10);
//		myTree.insert(40);
        BST<Person> myTree = new BST<>();
        myTree.insert(new Person("Ana", 50));
        myTree.insert(new Person("Juan", 60));
        myTree.insert(new Person("Sergio", 80));
        myTree.insert(new Person("Lila", 20));
        myTree.insert(new Person("Ana", 77));
        return myTree;
    }


}