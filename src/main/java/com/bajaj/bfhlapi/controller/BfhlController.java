package com.bajaj.bfhlapi.controller;

import com.bajaj.bfhlapi.dto.RequestDTO;
import com.bajaj.bfhlapi.dto.ResponseDTO;
import com.bajaj.bfhlapi.service.BfhlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/bfhl")
public class BfhlController {

    @Autowired
    BfhlService service;

    @GetMapping
    public ResponseEntity<?> healthCheck() {
        return ResponseEntity.ok(Map.of("operation_code", 1));
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> processData(@RequestBody RequestDTO req) {
        try {
            return ResponseEntity.ok(service.processData(req));
        } catch (Exception e) {
            ResponseDTO err = new ResponseDTO();
            err.setSuccess(false);
            return ResponseEntity.internalServerError().body(err);
        }
    }
}
