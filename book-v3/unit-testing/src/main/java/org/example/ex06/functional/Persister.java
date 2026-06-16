package org.example.ex06.functional;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.StreamSupport;

class Persister {
    public FileContent[] readDirectory(String directoryName) throws IOException {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(directoryName), "audit_*.txt")) {
            return StreamSupport.stream(stream.spliterator(), false)
                    .map(path -> {
                        try {
                            return new FileContent(
                                    path.getFileName().toString(),
                                    Files.readAllLines(path).toArray(new String[0])
                            );
                        } catch (IOException e) {
                            throw new UncheckedIOException(e);
                        }
                    })
                    .toArray(FileContent[]::new);
        }
    }

    public void applyUpdate(String directoryName, FileUpdate update) throws IOException {
        Path filePath = Paths.get(directoryName, update.fileName);
        Files.writeString(filePath, update.newContent);
    }
}