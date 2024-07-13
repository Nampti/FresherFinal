package com.lthdv.authservice.service.impl;

import com.lthdv.authservice.model.Fresher;
import com.lthdv.authservice.repository.FresherRepository;
import com.lthdv.authservice.repository.table.FresherRepositoryJPA;
import com.lthdv.authservice.service.FresherService;
import com.lthdv.authservice.vo.dto.SearchFresherDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FresherServiceImpl implements FresherService {

    private final FresherRepository fresherRepository;

    private final FresherRepositoryJPA fresherRepositoryJPA;

    public Object getAllFreshers(SearchFresherDTO searchFresherDTO) {

        return fresherRepository.findFresherAndCount(searchFresherDTO);
    }

    public Fresher getFresherById(Long id) {
        return fresherRepositoryJPA.findById(id).orElse(null);
    }

    public Fresher saveFresher(Fresher fresher) {
        return fresherRepositoryJPA.save(fresher);
    }

    public void deleteFresher(Long id) {
        fresherRepositoryJPA.deleteById(id);
    }
}
