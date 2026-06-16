package org.example.rules;

import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class RuleTester {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    private static File createdFolder;
    private static File createdFile;

    @Test
    public void testTemporaryFolder() throws IOException {
        createdFolder = folder.newFolder("createdFolder");
        createdFile = folder.newFile("createdFile.txt");
        assertThat(createdFolder).exists();
        assertThat(createdFile).exists();
    }

    @AfterClass
    public static void tearDown() {
        assertThat(createdFolder).doesNotExist();
        assertThat(createdFile).doesNotExist();
    }
}
