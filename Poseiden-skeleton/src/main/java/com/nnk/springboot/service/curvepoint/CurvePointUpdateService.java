package com.nnk.springboot.service.curvepoint;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CurvePointUpdateService {

    @Autowired
    private CurvePointRepository curvePointRepository;

    public CurvePoint updateCurvePoint(CurvePoint curvePoint) {

        Optional<CurvePoint> optionalCurvePoint =
                curvePointRepository.findById(curvePoint.getId());

        if (!optionalCurvePoint.isPresent()) {

            return new CurvePoint(curvePoint.getCurveId(), curvePoint.getTerm(), curvePoint.getValue());
        }
        return curvePointRepository.save(curvePoint);
    }
}
