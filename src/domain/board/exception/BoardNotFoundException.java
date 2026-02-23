package domain.board.exception;

import global.exception.CustomException;

public class BoardNotFoundException extends CustomException {
    public BoardNotFoundException() {
        super(BoardExceptionCode.BOARD_NOT_FOUND);
    }
}