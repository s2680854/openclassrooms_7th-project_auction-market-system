package com.nnk.springboot;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CurvePointTests {

	@Autowired
	private CurvePointRepository curvePointRepository;

	@Test
	public void curvePointTest() {
		CurvePoint curvePoint = new CurvePoint(10, 10d, 30d);

		// Save
		curvePoint = curvePointRepository.save(curvePoint);
		assertTrue(curvePoint.getCurveId() == 10);

		// Update
		curvePoint.setCurveId(20);
		curvePoint = curvePointRepository.save(curvePoint);
		assertTrue(curvePoint.getCurveId() == 20);

		// Find
		List<CurvePoint> listResult = curvePointRepository.findAll();
		assertTrue(listResult.size() > 0);

		//Find By ID
		Optional<CurvePoint> optional = curvePointRepository.findByCurveId(20);
		CurvePoint actual = new CurvePoint();
		if (optional.isPresent()) {
			actual.setId(optional.get().getId());
			actual.setCurveId(optional.get().getCurveId());
			actual.setTerm(optional.get().getTerm());
			actual.setValue(optional.get().getValue());
			actual.setAsOfDate(optional.get().getAsOfDate());
			actual.setCreationDate(optional.get().getCreationDate());
		}
		assertEquals(curvePoint, actual);

		// Delete
		Long id = curvePoint.getId();
		curvePointRepository.delete(curvePoint);
		Optional<CurvePoint> curvePointList = curvePointRepository.findById(id);
		assertFalse(curvePointList.isPresent());
	}

}
