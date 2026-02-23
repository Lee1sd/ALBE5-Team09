package domain.board.exception;

import global.exception.CustomException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("BoardAlreadyDeletedException 테스트")
class BoardAlreadyDeletedExceptionTest {

    @Test
    @DisplayName("BoardAlreadyDeletedException은 CustomException을 상속한다")
    void testExtendsCustomException() {
        // given
        BoardAlreadyDeletedException exception = new BoardAlreadyDeletedException();

        // then
        assertInstanceOf(CustomException.class, exception);
    }

    @Test
    @DisplayName("BoardAlreadyDeletedException은 RuntimeException을 상속한다")
    void testExtendsRuntimeException() {
        // given
        BoardAlreadyDeletedException exception = new BoardAlreadyDeletedException();

        // then
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    @DisplayName("BoardAlreadyDeletedException은 올바른 ExceptionCode를 갖는다")
    void testHasCorrectExceptionCode() {
        // given
        BoardAlreadyDeletedException exception = new BoardAlreadyDeletedException();

        // then
        assertEquals(BoardExceptionCode.BOARD_ALREADY_DELETED, exception.getCode());
    }

    @Test
    @DisplayName("BoardAlreadyDeletedException은 올바른 메시지를 갖는다")
    void testHasCorrectMessage() {
        // given
        BoardAlreadyDeletedException exception = new BoardAlreadyDeletedException();

        // then
        assertEquals("이미 삭제된 게시글입니다.", exception.getMessage());
    }

    @Test
    @DisplayName("BoardAlreadyDeletedException의 코드명은 BOARD_ALREADY_DELETED이다")
    void testCodeName() {
        // given
        BoardAlreadyDeletedException exception = new BoardAlreadyDeletedException();

        // then
        assertEquals("BOARD_ALREADY_DELETED", exception.getCode().getCode());
    }

    @Test
    @DisplayName("여러 BoardAlreadyDeletedException 인스턴스는 같은 코드를 공유한다")
    void testMultipleInstancesShareSameCode() {
        // given
        BoardAlreadyDeletedException exception1 = new BoardAlreadyDeletedException();
        BoardAlreadyDeletedException exception2 = new BoardAlreadyDeletedException();

        // then
        assertEquals(exception1.getCode(), exception2.getCode());
        assertEquals(exception1.getMessage(), exception2.getMessage());
    }

    @Test
    @DisplayName("BoardAlreadyDeletedException은 throw될 수 있다")
    void testCanBeThrown() {
        // when & then
        assertThrows(BoardAlreadyDeletedException.class, () -> {
            throw new BoardAlreadyDeletedException();
        });
    }

    @Test
    @DisplayName("BoardAlreadyDeletedException은 CustomException으로 캐치될 수 있다")
    void testCanBeCaughtAsCustomException() {
        // given
        boolean caught = false;

        // when
        try {
            throw new BoardAlreadyDeletedException();
        } catch (CustomException e) {
            caught = true;
            assertEquals(BoardExceptionCode.BOARD_ALREADY_DELETED, e.getCode());
        }

        // then
        assertTrue(caught);
    }

    @Test
    @DisplayName("BoardAlreadyDeletedException은 RuntimeException으로 캐치될 수 있다")
    void testCanBeCaughtAsRuntimeException() {
        // given
        boolean caught = false;

        // when
        try {
            throw new BoardAlreadyDeletedException();
        } catch (RuntimeException e) {
            caught = true;
        }

        // then
        assertTrue(caught);
    }

    @Test
    @DisplayName("BoardAlreadyDeletedException은 다른 Board 예외들과 구별된다")
    void testDistinctFromOtherBoardExceptions() {
        // given
        BoardAlreadyDeletedException alreadyDeleted = new BoardAlreadyDeletedException();
        BoardNotFoundException notFound = new BoardNotFoundException();
        BoardAccessDeniedException accessDenied = new BoardAccessDeniedException();

        // then
        assertNotEquals(alreadyDeleted.getCode(), notFound.getCode());
        assertNotEquals(alreadyDeleted.getCode(), accessDenied.getCode());
        assertNotEquals(alreadyDeleted.getMessage(), notFound.getMessage());
        assertNotEquals(alreadyDeleted.getMessage(), accessDenied.getMessage());
    }
}