package org.example.base;

import org.example.base.ParameterizedWithEnumSourceTest.Sentences;
import org.example.parametrized.WordCounter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

public class ParameterizedWithEnumSourceTest {
    private WordCounter wordCounter = new WordCounter();

    @ParameterizedTest
    @EnumSource(Sentences.class)
    void testWordsInSentence(Sentences sentences) {
        assertEquals(3, wordCounter.countWords(sentences.value()));
    }

    @ParameterizedTest
    @EnumSource(value = Sentences.class, names = {"JUNIT_IN_ACTION", "THREE_PARAMETERS"})
    void testSelectedWordsInSentence(Sentences sentences) {
        assertEquals(3, wordCounter.countWords(sentences.value()));
    }

    @ParameterizedTest
    @EnumSource(value = Sentences.class, mode = EnumSource.Mode.EXCLUDE, names = {"THREE_PARAMETERS"})
    void testExcludedWordsInSentence(Sentences sentences) {
        assertEquals(3, wordCounter.countWords(sentences.value()));
    }


    enum Sentences {
        JUNIT_IN_ACTION("JUnit in action"),
        SOME_PARAMETERS("Check some parameters"),
        THREE_PARAMETERS("Check three parameters");

        private final String sentence;

        Sentences(String sentence) {
            this.sentence = sentence;
        }

        public String value() {
            return sentence;
        }
        }

}
