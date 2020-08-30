package br.com.kaikeventura.bdb.error;

import br.com.kaikeventura.bdb.error.exception.*;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;

@RestControllerAdvice
@RequiredArgsConstructor
public class ApiExceptionHandler {

    private static final String NO_MESSAGE_AVAILABLE = "No error message available";
    private final MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception,
            Locale locale
    ) {
        final String errorCode = exception
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .findFirst()
                .get();

        return ResponseEntity.badRequest().body(buildApiError(errorCode, locale));
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<ApiError> handleInvalidFormatException(
            Locale locale
    ) {
        return ResponseEntity.badRequest().body(buildApiError("ERROR-14", locale));
    }

    @ExceptionHandler(EmailAlreadyRegisteredException.class)
    public ResponseEntity<ApiError> handleEmailAlreadyRegisteredException(
            EmailAlreadyRegisteredException exception,
            Locale locale
    ) {
        return ResponseEntity.badRequest().body(buildApiError(exception.getErrorCode(), locale));
    }

    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<ApiError> handleEmailNotFoundException(
            EmailNotFoundException exception,
            Locale locale
    ) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(buildApiError(exception.getErrorCode(), locale));
    }

    @ExceptionHandler(IncorrectCredentialsException.class)
    public ResponseEntity<ApiError> handleIncorrectCredentialsException(
            IncorrectCredentialsException exception,
            Locale locale
    ) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(buildApiError(exception.getErrorCode(), locale));
    }

    @ExceptionHandler(TechnologyAlreadyRegisteredException.class)
    public ResponseEntity<ApiError> handleTechnologyAlreadyRegisteredException(
            TechnologyAlreadyRegisteredException exception,
            Locale locale
    ) {
        return ResponseEntity
                .badRequest().body(buildApiError(exception.getErrorCode(), locale));
    }

    @ExceptionHandler(TechnologyNotFoundException.class)
    public ResponseEntity<ApiError> handleTechnologyNotFoundException(
            TechnologyNotFoundException exception,
            Locale locale
    ) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(buildApiError(exception.getErrorCode(), locale));
    }

    @ExceptionHandler(IncorrectTechnologyTypeException.class)
    public ResponseEntity<ApiError> handleIncorrectTechnologyTypeException(
            IncorrectTechnologyTypeException exception,
            Locale locale
    ) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(buildApiError(exception.getErrorCode(), locale));
    }

    @ExceptionHandler(BigDeveloperBrazilAlreadyRegisteredException.class)
    public ResponseEntity<ApiError> handleBigDeveloperBrazilAlreadyRegisteredException(
            BigDeveloperBrazilAlreadyRegisteredException exception,
            Locale locale
    ) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(buildApiError(exception.getErrorCode(), locale));
    }

    @ExceptionHandler(InvalidBigDeveloperBrazilException.class)
    public ResponseEntity<ApiError> handleInvalidBigDeveloperBrazilException(
            InvalidBigDeveloperBrazilException exception,
            Locale locale
    ) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(buildApiError(exception.getErrorCode(), locale));
    }

    @ExceptionHandler(BigDebugAlreadyRegisteredException.class)
    public ResponseEntity<ApiError> handleBigDebugAlreadyRegisteredException(
            BigDebugAlreadyRegisteredException exception,
            Locale locale
    ) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(buildApiError(exception.getErrorCode(), locale));
    }

    @ExceptionHandler(TechnologyAlreadyEliminatedException.class)
    public ResponseEntity<ApiError> handleTechnologyAlreadyEliminatedException(
            TechnologyAlreadyEliminatedException exception,
            Locale locale
    ) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(buildApiError(exception.getErrorCode(), locale));
    }

    @ExceptionHandler(TechnologyNotAvailableInThisBigDeveloperBrazilException.class)
    public ResponseEntity<ApiError> handleTechnologyNotAvailableInThisBigDeveloperBrazilException(
            TechnologyNotAvailableInThisBigDeveloperBrazilException exception,
            Locale locale
    ) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(buildApiError(exception.getErrorCode(), locale));
    }

    @ExceptionHandler(BigDebugNotFoundException.class)
    public ResponseEntity<ApiError> handleBigDebugNotFoundException(
            BigDebugNotFoundException exception,
            Locale locale
    ) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(buildApiError(exception.getErrorCode(), locale));
    }

    @ExceptionHandler(BigDebugNotAvailableException.class)
    public ResponseEntity<ApiError> handleBigDebugNotAvailableException(
            BigDebugNotAvailableException exception,
            Locale locale
    ) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(buildApiError(exception.getErrorCode(), locale));
    }

    private ApiError buildApiError(String errorCode, Locale locale, String... args) {
        String errorMessage;

        try {
            errorMessage = messageSource.getMessage(errorCode, args, locale);
        }
        catch (NoSuchMessageException e) {
            errorMessage = NO_MESSAGE_AVAILABLE;
        }

        return new ApiError(errorCode, errorMessage);
    }
}
