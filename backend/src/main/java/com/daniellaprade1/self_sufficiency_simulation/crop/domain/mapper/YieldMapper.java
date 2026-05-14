package com.daniellaprade1.self_sufficiency_simulation.crop.domain.mapper;

import com.daniellaprade1.self_sufficiency_simulation.crop.domain.dto.YieldDTO;
import com.daniellaprade1.self_sufficiency_simulation.crop.domain.entity.Yield;
import org.springframework.stereotype.Component;

@Component
public class YieldMapper {

    public Yield toEmbeddable(YieldDTO dto) {
        Yield yield = new Yield();
        yield.setMinGrams(dto.minGrams());
        yield.setMaxGrams(dto.maxGrams());
        return yield;
    }
}
