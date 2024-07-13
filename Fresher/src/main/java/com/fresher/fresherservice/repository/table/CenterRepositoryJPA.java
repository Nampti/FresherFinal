package com.lthdv.authservice.repository.table;

import com.lthdv.authservice.model.Center;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CenterRepositoryJPA extends JpaRepository<Center, Long> {
}
