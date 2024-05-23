package org.stefanosgersch.paperworld;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;


/**
 * Through this class the application is started
 */
public class Main extends Application {

    private static Main mainInstance;
    private static Searcher searcher;
    //private static Indexer indexer;

    // should uncomment
    public Main() throws IOException {
        //indexer = new Indexer(getClass().getResource(ApplicationConstants.INDEX_PATH), ApplicationConstants.CORPUS_PATH);
        //indexer = new Indexer(ApplicationConstants.INDEX_PATH, ApplicationConstants.CORPUS_PATH);
        //indexer.index();
        searcher = new Searcher(ApplicationConstants.INDEX_PATH);
    }

    @Override
    public void start(Stage stage) throws IOException {

        // need to create landing-page.fxml
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

    // singleton, creation of Main instance
    public static Main getMainInstance() throws IOException {
        if (mainInstance == null) {
            mainInstance = new Main();
        }
        return mainInstance;
    }

    public Searcher getSearcher() {
        return searcher;
    }

    /*
    public Indexer getIndexer() {
        return indexer;
    }
*/

}
