package org.example.repository;

import org.example.entity.ReservationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationDao extends JpaRepository<ReservationEntity, Long> {

    Page<ReservationEntity> findAllByUserId(Pageable pageable, Long userId);
}
