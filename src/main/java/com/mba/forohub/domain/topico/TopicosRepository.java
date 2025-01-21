package com.mba.forohub.domain.topico;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TopicosRepository extends JpaRepository<Topico, Long>{

    @Query("SELECT t FROM Topico t WHERE t.autor.id = :id")
    Page<Topico> findAllByIdAutor(Long id, Pageable pageable);



    @Query("SELECT t FROM Topico t WHERE t.id = :id")
    Topico findById(long id);
    
} 
