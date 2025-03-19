package org.stefanosgersch.paperworld;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.apache.lucene.queryparser.classic.ParseException;


import java.io.IOException;
import java.util.List;


/**
 * This class is responsible for the actions taken in the results page
 */
public class ResultsPageController {

    @FXML
    private VBox resultsVBox;

    @FXML
    private TextField userQueryInput;

    @FXML
    private Button previousButton;

    @FXML
    private Button nextButton;

    @FXML
    private ComboBox<String> sortBy;

    @FXML
    private ComboBox<String> searchHistoryBox;




    // integer representing the page number
    private int pageId;
    // list containing Results objects of a query
    private List<Results> queryResults;

    // Class that is passed into Searcher.search(), determines the sorting
    private SearchOptions searchOptions;

    // search history list
    private ObservableList<String> searchHistory = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        sortBy.getItems().addAll("Relevance", "Year");

        searchOptions = new SearchOptions();

        sortBy.setOnAction(this::sortByChanged);


        if (pageId == 0) {
            previousButton.setVisible(false);
        }

    }

    @FXML
    public void sortByChanged(ActionEvent e) {
        String newValue = sortBy.getValue();
        if (newValue.equals("Relevance")) {
            searchOptions.setSort("relevance");

        } else {
            searchOptions.setSort("year");
        }

        // for verifying use
        System.out.println(searchOptions.getSort());
    }


    // as the name suggests
    @FXML
    public void showPreviousPage() {

        resultsVBox.getChildren().clear();
        showSearchResults(queryResults, --pageId);

        nextButton.setVisible(true);
        if (pageId == 0) {
            previousButton.setVisible(false);
        }
        System.out.println(pageId);
    }

    // as the name suggests
    @FXML
    public void showNextPage() {
        // clear resultsBox children
        resultsVBox.getChildren().clear();
        showSearchResults(queryResults, ++pageId);

        // for max results
        previousButton.setVisible(true);
        if (queryResults.size() - (5 * pageId) <= 5) {
            nextButton.setVisible(false);
        }
        System.out.println(pageId);
    }



    // this is stupid, but it will do the job
    @FXML
    public void searchWithThisWindow(ActionEvent e) throws IOException, ParseException {

        // get the user query from TextField
        String query = userQueryInput.getText();


        // just call search
        search(query);
    }

    // called by Landing Page when scenes change and also called by searchWithThisWindow
    @FXML
    public void search(String query) throws IOException, ParseException {

        // clear previous results
        resultsVBox.getChildren().clear();


        // the search text appears also to the search box of results page
        userQueryInput.setText(query);



        // check if in search history, if not add to list, which will later be added to searchHistoryBox
        if (!searchHistory.contains(query)) {
            searchHistory.add(query);
            searchHistoryBox.getItems().add(searchHistory.getLast());
        }


        // search
        List<Results> queryResults = Main.getMainInstance().getSearcher().search(query, searchOptions);


        // show search results
        showSearchResults(queryResults, pageId);


    }

    // as the name suggests
    public void showSearchResults(List<Results> searchResults, int pageId) {

        this.queryResults = searchResults;


        // clear previous search/page results
        resultsVBox.getChildren().clear();

        // checking if there are no results
        if (searchResults.isEmpty()) {
            Text resultsNotFound = new Text("No results matching your query where found");
            resultsNotFound.setFont(new Font(20));
            resultsVBox.getChildren().add(resultsNotFound);
            return;
        }

        // create 5 links/description results for the current page
        // add short descriptions and links on the title


        // if the results are fewer than the window size
        int window = Math.min((queryResults.size() - 5 * pageId), 5);

        if (window <  5 ) {
            nextButton.setVisible(false);
        }


        Text searchInfo = new Text("Found " + queryResults.size() + " results that are fitting your query");
        searchInfo.setFont(new Font(15));
        searchInfo.setUnderline(true);
        resultsVBox.getChildren().add(searchInfo);

        resultsVBox.getChildren().add(new Text("\n"));


        // loop the right amount of times
        for (int i = pageId * window; i < pageId * 5 + window; i++) {

            // hyperlink in the title
            Text text = new Text(queryResults.get(i).title());
            text.setFont(new Font(18));
            resultsVBox.getChildren().add(text);


            // create hyperlink
            Hyperlink hyperlink = new Hyperlink(queryResults.get(i).title());
            hyperlink.setWrapText(true);
            hyperlink.setFont(new Font(14));

            resultsVBox.getChildren().add(hyperlink);

            // if pressed, the action is performed
            openPaper(hyperlink);
        }

        // check buttons
        //nextButton.setVisible(queryResults.size() > 5);
        if (pageId == 0) {
            previousButton.setVisible(false);
        }

    }


    // this method is responsible for opening a new window where info about the current file is displayed
    private void openPaper(Hyperlink hyperlink) {

        hyperlink.setOnAction(actionEvent -> {
            String titleText = hyperlink.getText();
            for (Results result: queryResults) {
                if (result.title().equals(titleText)) {
                    new PaperViewer(result);
                    break;
                }
            }
        });
    }


}
