package com.example.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.TransferService;
import com.example.web.dto.TransferRequest;
import com.example.web.dto.TransferResponse;

@RestController("apiTransferController")
@RequestMapping("/api/v1/transfer")
public class TransferController {

    private TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping(
            consumes = "application/json",
            produces = "application/json"               
    )
    public ResponseEntity<?> transfer(
        @RequestBody TransferRequest request
    ) {
        transferService.transfer(
            request.getFromAccountNum(),
            request.getToAccountNum(),
            request.getAmount()
        );
        boolean b=false;
        if(b)
        throw new RuntimeException("transfer failed");
        TransferResponse response = new TransferResponse();
        response.setMessage("transfer successful");
        return ResponseEntity.ok(response);

    }
    
}
