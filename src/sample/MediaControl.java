package sample;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.Duration;
import sample.Database.LuuCauDAO;
import sample.Model.LuuCau;
import sample.Model.Phim;
import sample.Function.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MediaControl implements Initializable {

    private MediaPlayer mp;
    private Media me;
    private List<String> listspell=new ArrayList<>();
    private final Image IMAGE_RUBY  = new Image("drawable/star_null.png");
    private final Image IMAGE  = new Image("drawable/star.png");

    private final boolean repeat = false;
    private boolean stopRequested = false;
    private boolean atEndOfMedia = false;
    private Duration duration;
    @FXML
    private Slider timeSlider;
    @FXML
    private Label playTime;
    @FXML
    private Slider volumeSlider;
    @FXML
    private Button playButton;
    @FXML
    private MediaView mediaView;
    @FXML
    private Pane panebg;
    @FXML
    private ListView<Readfile.Line> list;
    @FXML
    private ListView<LuuCau> listyeu;
    @FXML
    private WebView web;
    @FXML
    private Tab tabsub;
    @FXML
    private Tab tabyeu;
    List<Readfile.Line> linessub;

    public String filevideo;
    public String filesub;
    public Phim phim;
    public MediaControl(Phim phim)
    {
        this.phim=phim;
        this.filevideo=phim.getVideo();
        this.filesub=phim.getSubEng();
    }
    public void FinishForm()
    {
        this.mediaView.getMediaPlayer().stop();
        System.out.println("Stop");
    }
    public void LoadListLuuCau(String idphim)
    {
        listyeu.getItems().clear();
        LuuCauDAO luu=new LuuCauDAO();
        List<LuuCau> luuCaus=null;
        try {
            luuCaus=new LuuCauDAO().getListLuuCauByID(idphim);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < luuCaus.size(); i++) {
            listyeu.getItems().add(luuCaus.get(i));
        }
        listyeu.setCellFactory(lv ->{ListCell<LuuCau> cell = new ListCell<LuuCau>() {
            private ImageView imageView = new ImageView();

            @Override
            public void updateItem(LuuCau name, boolean empty) {
                super.updateItem(name, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    imageView.setImage(new Image("drawable/bin.png"));
                    imageView.setFitWidth(25);
                    imageView.setFitHeight(25);
                    Label label = new Label();
                    label.setText(name.getNoidungsub());
                    label.setFont(new Font("Arial", 12));
                    label.setMaxWidth(Double.MAX_VALUE);
                    label.setMaxHeight(Double.MAX_VALUE);
                    HBox.setHgrow(label, Priority.ALWAYS);

                    HBox hbox = new HBox();
                    hbox.getChildren().addAll(label,imageView);
                    setGraphic(hbox);
                }
            }


        };
            cell.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent click) {

                    if (click.getClickCount() == 2) {
                        LuuCauDAO luuCauDAO=new LuuCauDAO();
                    }
                }
            });

            return  cell;
        });


    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //filevideo="G:/giaitri/EnglishROUTINE.mp4";
        String path = "file:///"+filevideo;
        me = new Media(path);


        mp = new MediaPlayer(me);
        mediaView.setMediaPlayer(mp);
        mp.setAutoPlay(true);


        //String filesub = "G:/giaitri/english_routine.srt";

        linessub = new Readfile().start(filesub);
        for (int i = 0; i < linessub.size(); i++) {
            list.getItems().add(linessub.get(i));
        }
        String filespell=phim.getSubSpell();

        try {
            listspell= new ReadFileSpell().docFile(filespell);
        } catch (IOException e) {
            e.printStackTrace();
        }
        final HTMLEditor htmlEditor = new HTMLEditor();
        final WebEngine webEngine = web.getEngine();



        list.setCellFactory(lv ->{ListCell<Readfile.Line> cell = new ListCell<Readfile.Line>() {
            private ImageView imageView = new ImageView();

            @Override
            public void updateItem(Readfile.Line name, boolean empty) {
                super.updateItem(name, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    imageView.setImage(IMAGE_RUBY);
                    imageView.setFitWidth(50);
                    imageView.setFitHeight(50);
                    Label label = new Label();
                    label.setText(name.getText());
                    label.setFont(new Font("Arial", 12));
                    label.setMaxWidth(Double.MAX_VALUE);
                    label.setMaxHeight(Double.MAX_VALUE);
                    HBox.setHgrow(label, Priority.ALWAYS);

                    HBox hbox = new HBox();
                    hbox.getChildren().addAll(label,imageView);
                    setGraphic(hbox);
                }
            }

        };
                cell.setOnMouseClicked(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent click) {

                        if (click.getClickCount() == 2) {
                            HBox hBox=(HBox)cell.getGraphic();
                            ImageView imageView =(ImageView)hBox.getChildren().get(1);
                            imageView.setImage(IMAGE);

                            Readfile.Line sub=cell.getItem();
                            int index=cell.getIndex();
                            LuuCauDAO luuCauDAO=new LuuCauDAO();
                            LuuCau luuCau=new LuuCau(phim.getId(),sub.getText(),listspell.get(index),sub.getStart(),sub.getEnd(),"");
                            luuCauDAO.insertLuuCau(luuCau);
                        }
                    }
                });

            return  cell;
        });

        list.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent click) {
                    mp.pause();

                    //Use ListView's getSelected Item
                    int giay = list.getSelectionModel().getSelectedIndex();
                    mp.seek(Duration.seconds(linessub.get(giay).getStart()));
                    //use this to do whatever you want to. Open Link etc.

                    htmlEditor.setHtmlText(listspell.get(giay));
                    webEngine.loadContent(htmlEditor.getHtmlText());
                    list.getSelectionModel().clearSelection();
                    mp.play();
            }
        });
        listyeu.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent click) {

                //Use ListView's getSelected Item
                int giay = listyeu.getSelectionModel().getSelectedIndex();
                mp.seek(Duration.seconds(listyeu.getItems().get(giay).getTgdau()));
                //use this to do whatever you want to. Open Link etc.

                htmlEditor.setHtmlText(listyeu.getItems().get(giay).getNoidungspell());
                webEngine.loadContent(htmlEditor.getHtmlText());
            }
        });
        tabyeu.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
                if(tabyeu.isSelected()) {
                    String idphim=String.valueOf(phim.getId());
                    LoadListLuuCau(idphim);
                }
            }
        });



        playButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                Status status = mp.getStatus();

                if (status == Status.UNKNOWN || status == Status.HALTED) {
                    // don't do anything in these states
                    return;
                }

                if (status == Status.PAUSED
                        || status == Status.READY
                        || status == Status.STOPPED) {
                    // rewind the Phim if we're sitting at the end
                    if (atEndOfMedia) {
                        mp.seek(mp.getStartTime());
                        atEndOfMedia = false;
                    }
                    mp.play();
                } else {
                    mp.pause();
                }
            }
        });
        mp.currentTimeProperty().addListener(new InvalidationListener() {
            public void invalidated(Observable ov) {
                    Duration currentTime = mp.getCurrentTime();
                    int index=SelectList(currentTime.toSeconds());
                    if(index>-1) {
                        list.getSelectionModel().select(index);
                        htmlEditor.setHtmlText(listspell.get(index));
                        webEngine.loadContent(htmlEditor.getHtmlText());
                        System.out.println((int)currentTime.toSeconds());
                    }
                    updateValues();
                }
        });


        mp.setOnPlaying(new Runnable() {
            public void run() {
                if (stopRequested) {
                    mp.pause();
                    stopRequested = false;
                } else {
                    playButton.setText("||");
                }
            }
        });

        mp.setOnPaused(new Runnable() {
            public void run() {
                System.out.println("onPaused");
                playButton.setText(">");
            }
        });

        mp.setOnReady(new Runnable() {
            public void run() {
                duration = mp.getMedia().getDuration();
                updateValues();
            }
        });

        mp.setCycleCount(repeat ? MediaPlayer.INDEFINITE : 1);
        mp.setOnEndOfMedia(new Runnable() {
            public void run() {
                if (!repeat) {
                    playButton.setText(">");
                    stopRequested = true;
                    atEndOfMedia = true;
                }
            }
        });

