package global.exception;

public enum GlobalExceptionCode implements ExceptionCode {
    SYSTEM_ERROR("시스템 내부 오류가 발생했습니다."),
    INVALID_INPUT("잘못된 입력값이 존재합니다.");

    private final String message;

    GlobalExceptionCode(String message) {
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