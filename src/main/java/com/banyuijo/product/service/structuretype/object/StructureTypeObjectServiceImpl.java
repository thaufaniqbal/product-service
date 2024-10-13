package com.banyuijo.product.service.structuretype.object;

import com.banyuijo.product.dto.structuretype.object.StructureTypeObjectOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class StructureTypeObjectServiceImpl implements StructureTypeObjectService {
    @Override
    public List<StructureTypeObjectOutput> getStructureTypeObjects() {
        return null;
    }
}
