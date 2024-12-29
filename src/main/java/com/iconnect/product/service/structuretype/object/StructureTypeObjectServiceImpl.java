package com.iconnect.product.service.structuretype.object;

import com.iconnect.product.dto.structuretype.object.StructureTypeObjectOutput;
import com.iconnect.product.enums.ObjectTypeStructure;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class StructureTypeObjectServiceImpl implements StructureTypeObjectService {
    @Override
    public List<StructureTypeObjectOutput> getStructureTypeObjects() {
        List<ObjectTypeStructure> getEnums = List.of(ObjectTypeStructure.values());
        return build(getEnums);
    }

    private List<StructureTypeObjectOutput> build(List<ObjectTypeStructure> enums) {
        return enums.stream()
                .map(this::convertToOutput)
                .collect(toList());
    }

    private StructureTypeObjectOutput convertToOutput(ObjectTypeStructure input) {
        return new StructureTypeObjectOutput(
                input.getCode(),
                input.getValue(),
                input.getLowerBond(),
                input.getUpperBond()
        );
    }
}
