module org.stefanosgersch.paperworld {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.lucene.core;
    requires org.apache.lucene.queryparser;


    opens org.stefanosgersch.paperworld to javafx.fxml;
    exports org.stefanosgersch.paperworld;

}