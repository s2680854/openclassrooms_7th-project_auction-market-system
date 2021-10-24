package com.nnk.springboot.controller;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc(addFilters=false)
public class CurveControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CurvePointRepository curvePointRepository;
    @Autowired
    private CurveController curveController;

    @Before("")
    public void setup() {

        curveController = new CurveController();
        mockMvc = MockMvcBuilders.standaloneSetup(curveController).build();
    }

    @Test
    public void shouldGetCurvePointList() throws Exception {

        mockMvc.perform(get("/curvePoint/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/list"));
    }

    @Test
    public void shouldAddCurvePoint() throws Exception {

        mockMvc.perform(get("/curvePoint/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/add"));
    }

    @Test
    public void shouldShowUpdateCurveForm() throws Exception {

        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setCurveId(1);
        curvePoint.setTerm(0.5d);
        curvePoint.setValue(10d);
        curvePointRepository.save(curvePoint);
        Long id = curvePointRepository.findByCurveId(1).get().getId();

        mockMvc.perform(get("/curvePoint/update/" + id))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/update"));
    }

    @Test
    public void shouldValidateCurvePoint() throws Exception {

        curvePointRepository.deleteAll();
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setCurveId(3);
        curvePoint.setTerm(0.5d);
        curvePoint.setValue(10d);
        curvePointRepository.save(curvePoint);

        curvePoint.setTerm(0.35d);
        Long id = curvePointRepository.findByCurveId(3).get().getId();
        curvePoint.setId(id);

        mockMvc.perform(post("/curvePoint/validate")
                        .param("id", curvePoint.getId().toString())
                        .param("curveId", String.valueOf(curvePoint.getCurveId()))
                        .param("term", String.valueOf(curvePoint.getTerm()))
                        .param("value", String.valueOf(curvePoint.getValue())))
                .andExpect(view().name("redirect:/curvePoint/list"));
    }

    @Test
    public void shouldUpdateCurvePoint() throws Exception {

        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setCurveId(8);
        curvePoint.setTerm(0.5d);
        curvePoint.setValue(10d);
        curvePointRepository.save(curvePoint);

        curvePoint.setTerm(0.25d);
        Long id = curvePointRepository.findByCurveId(8).get().getId();
        curvePoint.setId(id);

        mockMvc.perform(post("/curvePoint/update/" + id)
                        .param("id", curvePoint.getId().toString())
                        .param("curveId", String.valueOf(curvePoint.getCurveId()))
                        .param("term", String.valueOf(curvePoint.getTerm()))
                        .param("value", String.valueOf(curvePoint.getValue())))
                .andExpect(view().name("redirect:/curvePoint/list"));
    }

    @Test
    public void shouldDeleteCurvePoint() throws Exception {

        curvePointRepository.deleteAll();
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setCurveId(5);
        curvePoint.setTerm(0.5d);
        curvePoint.setValue(10d);
        curvePointRepository.save(curvePoint);
        Long id = curvePointRepository.findByCurveId(5).get().getId();

        mockMvc.perform(delete("/curvePoint/delete/" + id))
                .andExpect(view().name("redirect:/curvePoint/list"));
    }

    @Test
    public void shouldDeleteCurveList() throws Exception {

        curvePointRepository.deleteAll();
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setCurveId(9);
        curvePoint.setTerm(0.5d);
        curvePoint.setValue(10d);
        curvePointRepository.save(curvePoint);
        Long id = curvePointRepository.findByCurveId(9).get().getId();

        mockMvc.perform(delete("/curvePoint/delete/" + id))
                .andExpect(view().name("redirect:/curvePoint/list"));
    }
}
