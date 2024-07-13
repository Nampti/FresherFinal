package com.lthdv.authservice.repository.table;

import com.lthdv.authservice.model.Fresher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public interface FresherRepositoryJPA extends JpaRepository<Fresher, Long> {
}
