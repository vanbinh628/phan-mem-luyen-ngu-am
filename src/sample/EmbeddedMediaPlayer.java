package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class EmbeddedMediaPlayer extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("listvideo.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("listvideo.fxml"));
        ListMovieControl listMovieControl=new ListMovieControl();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1174, 760));
        primaryStage.show();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                listMovieControl.FinishForm();
            }
        });

    }




    public static void main(String[] args) {
        launch(args);
    }
}

