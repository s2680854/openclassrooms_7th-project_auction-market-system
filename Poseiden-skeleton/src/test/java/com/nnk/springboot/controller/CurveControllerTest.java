package com.nnk.springboot.controller;

import com.nnk.springboot.controllers.CurveController;
import com.nnk.springboot.controllers.UserController;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.repositories.UserRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
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
    public void shouldGetCurveList() throws Exception {

        mockMvc.perform(get("/curvePoint/list"))
                .andExpect(status().is(401))
                .andExpect(view().name("curvePoint/list"));
    }

    @Test
    public void shouldAddCurve() throws Exception {

        mockMvc.perform(get("/curvePoint/add"))
                .andExpect(status().is(401))
                .andExpect(view().name("curvePoint/add"));
    }

    @Test
    public void shouldShowUpdateCurveForm() throws Exception {

        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setCurveId(1);
        curvePoint.setTerm(0.5d);
        curvePoint.setValue(10d);
        curvePointRepository.save(curvePoint);
        Long id = curvePointRepository.findByCurveId(1).getId();

        mockMvc.perform(get("/curvePoint/update/" + id))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/update"));
    }

    @Test
    public void shouldDeleteCurve() throws Exception {

        Long id = curvePointRepository.findByCurveId(1).getId();

        mockMvc.perform(delete("/curvePoint/delete/" + id))
                .andExpect(status().is(401))
                .andExpect(view().name("redirect:/curvePoint/list"));
    }

    @Test
    public void shouldDeleteCurveList() throws Exception {

        mockMvc.perform(delete("/curvePoint/delete"))
                .andExpect(status().is(401))
                .andExpect(view().name("redirect:/curvePoint/list"));
    }
}
