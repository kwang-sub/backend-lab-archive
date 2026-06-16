package org.example.ex06.base;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class AuditManager {
    private final int maxEntriesPerFile;
    private final String directoryName;

    public AuditManager(int maxEntriesPerFile, String directoryName) {
        this.maxEntriesPerFile = maxEntriesPerFile;
        this.directoryName = directoryName;
    }

    public void addRecord(String visitorName, LocalDateTime timeOfVisit) throws IOException {
        Path directory = Paths.get(directoryName);
        List<FileInfo> sorted = sortByIndex(directory);

        String newRecord = visitorName + ";" + timeOfVisit.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        if (sorted.isEmpty()) {
            Path newFile = directory.resolve("audit_1.txt");
            Files.writeString(newFile, newRecord);
            return;
        }

        FileInfo currentFile = sorted.get(sorted.size() - 1);
        List<String> lines = Files.readAllLines(Paths.get(currentFile.path));

        if (lines.size() < maxEntriesPerFile) {
            lines.add(newRecord);
            String newContent = String.join("\r\n", lines);
            Files.writeString(Paths.get(currentFile.path), newContent);
        } else {
            int newIndex = currentFile.index + 1;
            String newName = String.format("audit_%d.txt", newIndex);
            Path newFile = directory.resolve(newName);
            Files.writeString(newFile, newRecord);
        }
    }

    private List<FileInfo> sortByIndex(Path directory) throws IOException {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directory, "audit_*.txt")) {
            return StreamSupport.stream(stream.spliterator(), false)
                    .map(path -> new FileInfo(getIndex(path), path.toString()))
                    .sorted(Comparator.comparingInt(f -> f.index))
                    .collect(Collectors.toList());
        }
    }

    private int getIndex(Path filePath) {
        // File name example: audit_1.txt
        String fileName = filePath.getFileName().toString();
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