package global.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ExceptionResponse 레코드 테스트")
class ExceptionResponseTest {

    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalErr = System.err;

    @BeforeEach
    void setUpStreams() {
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setErr(originalErr);
    }

    @Test
    @DisplayName("생성자로 ExceptionResponse를 생성할 수 있다")
    void testConstructor() {
        // when
        ExceptionResponse response = new ExceptionResponse("TEST_CODE", "테스트 메시지");

        // then
        assertEquals("TEST_CODE", response.code());
        assertEquals("테스트 메시지", response.message());
    }

    @Test
    @DisplayName("from(CustomException)은 CustomException으로부터 ExceptionResponse를 생성한다")
    void testFromCustomException() {
        // given
        CustomException exception = new CustomException(GlobalExceptionCode.SYSTEM_ERROR);

        // when
        ExceptionResponse response = ExceptionResponse.from(exception);

        // then
        assertEquals("SYSTEM_ERROR", response.code());
        assertEquals("시스템 내부 오류가 발생했습니다.", response.message());
    }

    @Test
    @DisplayName("from(ExceptionCode)는 ExceptionCode로부터 ExceptionResponse를 생성한다")
    void testFromExceptionCode() {
        // given
        ExceptionCode code = GlobalExceptionCode.INVALID_INPUT;

        // when
        ExceptionResponse response = ExceptionResponse.from(code);

        // then
        assertEquals("INVALID_INPUT", response.code());
        assertEquals("잘못된 입력값이 존재합니다.", response.message());
    }

    @Test
    @DisplayName("of(String, String)는 코드와 메시지로 ExceptionResponse를 생성한다")
    void testOf() {
        // when
        ExceptionResponse response = ExceptionResponse.of("CUSTOM_CODE", "커스텀 메시지");

        // then
        assertEquals("CUSTOM_CODE", response.code());
        assertEquals("커스텀 메시지", response.message());
    }

    @Test
    @DisplayName("render()는 에러 정보를 System.err에 출력한다")
    void testRender() {
        // given
        ExceptionResponse response = new ExceptionResponse("ERROR_CODE", "에러 메시지");

        // when
        response.render();

        // then
        String output = errContent.toString();
        assertTrue(output.contains("ERROR_CODE"));
        assertTrue(output.contains("에러 메시지"));
        assertTrue(output.contains("[ERROR]"));
        assertTrue(output.contains("[MESSAGE]"));
        assertTrue(output.contains("=".repeat(40)));
    }

    @Test
    @DisplayName("render()는 구분선(40개의 '=')을 포함한다")
    void testRenderContainsSeparator() {
        // given
        ExceptionResponse response = new ExceptionResponse("TEST", "테스트");

        // when
        response.render();

        // then
        String output = errContent.toString();
        long separatorCount = output.lines()
            .filter(line -> line.equals("=".repeat(40)))
            .count();
        assertEquals(2, separatorCount, "시작과 끝에 구분선이 있어야 한다");
    }

    @Test
    @DisplayName("레코드의 equals()는 동일한 코드와 메시지를 갖는 객체를 같다고 판단한다")
    void testEquals() {
        // given
        ExceptionResponse response1 = new ExceptionResponse("CODE", "메시지");
        ExceptionResponse response2 = new ExceptionResponse("CODE", "메시지");
        ExceptionResponse response3 = new ExceptionResponse("CODE", "다른 메시지");

        // then
        assertEquals(response1, response2);
        assertNotEquals(response1, response3);
    }

    @Test
    @DisplayName("레코드의 hashCode()는 동일한 값에 대해 같은 해시코드를 반환한다")
    void testHashCode() {
        // given
        ExceptionResponse response1 = new ExceptionResponse("CODE", "메시지");
        ExceptionResponse response2 = new ExceptionResponse("CODE", "메시지");

        // then
        assertEquals(response1.hashCode(), response2.hashCode());
    }

    @Test
    @DisplayName("레코드의 toString()은 필드 정보를 포함한다")
    void testToString() {
        // given
        ExceptionResponse response = new ExceptionResponse("TEST_CODE", "테스트 메시지");

        // when
        String result = response.toString();

        // then
        assertTrue(result.contains("TEST_CODE"));
        assertTrue(result.contains("테스트 메시지"));
    }

    @Test
    @DisplayName("null 코드와 메시지로도 생성할 수 있다")
    void testWithNullValues() {
        // when
        ExceptionResponse response = new ExceptionResponse(null, null);

        // then
        assertNull(response.code());
        assertNull(response.message());
    }

    @Test
    @DisplayName("빈 문자열로도 ExceptionResponse를 생성할 수 있다")
    void testWithEmptyStrings() {
        // when
        ExceptionResponse response = new ExceptionResponse("", "");

        // then
        assertEquals("", response.code());
        assertEquals("", response.message());
    }

    @Test
    @DisplayName("render()는 null 코드와 메시지도 처리할 수 있다")
    void testRenderWithNullValues() {
        // given
        ExceptionResponse response = new ExceptionResponse(null, null);

        // when & then
        assertDoesNotThrow(() -> response.render());
    }

    @Test
    @DisplayName("from()으로 생성된 여러 ExceptionResponse가 서로 다른 값을 갖는다")
    void testMultipleFromCreations() {
        // given
        CustomException exception1 = new CustomException(GlobalExceptionCode.SYSTEM_ERROR);
        CustomException exception2 = new CustomException(GlobalExceptionCode.INVALID_INPUT);

        // when
        ExceptionResponse response1 = ExceptionResponse.from(exception1);
        ExceptionResponse response2 = ExceptionResponse.from(exception2);

        // then
        assertNotEquals(response1, response2);
        assertNotEquals(response1.code(), response2.code());
        assertNotEquals(response1.message(), response2.message());
    }
}