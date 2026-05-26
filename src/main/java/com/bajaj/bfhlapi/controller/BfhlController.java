package com.bajaj.bfhlapi.controller;

import com.bajaj.bfhlapi.dto.RequestDTO;
import com.bajaj.bfhlapi.dto.ResponseDTO;
import com.bajaj.bfhlapi.service.BfhlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/bfhl")
public class BfhlController {

    @Autowired
    private BfhlService bfhlService;

    // Health check endpoint - GET /bfhl
    @GetMapping
    public ResponseEntity<Map<String, Integer>> healthCheck() {
        System.out.println("GET /bfhl called - health check");
        Map<String, Integer> response = new HashMap<>();
        response.put("operation_code", 1);
        return ResponseEntity.ok(response);
    }

    // Main endpoint - POST /bfhl
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
