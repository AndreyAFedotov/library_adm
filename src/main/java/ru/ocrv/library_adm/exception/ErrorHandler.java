package ru.ocrv.library_adm.exception;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.ocrv.library_adm.exception.exceptions.AccessDeniedException;
import ru.ocrv.library_adm.exception.exceptions.NotFoundException;
import ru.ocrv.library_adm.exception.exceptions.ValidationException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice()
@Slf4j
public class ErrorHandler {

    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @ExceptionHandler({
            ValidationException.class,
            MethodArgumentNotValidException.class
    }
    )
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBadRequestException(final Exception e) {
        log.error("Error (Bad Request): " + e.getClass());
        return ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.name())
                .reason("Incorrectly made request.")
                .message(e.getMessage())
                .timestamp(LocalDateTime.now().format(FORMAT))
                .build();
    }

    @ExceptionHandler({
            NotFoundException.class}
    )
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundException(final NotFoundException e) {
        log.error("Error (Not Found): " + e.getClass());
        return ErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND.name())
                .reason("The required object was not found.")
                .message(e.getMessage())
                .timestamp(LocalDateTime.now().format(FORMAT))
                .build();
    }

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleOtherException(final Throwable e) {
        log.error("Error (Internal Server Error): " + e.getClass());
        return ErrorResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .reason("Other error.")
                .message(e.getMessage())
                .timestamp(LocalDateTime.now().format(FORMAT))
                .error(stToStrings(e.getStackTrace()))
                .build();
    }

    @ExceptionHandler({
            AccessDeniedException.class,
            ConstraintViolationException.class,
            DataIntegrityViolationException.class}
    )
    @ResponseStatus(code = HttpStatus.CONFLICT)
    public ErrorResponse handleConflictException(final Exception e) {
        log.error("Error (Conflict): " + e.getClass());
        return ErrorResponse.builder()
                .status(HttpStatus.CONFLICT.name())
                .reason("Integrity constraint has been violated.")
                .message(e.getMessage())
                .timestamp(LocalDateTime.now().format(FORMAT))
                .error(stToStrings(e.getStackTrace()))
                .build();
    }

    private List<String> stToStrings(StackTraceElement[] st) {
        List<String> result = new ArrayList<>();
        for (StackTraceElement ste : st) {
            result.add(ste.toString());
        }
        return result;
    }

}
