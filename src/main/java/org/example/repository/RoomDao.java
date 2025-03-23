package org.example.repository;

import org.example.entity.RoomEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomDao extends JpaRepository<RoomEntity, Long> {

    Page<RoomEntity> findByAvailable(boolean available, Pageable pageable);
}
