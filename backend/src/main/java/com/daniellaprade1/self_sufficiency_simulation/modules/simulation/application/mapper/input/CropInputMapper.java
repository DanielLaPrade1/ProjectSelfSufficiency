package com.daniellaprade1.self_sufficiency_simulation.modules.simulation.application.mapper.input;

import com.daniellaprade1.self_sufficiency_simulation.modules.crop.domain.entity.Variety;
import com.daniellaprade1.self_sufficiency_simulation.modules.simulation.domain.valueobject.parameters.CropInput;
import org.springframework.stereotype.Component;



@Component
public class CropInputMapper {
    public CropInput toCropInput(Variety variety, Double units) {
        return new CropInput(
                units,
                NutritionMetricMapper.toMetricValues(variety.getProfile().getNutrition()),
                YieldRangeMapper.toValueRange(variety.getProfile().getYield())
        );
    }
}
