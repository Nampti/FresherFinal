package com.lthdv.authservice.repository;

import com.lthdv.authservice.vo.dto.SearchFresherDTO;
import com.lthdv.authservice.vo.until.ResultSelectEntity;

public interface FresherRepository {

    ResultSelectEntity findFresherAndCount(SearchFresherDTO searchFresherDTO);
}
