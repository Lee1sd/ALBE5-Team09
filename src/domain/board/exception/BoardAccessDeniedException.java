package domain.board.exception;

import global.exception.CustomException;

public class BoardAccessDeniedException extends CustomException {
    public BoardAccessDeniedException() {
        super(BoardExceptionCode.BOARD_ACCESS_DENIED);
    }
}