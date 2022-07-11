package com.alkemy.ong.repository;

import com.alkemy.ong.model.Slide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SlideRepository extends JpaRepository<Slide, Long> {

    @Query(nativeQuery = true, value = "SELECT max(order_number) FROM slides s")
    Integer getMax();
}
