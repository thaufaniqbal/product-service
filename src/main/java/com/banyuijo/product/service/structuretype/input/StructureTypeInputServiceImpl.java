package com.banyuijo.product.service.structuretype.input;

import com.banyuijo.product.dto.structuretype.input.StructureTypeInputOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class StructureTypeInputServiceImpl implements StructureTypeInputService {
    @Override
    public List<StructureTypeInputOutput> getStructureTypeInputs() {
        return null;
    }
}