//        list.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
//
//            @Override
//            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//                int giay=newValue.byteValue();
//                mp.seek(Duration.seconds(giatri.get(giay)));
//
//            }
//
//
//        });



        // Add spacer

        // Add time slider
        timeSlider.setMinWidth(50);
        timeSlider.setMaxWidth(Double.MAX_VALUE);
        timeSlider.valueProperty().addListener(new InvalidationListener() {
            public void invalidated(Observable ov) {
                if (timeSlider.isValueChanging()) {
                    // multiply duration by percentage calculated by slider position
                    mp.seek(duration.multiply(timeSlider.getValue() / 100.0));
                }
            }
        });

        // Add Play label
        playTime.setPrefWidth(130);
        playTime.setMinWidth(50);

        // Add the volume label

        // Add Volume slider
        volumeSlider.setPrefWidth(70);
        volumeSlider.setMaxWidth(Region.USE_PREF_SIZE);
        volumeSlider.setMinWidth(30);
        volumeSlider.valueProperty().addListener(new InvalidationListener() {
            public void invalidated(Observable ov) {
                if (volumeSlider.isValueChanging()) {
                    mp.setVolume(volumeSlider.getValue() / 100.0);
                }
            }
        });
    }

    protected void updateValues() {
        if (playTime != null && timeSlider != null && volumeSlider != null) {
            Platform.runLater(new Runnable() {
                public void run() {
                    Duration currentTime = mp.getCurrentTime();
                    playTime.setText(formatTime(currentTime, duration));
                    timeSlider.setDisable(duration.isUnknown());

                    if (!timeSlider.isDisabled()
                            && duration.greaterThan(Duration.ZERO)
                            && !timeSlider.isValueChanging()) {
                        timeSlider.setValue(currentTime.divide(duration).toMillis()
                                * 100.0);
                    }
                    if (!volumeSlider.isValueChanging()) {
                        volumeSlider.setValue((int) Math.round(mp.getVolume()
                                * 100));
                    }
//                    int index=SelectList(currentTime.toSeconds());
//                    if(index>-1) {
//                        list.getSelectionModel().select(index);
//                    }


                }
            });
        }
    }

    private static String formatTime(Duration elapsed, Duration duration) {
        int intElapsed = (int) Math.floor(elapsed.toSeconds());
        int elapsedHours = intElapsed / (60 * 60);
        if (elapsedHours > 0) {
            intElapsed -= elapsedHours * 60 * 60;
        }
        int elapsedMinutes = intElapsed / 60;
        int elapsedSeconds = intElapsed - elapsedHours * 60 * 60
                - elapsedMinutes * 60;

        if (duration.greaterThan(Duration.ZERO)) {
            int intDuration = (int) Math.floor(duration.toSeconds());
            int durationHours = intDuration / (60 * 60);
            if (durationHours > 0) {
                intDuration -= durationHours * 60 * 60;
            }
            int durationMinutes = intDuration / 60;
            int durationSeconds = intDuration - durationHours * 60 * 60
                    - durationMinutes * 60;
            if (durationHours > 0) {
                return String.format("%d:%02d:%02d/%d:%02d:%02d",
                        elapsedHours, elapsedMinutes, elapsedSeconds,
                        durationHours, durationMinutes, durationSeconds);
            } else {
                return String.format("%02d:%02d/%02d:%02d",
                        elapsedMinutes, elapsedSeconds, durationMinutes,
                        durationSeconds);
            }
        } else {
            if (elapsedHours > 0) {
                return String.format("%d:%02d:%02d", elapsedHours,
                        elapsedMinutes, elapsedSeconds);
            } else {
                return String.format("%02d:%02d", elapsedMinutes,
                        elapsedSeconds);
            }
        }
    }
    private int SelectList(double giay) {
        double a = (int) giay;
        for (int i = 0; i < linessub.size(); i++) {
            if ( (a <= linessub.get(i).getEnd())&&(a >= linessub.get(i).getStart())) {
                return i;
            }
        }
        return -1;
    }


}