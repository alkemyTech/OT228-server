package com.alkemy.ong.repository;

import com.alkemy.ong.dto.SlideDto;
import com.alkemy.ong.model.Slide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SlideRepository extends JpaRepository<Slide, Long> {

     List<Slide> findByOrganizationIdOrderByOrderAsc(Long id);

    @Query(nativeQuery = true, value = "SELECT max(order_number) FROM slides s")
    Integer getMax();
}
