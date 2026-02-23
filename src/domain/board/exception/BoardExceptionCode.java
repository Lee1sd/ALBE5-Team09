package domain.board.exception;

import global.exception.ExceptionCode;

public enum BoardExceptionCode implements ExceptionCode {
    BOARD_NOT_FOUND("해당 게시글을 찾을 수 없습니다."),
    BOARD_ACCESS_DENIED("게시글 접근 권한이 없습니다."),
    BOARD_ALREADY_DELETED("이미 삭제된 게시글입니다.");

    private final String message;

    BoardExceptionCode(String message) {
        this.message = message;
    }

    @Override
    public String getCode() {
        return this.name();
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
