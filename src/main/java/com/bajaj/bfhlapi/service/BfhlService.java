package com.bajaj.bfhlapi.service;

import com.bajaj.bfhlapi.dto.RequestDTO;
import com.bajaj.bfhlapi.dto.ResponseDTO;

public interface BfhlService {

    ResponseDTO processData(RequestDTO requestDTO);
}
