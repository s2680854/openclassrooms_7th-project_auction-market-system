package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.CurvePoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CurvePointRepository extends JpaRepository<CurvePoint, Long> {

    @Query("SELECT u FROM CurvePoint u WHERE u.curveId = ?1")
    CurvePoint findByCurveId(Integer id);
}
