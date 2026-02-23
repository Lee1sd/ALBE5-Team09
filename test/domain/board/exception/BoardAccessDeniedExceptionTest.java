package domain.board.exception;

import global.exception.CustomException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("BoardAccessDeniedException 테스트")
class BoardAccessDeniedExceptionTest {

    @Test
    @DisplayName("BoardAccessDeniedException은 CustomException을 상속한다")
    void testExtendsCustomException() {
        // given
        BoardAccessDeniedException exception = new BoardAccessDeniedException();

        // then
        assertInstanceOf(CustomException.class, exception);
    }

    @Test
    @DisplayName("BoardAccessDeniedException은 RuntimeException을 상속한다")
    void testExtendsRuntimeException() {
        // given
        BoardAccessDeniedException exception = new BoardAccessDeniedException();

        // then
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    @DisplayName("BoardAccessDeniedException은 올바른 ExceptionCode를 갖는다")
    void testHasCorrectExceptionCode() {
        // given
        BoardAccessDeniedException exception = new BoardAccessDeniedException();

        // then
        assertEquals(BoardExceptionCode.BOARD_ACCESS_DENIED, exception.getCode());
    }

    @Test
    @DisplayName("BoardAccessDeniedException은 올바른 메시지를 갖는다")
    void testHasCorrectMessage() {
        // given
        BoardAccessDeniedException exception = new BoardAccessDeniedException();

        // then
        assertEquals("게시글 접근 권한이 없습니다.", exception.getMessage());
    }

    @Test
    @DisplayName("BoardAccessDeniedException의 코드명은 BOARD_ACCESS_DENIED이다")
    void testCodeName() {
        // given
        BoardAccessDeniedException exception = new BoardAccessDeniedException();

        // then
        assertEquals("BOARD_ACCESS_DENIED", exception.getCode().getCode());
    }

    @Test
    @DisplayName("여러 BoardAccessDeniedException 인스턴스는 같은 코드를 공유한다")
    void testMultipleInstancesShareSameCode() {
        // given
        BoardAccessDeniedException exception1 = new BoardAccessDeniedException();
        BoardAccessDeniedException exception2 = new BoardAccessDeniedException();

        // then
        assertEquals(exception1.getCode(), exception2.getCode());
        assertEquals(exception1.getMessage(), exception2.getMessage());
    }

    @Test
    @DisplayName("BoardAccessDeniedException은 throw될 수 있다")
    void testCanBeThrown() {
        // when & then
        assertThrows(BoardAccessDeniedException.class, () -> {
            throw new BoardAccessDeniedException();
        });
    }

    @Test
    @DisplayName("BoardAccessDeniedException은 CustomException으로 캐치될 수 있다")
    void testCanBeCaughtAsCustomException() {
        // given
        boolean caught = false;

        // when
        try {
            throw new BoardAccessDeniedException();
        } catch (CustomException e) {
            caught = true;
            assertEquals(BoardExceptionCode.BOARD_ACCESS_DENIED, e.getCode());
        }

        // then
        assertTrue(caught);
    }

    @Test
    @DisplayName("BoardAccessDeniedException은 RuntimeException으로 캐치될 수 있다")
    void testCanBeCaughtAsRuntimeException() {
        // given
        boolean caught = false;

        // when
        try {
            throw new BoardAccessDeniedException();
        } catch (RuntimeException e) {
            caught = true;
        }

        // then
        assertTrue(caught);
    }

    @Test
    @DisplayName("BoardAccessDeniedException과 BoardNotFoundException은 서로 다른 예외이다")
    void testDifferentFromBoardNotFoundException() {
        // given
        BoardAccessDeniedException accessDenied = new BoardAccessDeniedException();
        BoardNotFoundException notFound = new BoardNotFoundException();

        // then
        assertNotEquals(accessDenied.getCode(), notFound.getCode());
        assertNotEquals(accessDenied.getMessage(), notFound.getMessage());
    }
}