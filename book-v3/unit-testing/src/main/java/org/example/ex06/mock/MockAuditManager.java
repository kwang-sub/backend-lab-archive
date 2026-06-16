package org.example.ex06.mock;

import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class MockAuditManager {
    private final int maxEntriesPerFile;
    private final String directoryName;
    private final FileSystem fileSystem;

    public MockAuditManager(
            int maxEntriesPerFile,
            String directoryName,
            FileSystem fileSystem) {
        this.maxEntriesPerFile = maxEntriesPerFile;
        this.directoryName = directoryName;
        this.fileSystem = fileSystem;
    }

    public void addRecord(String visitorName, LocalDateTime timeOfVisit) {
        String[] filePaths = fileSystem.getFiles(directoryName);
        List<FileInfo> sorted = sortByIndex(filePaths);

        String newRecord = visitorName + ";" + timeOfVisit.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        if (sorted.isEmpty()) {
            String newFile = Paths.get(directoryName, "audit_1.txt").toString();
            fileSystem.writeAllText(newFile, newRecord);
            return;
        }

        FileInfo currentFile = sorted.get(sorted.size() - 1);
        List<String> lines = fileSystem.readAllLines(currentFile.path);

        if (lines.size() < maxEntriesPerFile) {
            lines.add(newRecord);
            String newContent = String.join("\r\n", lines);
            fileSystem.writeAllText(currentFile.path, newContent);
        } else {
            int newIndex = currentFile.index + 1;
            String newName = String.format("audit_%d.txt", newIndex);
            String newFile = Paths.get(directoryName, newName).toString();
            fileSystem.writeAllText(newFile, newRecord);
        }
    }

    private List<FileInfo> sortByIndex(String[] files) {
        return Arrays.stream(files)
                .map(path -> new FileInfo(getIndex(path), path))
                .sorted(Comparator.comparingInt(f -> f.index))
                .collect(Collectors.toList());
    }

    private int getIndex(String filePath) {
        // File name example: audit_1.txt
        String fileName = Paths.get(filePath).getFileName().toString();
        String nameWithoutExtension = fileName.substring(0, fileName.lastIndexOf('.'));
        return Integer.parseInt(nameWithoutExtension.split("_")[1]);
    }

    private static class FileInfo {
        final int index;
        final String path;

        FileInfo(int index, String path) {
            this.index = index;
            this.path = path;
        }
    }
}
