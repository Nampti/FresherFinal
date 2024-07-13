package com.lthdv.authservice.service;

import com.lthdv.authservice.model.Center;

import java.util.List;
import java.util.Optional;

public interface CenterService {

    public List<Center> findAll();

    public Optional<Center> findById(Long id);

    public Center save(Center center);

    public void deleteById(Long id);
}
