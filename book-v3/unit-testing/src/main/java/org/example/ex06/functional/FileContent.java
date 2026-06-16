package org.example.ex06.functional;

class FileContent {
    public final String fileName;
    public final String[] lines;

    public FileContent(String fileName, String[] lines) {
        this.fileName = fileName;
        this.lines = lines;
    }
}
