package org.example.ex06.functional;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FunctionalAuditManager {
    private final int maxEntriesPerFile;

    public FunctionalAuditManager(int maxEntriesPerFile) {
        this.maxEntriesPerFile = maxEntriesPerFile;
    }

    public FileUpdate addRecord(FileContent[] files, String visitorName, LocalDateTime timeOfVisit) {
        List<FileInfo> sorted = sortByIndex(files);
        String newRecord = visitorName + ";" + timeOfVisit.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        if (sorted.isEmpty()) {
            return new FileUpdate("audit_1.txt", newRecord);
        }

        FileInfo currentFile = sorted.get(sorted.size() - 1);
        List<String> lines = new ArrayList<>(Arrays.asList(currentFile.file.lines));

        if (lines.size() < maxEntriesPerFile) {
            lines.add(newRecord);
            String newContent = String.join("\r\n", lines);
            return new FileUpdate(currentFile.file.fileName, newContent);
        } else {
            int newIndex = currentFile.index + 1;
            String newName = String.format("audit_%d.txt", newIndex);
            return new FileUpdate(newName, newRecord);
        }
    }

    private List<FileInfo> sortByIndex(FileContent[] files) {
        return Arrays.stream(files)
                .map(file -> new FileInfo(getIndex(file.fileName), file))
                .sorted(Comparator.comparingInt(f -> f.index))
                .collect(Collectors.toList());
    }

    private int getIndex(String fileName) {
        // File name example: audit_1.txt
        String name = fileName.substring(0, fileName.lastIndexOf('.'));
        return Integer.parseInt(name.split("_")[1]);
    }

    private static class FileInfo {
        final int index;
        final FileContent file;

        FileInfo(int index, FileContent file) {
            this.index = index;
            this.file = file;
        }
    }
}
