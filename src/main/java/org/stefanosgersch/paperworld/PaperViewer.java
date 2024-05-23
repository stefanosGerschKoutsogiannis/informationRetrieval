package org.stefanosgersch.paperworld;


import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 * This class is responsible for the viewing of a paper when opened via a hyperlink
 */
public class PaperViewer {

    public PaperViewer(Results results) {

        Text title = new Text(results.getTitle());
        title.setUnderline(true);
        title.setWrappingWidth(765);
        title.setFont(new Font(22));

        Text authors = new Text("Authors: " + results.getAuthors());
        authors.setFont(new Font(18));
        authors.setUnderline(true);

        Text year = new Text("Year of publication:  " + results.getYear());
        year.setFont(new Font(18));
        year.setUnderline(true);

        Text abstractHeader = new Text("Abstract: ");
        abstractHeader.setFont(new Font(20));
        abstractHeader.setUnderline(true);

        Text abstractText = new Text(results.getAbstractText());
        abstractText.setFont(new Font(15));

        Text fullTextHeader = new Text("Original paper");
        fullTextHeader.setFont(new Font(20));
        fullTextHeader.setUnderline(true);

        Text fullText = new Text(results.getFullText());
        fullText.setFont(new Font(15));

        VBox paperVBox = new VBox();
        paperVBox.getChildren().add(title);
        paperVBox.getChildren().add(new Text("\n"));
        paperVBox.getChildren().add(year);
        paperVBox.getChildren().add(new Text("\n"));
        paperVBox.getChildren().add(authors);
        paperVBox.getChildren().add(new Text("\n"));
        paperVBox.getChildren().add(abstractHeader);
        paperVBox.getChildren().add(new Text("\n"));
        paperVBox.getChildren().add(abstractText);
        paperVBox.getChildren().add(new Text("\n"));
        paperVBox.getChildren().add(fullTextHeader);
        paperVBox.getChildren().add(new Text("\n"));
        paperVBox.getChildren().add(fullText);
        paperVBox.getChildren().add(new Text("\n"));

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(paperVBox);

        Stage stage = new Stage(StageStyle.UTILITY);
        stage.setScene(new Scene(scrollPane, 800, 600));
        stage.setTitle(results.getTitle());

        System.out.println(results.getFullText());

        stage.show();
    }

}
