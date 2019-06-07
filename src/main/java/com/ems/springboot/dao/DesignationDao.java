package com.ems.springboot.dao;

import com.ems.springboot.model.Designation;

import java.util.List;

public interface DesignationDao {
    List<Designation> findAllDesignations();

    Designation findDesignation(String designation);
}
