package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.CurvePoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurvePointRepository extends JpaRepository<CurvePoint, Long> {

    Optional<CurvePoint> findByCurveId(Integer id);
}
