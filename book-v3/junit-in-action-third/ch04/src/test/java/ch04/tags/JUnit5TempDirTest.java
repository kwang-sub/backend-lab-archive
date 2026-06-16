package ch04.tags;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.*;

public class JUnit5TempDirTest {

    @TempDir
    Path tempDir;

    private static Path createdFile;

    @Test
    void testTemporaryFolder() throws IOException {
        assertThat(Files.isDirectory(tempDir)).isTrue();
        createdFile = Files.createFile(tempDir.resolve("createdFile.txt"));
        assertThat(createdFile.toFile().exists()).isTrue();
    }

    @AfterAll
    static void afterAll() throws IOException {
        assertThat(createdFile.toFile().exists()).isFalse();
    }
}
