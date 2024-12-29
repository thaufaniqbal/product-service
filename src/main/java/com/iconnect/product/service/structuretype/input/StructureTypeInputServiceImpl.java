package com.iconnect.product.service.structuretype.input;

import com.iconnect.product.dto.structuretype.input.StructureTypeInputOutput;
import com.iconnect.product.enums.InputTypeStructure;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class StructureTypeInputServiceImpl implements StructureTypeInputService {
    @Override
    public List<StructureTypeInputOutput> getStructureTypeInputs() {
        List<InputTypeStructure> getEnums = List.of(InputTypeStructure.values());
        return build(getEnums);
    }

    private List<StructureTypeInputOutput> build(List<InputTypeStructure> enums) {
        return enums.stream()
                .map(this::convertToOutput)
                .collect(toList());
    }

    private StructureTypeInputOutput convertToOutput(InputTypeStructure input) {
        return new StructureTypeInputOutput(
                input.getCode(),
                input.getValue(),
                input.getLowerBond(),
                input.getUpperBond(),
                input.getDescription()
        );
    }
}
