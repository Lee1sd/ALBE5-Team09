package domain.board.exception;

import global.exception.ExceptionCode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("BoardExceptionCode enum 테스트")
class BoardExceptionCodeTest {

    @Test
    @DisplayName("BOARD_NOT_FOUND는 올바른 코드와 메시지를 반환한다")
    void testBoardNotFound() {
        // given
        BoardExceptionCode code = BoardExceptionCode.BOARD_NOT_FOUND;

        // then
        assertEquals("BOARD_NOT_FOUND", code.getCode());
        assertEquals("해당 게시글을 찾을 수 없습니다.", code.getMessage());
    }

    @Test
    @DisplayName("BOARD_ACCESS_DENIED는 올바른 코드와 메시지를 반환한다")
    void testBoardAccessDenied() {
        // given
        BoardExceptionCode code = BoardExceptionCode.BOARD_ACCESS_DENIED;

        // then
        assertEquals("BOARD_ACCESS_DENIED", code.getCode());
        assertEquals("게시글 접근 권한이 없습니다.", code.getMessage());
    }

    @Test
    @DisplayName("BOARD_ALREADY_DELETED는 올바른 코드와 메시지를 반환한다")
    void testBoardAlreadyDeleted() {
        // given
        BoardExceptionCode code = BoardExceptionCode.BOARD_ALREADY_DELETED;

        // then
        assertEquals("BOARD_ALREADY_DELETED", code.getCode());
        assertEquals("이미 삭제된 게시글입니다.", code.getMessage());
    }

    @ParameterizedTest
    @EnumSource(BoardExceptionCode.class)
    @DisplayName("모든 BoardExceptionCode는 ExceptionCode 인터페이스를 구현한다")
    void testAllCodesImplementExceptionCode(BoardExceptionCode code) {
        // then
        assertInstanceOf(ExceptionCode.class, code);
    }

    @ParameterizedTest
    @EnumSource(BoardExceptionCode.class)
    @DisplayName("모든 BoardExceptionCode의 getCode()는 null이 아니고 비어있지 않다")
    void testGetCodeIsNotNullOrEmpty(BoardExceptionCode code) {
        // when
        String result = code.getCode();

        // then
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @ParameterizedTest
    @EnumSource(BoardExceptionCode.class)
    @DisplayName("모든 BoardExceptionCode의 getMessage()는 null이 아니고 비어있지 않다")
    void testGetMessageIsNotNullOrEmpty(BoardExceptionCode code) {
        // when
        String result = code.getMessage();

        // then
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @ParameterizedTest
    @EnumSource(BoardExceptionCode.class)
    @DisplayName("모든 BoardExceptionCode의 getCode()는 enum 상수명과 일치한다")
    void testGetCodeMatchesEnumName(BoardExceptionCode code) {
        // when
        String result = code.getCode();

        // then
        assertEquals(code.name(), result);
    }

    @Test
    @DisplayName("BoardExceptionCode는 3개의 상수를 갖는다")
    void testBoardExceptionCodeCount() {
        // when
        BoardExceptionCode[] values = BoardExceptionCode.values();

        // then
        assertEquals(3, values.length);
    }

    @Test
    @DisplayName("valueOf()로 enum 상수를 조회할 수 있다")
    void testValueOf() {
        // when
        BoardExceptionCode notFound = BoardExceptionCode.valueOf("BOARD_NOT_FOUND");
        BoardExceptionCode accessDenied = BoardExceptionCode.valueOf("BOARD_ACCESS_DENIED");
        BoardExceptionCode alreadyDeleted = BoardExceptionCode.valueOf("BOARD_ALREADY_DELETED");

        // then
        assertNotNull(notFound);
        assertNotNull(accessDenied);
        assertNotNull(alreadyDeleted);
        assertEquals(BoardExceptionCode.BOARD_NOT_FOUND, notFound);
        assertEquals(BoardExceptionCode.BOARD_ACCESS_DENIED, accessDenied);
        assertEquals(BoardExceptionCode.BOARD_ALREADY_DELETED, alreadyDeleted);
    }

    @Test
    @DisplayName("존재하지 않는 이름으로 valueOf() 호출 시 예외가 발생한다")
    void testValueOfWithInvalidName() {
        // then
        assertThrows(IllegalArgumentException.class, () ->
            BoardExceptionCode.valueOf("NONEXISTENT")
        );
    }

    @Test
    @DisplayName("서로 다른 BoardExceptionCode는 다른 메시지를 갖는다")
    void testDifferentCodesHaveDifferentMessages() {
        // given
        BoardExceptionCode notFound = BoardExceptionCode.BOARD_NOT_FOUND;
        BoardExceptionCode accessDenied = BoardExceptionCode.BOARD_ACCESS_DENIED;
        BoardExceptionCode alreadyDeleted = BoardExceptionCode.BOARD_ALREADY_DELETED;

        // then
        assertNotEquals(notFound.getMessage(), accessDenied.getMessage());
        assertNotEquals(notFound.getMessage(), alreadyDeleted.getMessage());
        assertNotEquals(accessDenied.getMessage(), alreadyDeleted.getMessage());
    }
}