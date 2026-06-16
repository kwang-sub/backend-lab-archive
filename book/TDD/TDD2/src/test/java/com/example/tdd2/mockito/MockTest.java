package com.example.tdd2.mockito;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class MockTest {
    @Mock
    private GameNumGen genMock;
    @Test
    void mockTest() {
//        GameNumGen genMock = mock(GameNumGen.class);
//      generate()메서드 파라미터로 GameLevel.EASY을 사용
        genMock.generate(GameLevel.EASY);

        ArgumentCaptor<GameLevel> captor = ArgumentCaptor.forClass(GameLevel.class);
//         captor.capture()를 이용해 해당 메서드의 파라미터를 캡쳐한다.
        then(genMock).should().generate(captor.capture());
//        captor.getValue()를 이용해 캡처된 파라미터 값을 가져와 검증하는 코드
        assertThat(captor.getValue()).isEqualTo(GameLevel.EASY);
    }

    @Test
    void mockThrowTest() {
        GameNumGen genMock = mock(GameNumGen.class);
        given(genMock.generate(null)).willThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> {
            genMock.generate(null);
        });
    }

    @Test
    void voidMethodWillThrowTest() {
        List<String> mockList = mock(List.class);
        willThrow(UnsupportedOperationException.class)
                .given(mockList)
                .clear();
        assertThrows(
                UnsupportedOperationException.class,
                () -> mockList.clear()
        );
    }
}
