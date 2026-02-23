package domain.board.exception;

import global.exception.CustomException;

public class BoardAlreadyDeletedException extends CustomException {
    public BoardAlreadyDeletedException() {
        super(BoardExceptionCode.BOARD_ALREADY_DELETED);
    }
}