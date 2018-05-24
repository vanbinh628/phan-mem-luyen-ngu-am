package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.Database.MovieDAO;
import sample.Model.Phim;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ListMovieControl implements Initializable {
    @FXML
    ListView<Phim> listhoathinh;
    @FXML
    ListView<Phim> listhanhdong;
    @FXML
    Button btn;
    public void FinishForm()
    {
        System.out.println("close form");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        MovieDAO movieDAO=new MovieDAO();
        List<Phim> phims=null;
        try {
            phims=movieDAO.getMovieList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<Phim> phimsleve=null;
        try {
            phimsleve=movieDAO.getMovieListLeve();
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        Phim phima=new Phim();
//        phima.setTen("Frozen");
//        phima.setVideo("G:/giaitri/EnglishROUTINE.mp4");
//        phima.setSubEng("G:/giaitri/naccy.srt");
//        phima.setAnh("sample/frozen.jpg");
//        Phim phimb=new Phim();
//        phimb.setTen("Chúa `Nhẫn");
//        phimb.setAnh("sample/chuanhan.jpg");
//        Phim phimc=new Phim();
//        phimc.setTen("cỏ xanh");
//        phimc.setAnh("sample/coxanh.jpg");

//        listhoathinh.getItems().add(phima);
//        listhoathinh.getItems().add(phimb);
//        listhoathinh.getItems().add(phimc);
        for (int i=0;i<phims.size();i++)
        {
            listhoathinh.getItems().add(phims.get(i));
        }
        for (int i=0;i<phimsleve.size();i++)
        {
            listhanhdong.getItems().add(phimsleve.get(i));
        }


        //btn.setAlignment(Pos.BASELINE_RIGHT);
        listhoathinh.setOrientation(Orientation.HORIZONTAL);
        listhoathinh.setCellFactory(param -> new ListCell<Phim>() {
            private ImageView imageView = new ImageView();

            @Override
            public void updateItem(Phim name, boolean empty) {
                super.updateItem(name, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    Image image  =null;
                    try {
                        image  = new Image("file:///"+name.getAnh());
                    } catch (Exception e) {
                        image  = new Image("drawable/star.png");
                    }
                    imageView.setImage(image);
                    imageView.setFitWidth(100);
                    imageView.setFitHeight(150);
                    VBox vBox = new VBox();
                    vBox.setSpacing(10);
                    vBox.setAlignment(Pos.CENTER);
                    vBox.setMaxWidth(200);
                    vBox.getChildren().addAll(imageView);

                    Label label = new Label(name.getTen());
                    label.setFont(new Font("Arial", 30));
                    label.setAlignment(Pos.CENTER);
                    label.setMaxWidth(200);
                    label.setTextAlignment(TextAlignment.CENTER);

                    vBox.getChildren().addAll(label);
                    setGraphic(vBox);
                }
            }
        });

        listhanhdong.setOrientation(Orientation.HORIZONTAL);
        listhanhdong.setCellFactory(param -> new ListCell<Phim>() {
            private ImageView imageView = new ImageView();

            @Override
            public void updateItem(Phim name, boolean empty) {
                super.updateItem(name, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    Image image  =null;
                    try {
                        image  = new Image("file:///"+name.getAnh());
                    } catch (Exception e) {
                        image  = new Image("drawable/star.png");
                    }
                    imageView.setImage(image);
                    imageView.setFitWidth(100);
                    imageView.setFitHeight(150);
                    VBox vBox = new VBox();
                    vBox.setSpacing(10);
                    vBox.setAlignment(Pos.CENTER);
                    vBox.setMaxWidth(200);
                    vBox.getChildren().addAll(imageView);

                    Label label = new Label(name.getTen());
                    label.setFont(new Font("Arial", 30));
                    label.setAlignment(Pos.CENTER);
                    label.setMaxWidth(200);
                    label.setTextAlignment(TextAlignment.CENTER);

                    vBox.getChildren().addAll(label);
                    setGraphic(vBox);
                }
            }
        });


        listhoathinh.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent click) {

                if (click.getClickCount() == 2) {

                    int i=listhoathinh.getSelectionModel().getSelectedIndex();
                    Phim movie=listhoathinh.getItems().get(i);
                    MediaControl tc = new MediaControl(movie);

                    // Load in the .fxml file:
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/sample.fxml"));
                    loader.setController(tc);
                    Parent root = null;
                    try {
                        root = loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    // Set the scene:
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setScene(new Scene(root));
                    stage.setTitle("Task");
                    stage.showAndWait();
//                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/sample.fxml"));
//                    Parent root1 = null;
//                    try {
//                        root1 = (Parent) fxmlLoader.load();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    Stage stage = new Stage();
//                    stage.setScene(new Scene(root1));
//                    stage.show();
                }
            }
        });

        listhanhdong.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent click) {

                if (click.getClickCount() == 2) {

                    int i=listhanhdong.getSelectionModel().getSelectedIndex();
                    Phim movie=listhanhdong.getItems().get(i);
                    MediaControl tc = new MediaControl(movie);

                    // Load in the .fxml file:
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/sample.fxml"));
                    loader.setController(tc);
                    Parent root = null;
                    try {
                        root = loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    // Set the scene:
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setScene(new Scene(root));
                    stage.setTitle("Task");
                    stage.showAndWait();

                    stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                        public void handle(WindowEvent we) {
                            tc.FinishForm();
                        }
                    });
//                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/sample.fxml"));
//                    Parent root1 = null;
//                    try {
//                        root1 = (Parent) fxmlLoader.load();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    Stage stage = new Stage();
//                    stage.setScene(new Scene(root1));
//                    stage.show();
                }
            }
        });


    }
}
