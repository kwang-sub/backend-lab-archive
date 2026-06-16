package org.example.ex06.functional;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class FunctionalAuditManagerTest {

    @Test
    void a_new_file_is_created_when_the_current_file_overflows() {
        // given
        FunctionalAuditManager sut = new FunctionalAuditManager(3);
        FileContent[] files = new FileContent[] {
                new FileContent("audit_1.txt", new String[0]),
                new FileContent("audit_2.txt", new String[] {
                        "Peter;2019-04-06T16:30:00",
                        "Jane;2019-04-06T16:40:00",
                        "Jack;2019-04-06T17:00:00"
                })
        };

        // when
        FileUpdate update = sut.addRecord(files, "Alice", LocalDateTime.parse("2019-04-06T18:00:00"));

        // then
        assertThat(update.fileName).isEqualTo("audit_3.txt");
        assertThat(update.newContent).isEqualTo("Alice;2019-04-06T18:00:00");
    }

}