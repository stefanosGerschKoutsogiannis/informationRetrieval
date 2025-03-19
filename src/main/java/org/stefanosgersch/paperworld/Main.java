package org.stefanosgersch.paperworld;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;



public class Main extends Application {

    private static Main mainInstance;
    private static Searcher searcher;
    private static Indexer indexer;

    public Main() throws IOException {
        indexer = new Indexer(ApplicationConstants.INDEX_PATH, ApplicationConstants.CORPUS_PATH);
        indexer.index();
        searcher = new Searcher(ApplicationConstants.INDEX_PATH);
    }

    @Override
    public void start(Stage stage) throws IOException {
        URL resource = getClass().getResource("LandingPage.fxml");
        FXMLLoader loader =  new FXMLLoader(resource);
        Parent root = loader.load();
        stage.setTitle(ApplicationConstants.WINDOW_TITLE);
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

    public static void main(String[] args) throws IOException {
        getMainInstance();
        launch(args);
    }

    public static Main getMainInstance() throws IOException {
        if (mainInstance == null) {
            mainInstance = new Main();
        }
        return mainInstance;
    }

    public Searcher getSearcher() {
        return searcher;
    }

}
