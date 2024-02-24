package br.gov.mg.fazenda.manager.api.server.configuration;

import br.gov.mg.fazenda.manager.api.server.exception.ApiManagerNotFoundException;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ApiManagerNotFoundException.class)
    protected ResponseEntity handleException(ApiManagerNotFoundException ex, HandlerMethod handlerMethod, HttpServletRequest request) {
        final var headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);
        return new ResponseEntity<>(createErrorBody(ex, NOT_FOUND, handlerMethod, request), headers, NOT_FOUND);
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(NON_NULL)
    public static class ErrorBody {

        @JsonFormat(shape = STRING, pattern = "dd-MM-yyyy HH:mm:ss")
        private LocalDateTime timestamp;
        private int status;
        private String error;
        private String message;
        private String path;
        private String controllerName;
        private String serviceName;

    }

    private ErrorBody createErrorBody(Exception ex, HttpStatus httpStatus, HandlerMethod handlerMethod, HttpServletRequest request) {
        final var controllerName = handlerMethod.getMethod().getDeclaringClass().getSimpleName();
        final var methodName = handlerMethod.getMethod().getName();
        return ErrorBody.builder()
                .timestamp(now())
                .status(httpStatus.value())
                .error(httpStatus.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .controllerName(controllerName)
                .serviceName(methodName)
                .build();
    }

}
