package domain.board.exception;

import global.exception.CustomException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("BoardNotFoundException 테스트")
class BoardNotFoundExceptionTest {

    @Test
    @DisplayName("BoardNotFoundException은 CustomException을 상속한다")
    void testExtendsCustomException() {
        // given
        BoardNotFoundException exception = new BoardNotFoundException();

        // then
        assertInstanceOf(CustomException.class, exception);
    }

    @Test
    @DisplayName("BoardNotFoundException은 RuntimeException을 상속한다")
    void testExtendsRuntimeException() {
        // given
        BoardNotFoundException exception = new BoardNotFoundException();

        // then
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    @DisplayName("BoardNotFoundException은 올바른 ExceptionCode를 갖는다")
    void testHasCorrectExceptionCode() {
        // given
        BoardNotFoundException exception = new BoardNotFoundException();

        // then
        assertEquals(BoardExceptionCode.BOARD_NOT_FOUND, exception.getCode());
    }

    @Test
    @DisplayName("BoardNotFoundException은 올바른 메시지를 갖는다")
    void testHasCorrectMessage() {
        // given
        BoardNotFoundException exception = new BoardNotFoundException();

        // then
        assertEquals("해당 게시글을 찾을 수 없습니다.", exception.getMessage());
    }

    @Test
    @DisplayName("BoardNotFoundException의 코드명은 BOARD_NOT_FOUND이다")
    void testCodeName() {
        // given
        BoardNotFoundException exception = new BoardNotFoundException();

        // then
        assertEquals("BOARD_NOT_FOUND", exception.getCode().getCode());
    }

    @Test
    @DisplayName("여러 BoardNotFoundException 인스턴스는 같은 코드를 공유한다")
    void testMultipleInstancesShareSameCode() {
        // given
        BoardNotFoundException exception1 = new BoardNotFoundException();
        BoardNotFoundException exception2 = new BoardNotFoundException();

        // then
        assertEquals(exception1.getCode(), exception2.getCode());
        assertEquals(exception1.getMessage(), exception2.getMessage());
    }

    @Test
    @DisplayName("BoardNotFoundException은 throw될 수 있다")
    void testCanBeThrown() {
        // when & then
        assertThrows(BoardNotFoundException.class, () -> {
            throw new BoardNotFoundException();
        });
    }

    @Test
    @DisplayName("BoardNotFoundException은 CustomException으로 캐치될 수 있다")
    void testCanBeCaughtAsCustomException() {
        // given
        boolean caught = false;

        // when
        try {
            throw new BoardNotFoundException();
        } catch (CustomException e) {
            caught = true;
            assertEquals(BoardExceptionCode.BOARD_NOT_FOUND, e.getCode());
        }

        // then
        assertTrue(caught);
    }

    @Test
    @DisplayName("BoardNotFoundException은 RuntimeException으로 캐치될 수 있다")
    void testCanBeCaughtAsRuntimeException() {
        // given
        boolean caught = false;

        // when
        try {
            throw new BoardNotFoundException();
        } catch (RuntimeException e) {
            caught = true;
        }

        // then
        assertTrue(caught);
    }
}