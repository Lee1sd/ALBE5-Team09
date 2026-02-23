package global.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CustomException 클래스 테스트")
class CustomExceptionTest {

    @Test
    @DisplayName("ExceptionCode로 CustomException 생성 시 메시지가 올바르게 설정된다")
    void testConstructorWithExceptionCode() {
        // given
        ExceptionCode code = GlobalExceptionCode.SYSTEM_ERROR;

        // when
        CustomException exception = new CustomException(code);

        // then
        assertEquals(code.getMessage(), exception.getMessage());
        assertEquals(code, exception.getCode());
    }

    @Test
    @DisplayName("ExceptionCode와 원인 예외로 CustomException 생성 시 올바르게 설정된다")
    void testConstructorWithExceptionCodeAndCause() {
        // given
        ExceptionCode code = GlobalExceptionCode.SYSTEM_ERROR;
        Throwable cause = new RuntimeException("원인 예외");

        // when
        CustomException exception = new CustomException(code, cause);

        // then
        assertEquals(code.getMessage(), exception.getMessage());
        assertEquals(code, exception.getCode());
        assertEquals(cause, exception.getCause());
    }

    @Test
    @DisplayName("getCode()는 생성 시 전달된 ExceptionCode를 반환한다")
    void testGetCode() {
        // given
        ExceptionCode code = GlobalExceptionCode.INVALID_INPUT;
        CustomException exception = new CustomException(code);

        // when
        ExceptionCode result = exception.getCode();

        // then
        assertEquals(code, result);
    }

    @Test
    @DisplayName("CustomException은 RuntimeException을 상속한다")
    void testCustomExceptionIsRuntimeException() {
        // given
        CustomException exception = new CustomException(GlobalExceptionCode.SYSTEM_ERROR);

        // then
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    @DisplayName("다른 ExceptionCode로 생성된 예외는 다른 메시지를 갖는다")
    void testDifferentExceptionCodesHaveDifferentMessages() {
        // given
        CustomException systemError = new CustomException(GlobalExceptionCode.SYSTEM_ERROR);
        CustomException invalidInput = new CustomException(GlobalExceptionCode.INVALID_INPUT);

        // then
        assertNotEquals(systemError.getMessage(), invalidInput.getMessage());
        assertNotEquals(systemError.getCode(), invalidInput.getCode());
    }

    @Test
    @DisplayName("원인 예외 체인이 올바르게 유지된다")
    void testExceptionChaining() {
        // given
        RuntimeException rootCause = new RuntimeException("루트 원인");
        IllegalStateException middleCause = new IllegalStateException("중간 원인", rootCause);
        CustomException exception = new CustomException(GlobalExceptionCode.SYSTEM_ERROR, middleCause);

        // then
        assertEquals(middleCause, exception.getCause());
        assertEquals(rootCause, exception.getCause().getCause());
    }

    @Test
    @DisplayName("null이 아닌 메시지를 항상 반환한다")
    void testMessageIsNeverNull() {
        // given
        CustomException exception = new CustomException(GlobalExceptionCode.SYSTEM_ERROR);

        // then
        assertNotNull(exception.getMessage());
        assertFalse(exception.getMessage().isEmpty());
    }
}