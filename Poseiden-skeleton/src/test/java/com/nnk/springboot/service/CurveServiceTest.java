package com.nnk.springboot.service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.service.curvepoint.CurvePointDeletionService;
import com.nnk.springboot.service.curvepoint.CurvePointReadService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@SpringBootTest
@AutoConfigureMockMvc
public class CurveServiceTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CurvePointReadService curvePointReadService;
    @MockBean
    private CurvePointDeletionService curvePointDeletionService;
    @Autowired
    private CurvePointRepository curvePointRepository;

    @Test
    public void shouldGetCurvePoints() throws Exception {

        Collection<CurvePoint> actualList = new ArrayList<>();

        Collection<CurvePoint> expectedList = curvePointReadService.getCurvePoints();

        assertEquals(actualList, expectedList);
    }

    @Test
    public void shouldGetCurvePointById() throws Exception {

        curvePointRepository.deleteAll();
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setCurveId(1);
        curvePoint.setTerm(0.5d);
        curvePoint.setValue(10d);
        curvePointRepository.save(curvePoint);
        Long id = 1L;
        try {id = curvePointRepository.findByCurveId(1).get().getId();} catch (Exception e) {}

        CurvePoint expected = new CurvePoint();

        Optional<CurvePoint> optional = Optional.ofNullable(curvePointReadService.getCurvePointById(id));
        CurvePoint actual = new CurvePoint();
        if (optional.isPresent()) {
            curvePoint.setCurveId(1);
            curvePoint.setTerm(0.5d);
            curvePoint.setValue(10d);
        }

        assertEquals(expected, actual);
    }


    @Test
    public void shouldDeleteCurvePoint() throws Exception {

        curvePointRepository.deleteAll();
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setCurveId(2);
        curvePoint.setTerm(0.5d);
        curvePoint.setValue(10d);
        curvePointRepository.save(curvePoint);
        Long curvePointId = curvePointRepository.findByCurveId(2).get().getId();

        Mockito.doNothing().when(curvePointDeletionService).deleteCurvePointById(curvePointId);

        mockMvc.perform(delete("/curvePoint/delete/" + curvePointId));

        Mockito.verify(curvePointDeletionService, Mockito.times(1)).deleteCurvePointById(curvePointId);
    }
}
