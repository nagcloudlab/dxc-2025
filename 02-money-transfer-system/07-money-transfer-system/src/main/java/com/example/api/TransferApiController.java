package com.example.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import com.example.service.TransferService;
import com.example.dto.TransferResponse;
import com.example.dto.TransferRequest;
import com.example.exception.InsufficientFundsException;
import com.example.exception.AccountNotFoundException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController("apiTransferController")
@RequestMapping("/api/v1/transfer")
public class TransferApiController {

    private final TransferService transferService;

    public TransferApiController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping(
            consumes = "application/json",
            produces = "application/json"               
    )
    public ResponseEntity<?> transfer(
        @Valid @RequestBody TransferRequest request
    ) {
        try {
            transferService.transfer(
                request.getFromAccountNum(),
                request.getToAccountNum(),
                request.getAmount()
            );
        } catch (InsufficientFundsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(buildErrorResponse("Insufficient funds", HttpStatus.BAD_REQUEST));
        } catch (AccountNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(buildErrorResponse("Account not found", HttpStatus.NOT_FOUND));
        }

        TransferResponse response = new TransferResponse("Transfer successful");
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            errors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(buildErrorResponse("Validation failed", HttpStatus.BAD_REQUEST, errors));
    }
    
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidJson(HttpMessageNotReadableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(buildErrorResponse("Invalid request format. Please check your JSON input.", HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(buildErrorResponse("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR));
    }

    private Map<String, Object> buildErrorResponse(String message, HttpStatus status) {
        return buildErrorResponse(message, status, null);
    }

    private Map<String, Object> buildErrorResponse(String message, HttpStatus status, Map<String, String> errors) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("status", status.value());
        errorResponse.put("error", status.getReasonPhrase());
        errorResponse.put("message", message);
        errorResponse.put("path", "/api/v1/transfer"); // This can be dynamically retrieved
        if (errors != null) {
            errorResponse.put("errors", errors);
        }
        return errorResponse;
    }
}
