package org.stefanosgersch.paperworld;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Indexer {

    private final String indexDirectory;
    private final String dataDirectory;
    private final Analyzer analyzer;

    public Indexer(String indexDirectory, String dataDirectory) {
        this.indexDirectory = indexDirectory;
        this.dataDirectory = dataDirectory;
        this.analyzer = new StandardAnalyzer();
    }

    public void index() throws IOException {
        Directory directory = FSDirectory.open(new File(indexDirectory).toPath());
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);

        File[] filesToIndex = new File(dataDirectory).listFiles();
        assert filesToIndex != null;
        for (File file: filesToIndex) {
            indexWriter.addDocument(createDocument(file));
        }

        indexWriter.close();
        directory.close();
    }

    private Document createDocument(File file) throws IOException {

        // create an empty Document object
        Document document = new Document();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] fields = line.split(","); // split according to commas
                if (fields.length >= 5) {
                    String year = fields[0];
                    String title = fields[1];
                    String abstractText = fields[2];
                    String fullText = fields[3];
                    String authors = fields[4];

                    // issue with full text

                    document.add(new TextField(ApplicationConstants.YEAR, year, Field.Store.YES));
                    document.add(new TextField(ApplicationConstants.TITLE, title, Field.Store.YES));
                    document.add(new TextField(ApplicationConstants.ABSTRACT, abstractText, Field.Store.YES));
                    document.add(new TextField(ApplicationConstants.FULL_TEXT, fullText, Field.Store.YES));
                    document.add(new TextField(ApplicationConstants.AUTHORS, authors, Field.Store.YES));
                }
            }
        }

        return document;
    }

    // run alone, to create index before trying to run application
    public static void main(String[] args) throws IOException {
        Indexer indexer = new Indexer(ApplicationConstants.INDEX_PATH, ApplicationConstants.CORPUS_PATH);
        indexer.index();
    }
}
