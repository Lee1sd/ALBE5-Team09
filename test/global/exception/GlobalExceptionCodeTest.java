package global.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("GlobalExceptionCode enum 테스트")
class GlobalExceptionCodeTest {

    @Test
    @DisplayName("SYSTEM_ERROR는 올바른 코드와 메시지를 반환한다")
    void testSystemError() {
        // given
        GlobalExceptionCode code = GlobalExceptionCode.SYSTEM_ERROR;

        // then
        assertEquals("SYSTEM_ERROR", code.getCode());
        assertEquals("시스템 내부 오류가 발생했습니다.", code.getMessage());
    }

    @Test
    @DisplayName("INVALID_INPUT은 올바른 코드와 메시지를 반환한다")
    void testInvalidInput() {
        // given
        GlobalExceptionCode code = GlobalExceptionCode.INVALID_INPUT;

        // then
        assertEquals("INVALID_INPUT", code.getCode());
        assertEquals("잘못된 입력값이 존재합니다.", code.getMessage());
    }

    @ParameterizedTest
    @EnumSource(GlobalExceptionCode.class)
    @DisplayName("모든 GlobalExceptionCode는 ExceptionCode 인터페이스를 구현한다")
    void testAllCodesImplementExceptionCode(GlobalExceptionCode code) {
        // then
        assertInstanceOf(ExceptionCode.class, code);
    }

    @ParameterizedTest
    @EnumSource(GlobalExceptionCode.class)
    @DisplayName("모든 GlobalExceptionCode의 getCode()는 null이 아니고 비어있지 않다")
    void testGetCodeIsNotNullOrEmpty(GlobalExceptionCode code) {
        // when
        String result = code.getCode();

        // then
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @ParameterizedTest
    @EnumSource(GlobalExceptionCode.class)
    @DisplayName("모든 GlobalExceptionCode의 getMessage()는 null이 아니고 비어있지 않다")
    void testGetMessageIsNotNullOrEmpty(GlobalExceptionCode code) {
        // when
        String result = code.getMessage();

        // then
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @ParameterizedTest
    @EnumSource(GlobalExceptionCode.class)
    @DisplayName("모든 GlobalExceptionCode의 getCode()는 enum 상수명과 일치한다")
    void testGetCodeMatchesEnumName(GlobalExceptionCode code) {
        // when
        String result = code.getCode();

        // then
        assertEquals(code.name(), result);
    }

    @Test
    @DisplayName("GlobalExceptionCode는 2개의 상수를 갖는다")
    void testGlobalExceptionCodeCount() {
        // when
        GlobalExceptionCode[] values = GlobalExceptionCode.values();

        // then
        assertEquals(2, values.length);
    }

    @Test
    @DisplayName("valueOf()로 enum 상수를 조회할 수 있다")
    void testValueOf() {
        // when
        GlobalExceptionCode systemError = GlobalExceptionCode.valueOf("SYSTEM_ERROR");
        GlobalExceptionCode invalidInput = GlobalExceptionCode.valueOf("INVALID_INPUT");

        // then
        assertNotNull(systemError);
        assertNotNull(invalidInput);
        assertEquals(GlobalExceptionCode.SYSTEM_ERROR, systemError);
        assertEquals(GlobalExceptionCode.INVALID_INPUT, invalidInput);
    }

    @Test
    @DisplayName("존재하지 않는 이름으로 valueOf() 호출 시 예외가 발생한다")
    void testValueOfWithInvalidName() {
        // then
        assertThrows(IllegalArgumentException.class, () ->
            GlobalExceptionCode.valueOf("NONEXISTENT")
        );
    }
}