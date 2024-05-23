package org.stefanosgersch.paperworld;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.lucene.queryparser.classic.ParseException;

import java.io.IOException;

/**
 * This class is responsible for the actions taken in the LandingPage => search
 */
public class LandingPageController {

    @FXML
    private TextField userQueryInput;

    @FXML
    public void search(ActionEvent actionEvent) throws IOException, ParseException {

        // get the user input and if it is null or empty return
        String query = userQueryInput.getText();
        if (query == null || query.isEmpty()) {
            return;
        }

        // for creating a new scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/stefanosgersch/paperworld/ResultsPage.fxml"));
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(loader.load()));

        // get the results page controller and perform its search method
        ResultsPageController resultsPageController = loader.getController();
        resultsPageController.search(query);

        // show the stage
        stage.show();
    }
}
