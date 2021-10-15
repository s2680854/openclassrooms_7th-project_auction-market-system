package com.nnk.springboot.controllers.curvepoint;

import com.nnk.springboot.repositories.CurvePointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurvePointDeletionService {

    @Autowired
    private CurvePointRepository curvePointRepository;

    public void deleteCurvePointById(Long id) {

        curvePointRepository.deleteById(id);
    }

    public void deleteCurvePoints() {

        curvePointRepository.deleteAll();
    }
}
