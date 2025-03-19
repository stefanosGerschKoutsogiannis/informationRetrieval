package org.stefanosgersch.paperworld;


import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PaperViewer {

    public PaperViewer(Results results) {

        Text title = new Text(results.title());
        title.setUnderline(true);
        title.setWrappingWidth(765);
        title.setFont(new Font(22));

        Text authors = new Text("Authors: " + results.authors());
        authors.setFont(new Font(18));
        authors.setUnderline(true);

        Text year = new Text("Year of publication:  " + results.year());
        year.setFont(new Font(18));
        year.setUnderline(true);

        Text abstractHeader = new Text("Abstract: ");
        abstractHeader.setFont(new Font(20));
        abstractHeader.setUnderline(true);

        Text abstractText = new Text(results.abstractText());
        abstractText.setFont(new Font(15));

        Text fullTextHeader = new Text("Original paper");
        fullTextHeader.setFont(new Font(20));
        fullTextHeader.setUnderline(true);

        Text fullText = new Text(results.fullText());
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
        stage.setTitle(results.title());

        System.out.println(results.fullText());

        stage.show();
    }

}
