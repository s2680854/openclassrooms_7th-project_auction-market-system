package com.nnk.springboot.service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CurvePointService {

    @Autowired
    private CurvePointRepository curvePointRepository;

    public CurvePoint getCurvePoint(Long id) {

        return curvePointRepository.getById(id);
    }

    public Collection<CurvePoint> getCurvePoints() {

        return curvePointRepository.findAll();
    }

    public CurvePoint createCurvePoint(CurvePoint curvePoint) {

        return curvePointRepository.save(curvePoint);
    }

    public Collection<CurvePoint> createCurvePoints(Collection<CurvePoint> curvePoints) {

        return curvePointRepository.saveAll(curvePoints);
    }

    public CurvePoint updateCurvePoint(CurvePoint curvePoint) {

        Optional<CurvePoint> optionalCurvePoint =
                curvePointRepository.findById(curvePoint.getId());

        if (!optionalCurvePoint.isPresent()) {

            return new CurvePoint();
        }
        return curvePointRepository.save(curvePoint);
    }

    public void deleteCurvePointById(Long id) {

        curvePointRepository.deleteById(id);
    }

    public void deleteCurvePoints() {

        curvePointRepository.deleteAll();
    }
}
