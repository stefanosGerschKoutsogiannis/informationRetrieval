package org.stefanosgersch.paperworld;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;

/*
 * This class is responsible for creating the right queries as specified in the report
 */
public class QueryBuilder {

    public static Query buildQuery(String query) throws ParseException {

        // query parser can build all types of queries
        // queries with prefix: year, title, name(author), abstract,
        if (query.startsWith("year:")) {
            String actualQuery = query.substring(5);
            QueryParser qp = new QueryParser(ApplicationConstants.YEAR, new StandardAnalyzer());
            Query ret =  qp.parse(actualQuery);
            System.out.println(ret);
            return ret;
        } else if (query.startsWith("title:")) {
            String actualQuery = query.substring(6);
            QueryParser qp = new QueryParser(ApplicationConstants.TITLE, new StandardAnalyzer());
            Query ret = qp.parse(actualQuery);
            System.out.println(ret);
            return ret;
        } else if (query.startsWith("name:")) {
            String actualQuery = query.substring(5);
            QueryParser qp = new QueryParser(ApplicationConstants.AUTHORS, new StandardAnalyzer());
            Query ret = qp.parse(query);
            System.out.println(ret);
            return ret;
        } else if (query.startsWith("abstract:")) {
            String actualQuery = query.substring(9);
            QueryParser qp = new QueryParser(ApplicationConstants.ABSTRACT, new StandardAnalyzer());
            Query ret = qp.parse(actualQuery);
            System.out.println(ret);
            return ret;
        } else if (query.startsWith("text:")) {
            String actualQuery = query.substring(5);
            QueryParser qp = new QueryParser(ApplicationConstants.FULL_TEXT, new StandardAnalyzer());
            Query ret = qp.parse(actualQuery);
            System.out.println(ret);
            return ret;
        } else {
            QueryParser qp = new QueryParser(ApplicationConstants.FULL_TEXT, new StandardAnalyzer());
            Query ret = qp.parse(query);
            System.out.println(ret);
            return ret;
        }

    }
}
