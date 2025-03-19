package org.stefanosgersch.paperworld;

public record Results(
        String year,
        String title,
        String abstractText,
        String fullText,
        String authors) {
}
