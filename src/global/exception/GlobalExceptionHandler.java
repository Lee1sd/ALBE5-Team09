package global.exception;

import static global.exception.GlobalExceptionCode.*;

public class GlobalExceptionHandler {

    public static final GlobalExceptionHandler INSTANCE = new GlobalExceptionHandler();

    private GlobalExceptionHandler() {}

    /**
     * 모든 예외를 중앙에서 처리하는 진입점
     */
    public ExceptionResponse handle(Throwable t) {

        if (t instanceof CustomException e) {
            return handleCustomException(e);
        }

        if (t instanceof NumberFormatException || t instanceof IllegalArgumentException) {
            return handleValidationException(t);
        }

        return handleUnexpectedException(t);
    }

    // 커스텀 비즈니스 예외 처리
    private ExceptionResponse handleCustomException(CustomException e) {
        return ExceptionResponse.from(e);
    }

    // 입력값 검증 실패(Validation) 대용 처리
    private ExceptionResponse handleValidationException(Throwable t) {
        // 스프링의 handleMethodArgumentNotValid 로직을 CLI용으로 단순화
        String message = (t.getMessage() != null) ? t.getMessage() : INVALID_INPUT.getMessage();
        return ExceptionResponse.of(INVALID_INPUT.getCode(), message);
    }

    // 예상치 못한 시스템 예외 처리
    private ExceptionResponse handleUnexpectedException(Throwable t) {
        // 로깅 로직 (순수 자바에서는 System.err 사용)
        System.err.println("[LOG] Unexpected error occurred: " + t.getMessage());
        return ExceptionResponse.from(SYSTEM_ERROR);
    }
}