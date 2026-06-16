package org.example.ex06.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class MockAuditManagerTest {

    @Mock
    private FileSystem fileSystem;
    private MockAuditManager sut;
    private static final String DIRECTORY = "audits";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void newFileIsCreatedForFirstEntry() {
        // given
        sut = new MockAuditManager(3, DIRECTORY, fileSystem);
        when(fileSystem.getFiles(DIRECTORY))
                .thenReturn(new String[0]);

        // when
        sut.addRecord(
                "Peter",
                LocalDateTime.parse("2019-04-09T13:00:00")
        );

        // then
        verify(fileSystem).writeAllText(
                Paths.get(DIRECTORY, "audit_1.txt").toString(),
                "Peter;2019-04-09T13:00:00"
        );
    }

    @Test
    void newFileIsCreatedWhenCurrentFileOverflows() {
        // given
        sut = new MockAuditManager(3, DIRECTORY, fileSystem);
        String audit1Path = Paths.get(DIRECTORY, "audit_1.txt").toString();
        String audit2Path = Paths.get(DIRECTORY, "audit_2.txt").toString();
        when(fileSystem.getFiles(DIRECTORY))
                .thenReturn(new String[]{audit1Path, audit2Path});
        when(fileSystem.readAllLines(audit2Path))
                .thenReturn(Arrays.asList(
                        "Peter;2019-04-06T16:30:00",
                        "Jane;2019-04-06T16:40:00",
                        "Jack;2019-04-06T17:00:00"
                ));

        // when
        sut.addRecord(
                "Alice",
                LocalDateTime.parse("2019-04-06T18:00:00")
        );

        // then
        verify(fileSystem).writeAllText(
                Paths.get(DIRECTORY, "audit_3.txt").toString(),
                "Alice;2019-04-06T18:00:00"
        );
    }

}