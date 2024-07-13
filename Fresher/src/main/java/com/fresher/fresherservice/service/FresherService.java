package com.lthdv.authservice.service;

import com.lthdv.authservice.model.Fresher;
import com.lthdv.authservice.vo.dto.SearchFresherDTO;

import java.util.List;

public interface FresherService {

    public Object getAllFreshers(SearchFresherDTO searchFresherDTO);

    public Fresher getFresherById(Long id);

    public Fresher saveFresher(Fresher fresher);

    public void deleteFresher(Long id);
}
