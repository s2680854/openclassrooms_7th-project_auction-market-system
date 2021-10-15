package com.nnk.springboot.controllers.curvepoint;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CurvePointCreationService {

    @Autowired
    private CurvePointRepository curvePointRepository;

    public CurvePoint createCurvePoint(CurvePoint curvePoint) {

        return curvePointRepository.save(curvePoint);
    }

    public Collection<CurvePoint> createCurvePoints(Collection<CurvePoint> curvePoints) {

        return curvePointRepository.saveAll(curvePoints);
    }
}
