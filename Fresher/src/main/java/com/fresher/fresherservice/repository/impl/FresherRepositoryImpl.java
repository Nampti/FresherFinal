package com.lthdv.authservice.repository.impl;

import com.lthdv.authservice.model.Fresher;
import com.lthdv.authservice.repository.CommonDataBaseRepository;
import com.lthdv.authservice.repository.FresherRepository;
import com.lthdv.authservice.vo.dto.SearchFresherDTO;
import com.lthdv.authservice.vo.until.ResultSelectEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FresherRepositoryImpl extends CommonDataBaseRepository implements FresherRepository {

    @Override
    public ResultSelectEntity findFresherAndCount(SearchFresherDTO searchFresherDTO) {
        StringBuilder sql = new StringBuilder();
        List<Object> parrParams = new ArrayList<>();
        sql.append("SELECT ");
        sql.append(" F.ID as ID ");
        sql.append(" FROM FRESHER F ");
        sql.append(" WHERE 1 = 1 ");
        if (searchFresherDTO.getName()!= null) {
            sql.append(" AND UPPER(F.NAME) like ? ");
            parrParams.add("%" + searchFresherDTO.getName().toUpperCase() + "%");
        }
        if (searchFresherDTO.getEmail()!= null) {
            sql.append(" AND F.EMAIL = ? ");
            parrParams.add(searchFresherDTO.getEmail());
        }
        if (searchFresherDTO.getPhoneNumber()!= null) {
            sql.append(" AND F.PHONE_NUMBER = ? ");
            parrParams.add(searchFresherDTO.getPhoneNumber());
        }
        if (searchFresherDTO.getDob()!= null) {
            sql.append(" AND F.DOB = ? ");
            parrParams.add(searchFresherDTO.getDob());
        }
        if (searchFresherDTO.getStatus()!= null) {
            sql.append(" AND F.STATUS = ? ");
            parrParams.add(searchFresherDTO.getStatus());
        }
        if (searchFresherDTO.getUniversity()!= null) {
            sql.append(" AND F.UNIVERSITY = ? ");
            parrParams.add(searchFresherDTO.getUniversity());
        }
        if (searchFresherDTO.getDepartment()!= null) {
            sql.append(" AND F.DEPARTMENT = ? ");
            parrParams.add(searchFresherDTO.getDepartment());
        }
        if (searchFresherDTO.getLanguageCode()!= null) {
            sql.append(" AND F.LANGUAGE_CODE = ? ");
            parrParams.add(searchFresherDTO.getLanguageCode());
        }

        Integer start = 0;
        if (searchFresherDTO.getStartRecord() != null) {
            start = searchFresherDTO.getStartRecord();
        }
        Integer pageSize = null;
        if (searchFresherDTO.getPageSize() != null) {
            pageSize = searchFresherDTO.getPageSize();
        }
        System.out.println(sql);

        return getListDataAndCount(sql, parrParams, start, pageSize, Fresher.class);
    }
}
