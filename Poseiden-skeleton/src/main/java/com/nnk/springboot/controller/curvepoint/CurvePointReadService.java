package com.nnk.springboot.controller.curvepoint;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CurvePointReadService {

    @Autowired
    private CurvePointRepository curvePointRepository;

    public CurvePoint getCurvePoint(Long id) {

        return curvePointRepository.getById(id);
    }

    public Collection<CurvePoint> getCurvePoints() {

        return curvePointRepository.findAll();
    }
}
