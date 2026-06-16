package org.example.ex06.mock;

import java.util.List;

interface FileSystem {
    String[] getFiles(String directoryName);
    void writeAllText(String filePath, String content);
    List<String> readAllLines(String filePath);
}
