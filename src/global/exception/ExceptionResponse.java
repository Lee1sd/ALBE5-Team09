package global.exception;

public record ExceptionResponse(
        String code,
        String message
) {
    public static ExceptionResponse from(CustomException e) {
        return new ExceptionResponse(e.getCode().getCode(), e.getMessage());
    }

    public static ExceptionResponse from(ExceptionCode code) {
        return new ExceptionResponse(code.getCode(), code.getMessage());
    }

    public static ExceptionResponse of(String code, String message) {
        return new ExceptionResponse(code, message);
    }

    public void render() {
        System.err.println("\n" + "=".repeat(40));
        System.err.printf(" [ERROR] %s\n", code);
        System.err.printf(" [MESSAGE] %s\n", message);
        System.err.println("=".repeat(40) + "\n");
    }
}