package com.alkemy.ong.repository;

import com.alkemy.ong.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICommentsRepository extends JpaRepository<Comment, Long> {


    List<Comment> findByOrderByCreatedAtAsc();

    @Query("SELECT c FROM Comment c WHERE news.id = ?1")
    List<Comment> findCommentsByNewsId(Long id);


}
