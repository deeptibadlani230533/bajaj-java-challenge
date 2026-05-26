package com.bajaj.bfhlapi.controller;

import com.bajaj.bfhlapi.dto.RequestDTO;
import com.bajaj.bfhlapi.dto.ResponseDTO;
import com.bajaj.bfhlapi.service.BfhlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bfhl")
public class BfhlController {

    @Autowired
    private BfhlService bfhlService;

    @PostMapping
    public ResponseEntity<ResponseDTO> processData(@RequestBody RequestDTO requestDTO) {
        System.out.println("POST /bfhl called");
        try {
            ResponseDTO response = bfhlService.processData(requestDTO);
            System.out.println("Returning 200 OK");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.out.println("Error processing request: " + e.getMessage());
            ResponseDTO errorResponse = new ResponseDTO();
            errorResponse.setSuccess(false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
