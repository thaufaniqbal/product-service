package com.banyuijo.product.controller;

import com.banyuijo.product.dto.base.ApiResponseDTO;
import com.banyuijo.product.service.structuretype.input.StructureTypeInputService;
import com.banyuijo.product.service.structuretype.object.StructureTypeObjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/structure-type")
@CrossOrigin(methods = {RequestMethod.POST, RequestMethod.PUT, RequestMethod.GET, RequestMethod.DELETE})
public class StructureTypeController {

    private final StructureTypeInputService typeInputService;
    private final StructureTypeObjectService typeObjectService;

    @GetMapping("/input")
    public ResponseEntity<ApiResponseDTO<Object>> getStructureTypeInputs() {
        return ApiResponseDTO.toResponseEntity(HttpStatus.OK, typeInputService.getStructureTypeInputs());
    }
    @GetMapping("/object")
    public ResponseEntity<ApiResponseDTO<Object>> getStructureTypeObjects() {
        return ApiResponseDTO.toResponseEntity(HttpStatus.OK, typeObjectService.getStructureTypeObjects());
    }
}
