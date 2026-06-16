package com.example.tdd2.mockito;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AnyMatcherTest {
    @Test
    void anyMatchTest() {
        GameNumGen genMock = mock(GameNumGen.class);
//        when(genMock.generate(any())).thenReturn("456");
        given(genMock.generate(any())).willReturn("456");
        String num = genMock.generate(GameLevel.NORMAL);
        assertEquals("456", num );
    }
}
