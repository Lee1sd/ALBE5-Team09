package global.exception;

import domain.board.exception.BoardAccessDeniedException;
import domain.board.exception.BoardAlreadyDeletedException;
import domain.board.exception.BoardExceptionCode;
import domain.board.exception.BoardNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("GlobalExceptionHandler 테스트")
class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler handler;
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalErr = System.err;

    @BeforeEach
    void setUp() {
        handler = GlobalExceptionHandler.INSTANCE;
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    void tearDown() {
        System.setErr(originalErr);
    }

    @Test
    @DisplayName("GlobalExceptionHandler는 싱글톤 패턴을 사용한다")
    void testSingletonPattern() {
        // when
        GlobalExceptionHandler instance1 = GlobalExceptionHandler.INSTANCE;
        GlobalExceptionHandler instance2 = GlobalExceptionHandler.INSTANCE;

        // then
        assertSame(instance1, instance2);
    }

    @Test
    @DisplayName("CustomException 처리 시 올바른 ExceptionResponse를 반환한다")
    void testHandleCustomException() {
        // given
        CustomException exception = new CustomException(GlobalExceptionCode.SYSTEM_ERROR);

        // when
        ExceptionResponse response = handler.handle(exception);

        // then
        assertNotNull(response);
        assertEquals("SYSTEM_ERROR", response.code());
        assertEquals("시스템 내부 오류가 발생했습니다.", response.message());
    }

    @Test
    @DisplayName("BoardNotFoundException 처리 시 올바른 ExceptionResponse를 반환한다")
    void testHandleBoardNotFoundException() {
        // given
        BoardNotFoundException exception = new BoardNotFoundException();

        // when
        ExceptionResponse response = handler.handle(exception);

        // then
        assertNotNull(response);
        assertEquals("BOARD_NOT_FOUND", response.code());
        assertEquals("해당 게시글을 찾을 수 없습니다.", response.message());
    }

    @Test
    @DisplayName("BoardAccessDeniedException 처리 시 올바른 ExceptionResponse를 반환한다")
    void testHandleBoardAccessDeniedException() {
        // given
        BoardAccessDeniedException exception = new BoardAccessDeniedException();

        // when
        ExceptionResponse response = handler.handle(exception);

        // then
        assertNotNull(response);
        assertEquals("BOARD_ACCESS_DENIED", response.code());
        assertEquals("게시글 접근 권한이 없습니다.", response.message());
    }

    @Test
    @DisplayName("BoardAlreadyDeletedException 처리 시 올바른 ExceptionResponse를 반환한다")
    void testHandleBoardAlreadyDeletedException() {
        // given
        BoardAlreadyDeletedException exception = new BoardAlreadyDeletedException();

        // when
        ExceptionResponse response = handler.handle(exception);

        // then
        assertNotNull(response);
        assertEquals("BOARD_ALREADY_DELETED", response.code());
        assertEquals("이미 삭제된 게시글입니다.", response.message());
    }

    @Test
    @DisplayName("NumberFormatException 처리 시 INVALID_INPUT 응답을 반환한다")
    void testHandleNumberFormatException() {
        // given
        NumberFormatException exception = new NumberFormatException("숫자 형식이 아닙니다");

        // when
        ExceptionResponse response = handler.handle(exception);

        // then
        assertNotNull(response);
        assertEquals("INVALID_INPUT", response.code());
        assertTrue(response.message().contains("숫자 형식이 아닙니다"));
    }

    @Test
    @DisplayName("IllegalArgumentException 처리 시 INVALID_INPUT 응답을 반환한다")
    void testHandleIllegalArgumentException() {
        // given
        IllegalArgumentException exception = new IllegalArgumentException("잘못된 인자입니다");

        // when
        ExceptionResponse response = handler.handle(exception);

        // then
        assertNotNull(response);
        assertEquals("INVALID_INPUT", response.code());
        assertTrue(response.message().contains("잘못된 인자입니다"));
    }

    @Test
    @DisplayName("NumberFormatException에 메시지가 없으면 기본 메시지를 사용한다")
    void testHandleNumberFormatExceptionWithoutMessage() {
        // given
        NumberFormatException exception = new NumberFormatException();

        // when
        ExceptionResponse response = handler.handle(exception);

        // then
        assertNotNull(response);
        assertEquals("INVALID_INPUT", response.code());
        assertNotNull(response.message());
    }

    @Test
    @DisplayName("예상치 못한 예외 처리 시 SYSTEM_ERROR 응답을 반환한다")
    void testHandleUnexpectedException() {
        // given
        RuntimeException exception = new RuntimeException("예상치 못한 오류");

        // when
        ExceptionResponse response = handler.handle(exception);

        // then
        assertNotNull(response);
        assertEquals("SYSTEM_ERROR", response.code());
        assertEquals("시스템 내부 오류가 발생했습니다.", response.message());
    }

    @Test
    @DisplayName("예상치 못한 예외 처리 시 로그를 출력한다")
    void testUnexpectedExceptionLogging() {
        // given
        RuntimeException exception = new RuntimeException("예상치 못한 오류");

        // when
        handler.handle(exception);

        // then
        String errorOutput = errContent.toString();
        assertTrue(errorOutput.contains("[LOG] Unexpected error occurred"));
        assertTrue(errorOutput.contains("예상치 못한 오류"));
    }

    @Test
    @DisplayName("NullPointerException 처리 시 SYSTEM_ERROR 응답을 반환한다")
    void testHandleNullPointerException() {
        // given
        NullPointerException exception = new NullPointerException("null 참조 오류");

        // when
        ExceptionResponse response = handler.handle(exception);

        // then
        assertNotNull(response);
        assertEquals("SYSTEM_ERROR", response.code());
        assertEquals("시스템 내부 오류가 발생했습니다.", response.message());
    }

    @Test
    @DisplayName("IOException 같은 체크 예외도 처리할 수 있다")
    void testHandleCheckedException() {
        // given
        Exception exception = new Exception("체크 예외");

        // when
        ExceptionResponse response = handler.handle(exception);

        // then
        assertNotNull(response);
        assertEquals("SYSTEM_ERROR", response.code());
    }

    @Test
    @DisplayName("여러 CustomException을 연속으로 처리할 수 있다")
    void testHandleMultipleCustomExceptions() {
        // given
        CustomException exception1 = new CustomException(GlobalExceptionCode.SYSTEM_ERROR);
        CustomException exception2 = new CustomException(GlobalExceptionCode.INVALID_INPUT);

        // when
        ExceptionResponse response1 = handler.handle(exception1);
        ExceptionResponse response2 = handler.handle(exception2);

        // then
        assertEquals("SYSTEM_ERROR", response1.code());
        assertEquals("INVALID_INPUT", response2.code());
        assertNotEquals(response1.code(), response2.code());
    }

    @Test
    @DisplayName("handle 메서드는 null이 아닌 ExceptionResponse를 항상 반환한다")
    void testHandleNeverReturnsNull() {
        // given
        Throwable[] exceptions = {
            new CustomException(GlobalExceptionCode.SYSTEM_ERROR),
            new NumberFormatException(),
            new IllegalArgumentException(),
            new RuntimeException(),
            new Exception()
        };

        // when & then
        for (Throwable exception : exceptions) {
            ExceptionResponse response = handler.handle(exception);
            assertNotNull(response, "ExceptionResponse는 null이 아니어야 한다");
            assertNotNull(response.code(), "code는 null이 아니어야 한다");
            assertNotNull(response.message(), "message는 null이 아니어야 한다");
        }
    }

    @Test
    @DisplayName("같은 예외를 두 번 처리해도 일관된 결과를 반환한다")
    void testConsistentHandling() {
        // given
        CustomException exception = new CustomException(GlobalExceptionCode.SYSTEM_ERROR);

        // when
        ExceptionResponse response1 = handler.handle(exception);
        ExceptionResponse response2 = handler.handle(exception);

        // then
        assertEquals(response1.code(), response2.code());
        assertEquals(response1.message(), response2.message());
    }

    @Test
    @DisplayName("ValidationException으로 처리되는 예외의 메시지는 원본 메시지를 포함한다")
    void testValidationExceptionMessagePreservation() {
        // given
        String originalMessage = "특정 검증 오류 메시지";
        IllegalArgumentException exception = new IllegalArgumentException(originalMessage);

        // when
        ExceptionResponse response = handler.handle(exception);

        // then
        assertTrue(response.message().contains(originalMessage));
    }

    @Test
    @DisplayName("서로 다른 타입의 Board 예외들을 구별하여 처리한다")
    void testDifferentBoardExceptionHandling() {
        // given
        BoardNotFoundException notFound = new BoardNotFoundException();
        BoardAccessDeniedException accessDenied = new BoardAccessDeniedException();
        BoardAlreadyDeletedException alreadyDeleted = new BoardAlreadyDeletedException();

        // when
        ExceptionResponse response1 = handler.handle(notFound);
        ExceptionResponse response2 = handler.handle(accessDenied);
        ExceptionResponse response3 = handler.handle(alreadyDeleted);

        // then
        assertEquals("BOARD_NOT_FOUND", response1.code());
        assertEquals("BOARD_ACCESS_DENIED", response2.code());
        assertEquals("BOARD_ALREADY_DELETED", response3.code());

        assertNotEquals(response1.code(), response2.code());
        assertNotEquals(response1.code(), response3.code());
        assertNotEquals(response2.code(), response3.code());
    }
}