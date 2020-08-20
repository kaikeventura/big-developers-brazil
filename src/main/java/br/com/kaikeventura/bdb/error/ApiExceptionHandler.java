package br.com.kaikeventura.bdb.error;

import br.com.kaikeventura.bdb.error.exception.EmailAlreadyRegisteredException;
import br.com.kaikeventura.bdb.error.exception.EmailNotFoundException;
import br.com.kaikeventura.bdb.error.exception.IncorrectCredentialsException;
import br.com.kaikeventura.bdb.error.exception.TechnologyAlreadyRegisteredException;
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
            MethodArgumentNotValidException methodArgumentNotValidException,
            Locale locale
    ) {
        final String errorCode = methodArgumentNotValidException
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
            EmailAlreadyRegisteredException emailAlreadyRegisteredException,
            Locale locale
    ) {
        return ResponseEntity.badRequest().body(buildApiError(emailAlreadyRegisteredException.getErrorCode(), locale));
    }

    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<ApiError> handleEmailNotFoundException(
            EmailNotFoundException emailNotFoundException,
            Locale locale
    ) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(buildApiError(emailNotFoundException.getErrorCode(), locale));
    }

    @ExceptionHandler(IncorrectCredentialsException.class)
    public ResponseEntity<ApiError> handleIncorrectCredentialsException(
            IncorrectCredentialsException incorrectCredentialsException,
            Locale locale
    ) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(buildApiError(incorrectCredentialsException.getErrorCode(), locale));
    }

    @ExceptionHandler(TechnologyAlreadyRegisteredException.class)
    public ResponseEntity<ApiError> handleTechnologyAlreadyRegisteredException(
            TechnologyAlreadyRegisteredException technologyAlreadyRegisteredException,
            Locale locale
    ) {
        return ResponseEntity
                .badRequest().body(buildApiError(technologyAlreadyRegisteredException.getErrorCode(), locale));
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
