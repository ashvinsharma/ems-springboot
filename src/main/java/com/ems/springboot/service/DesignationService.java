package com.ems.springboot.service;

import com.ems.springboot.model.Designation;

import java.util.List;

public interface DesignationService {
    List<Designation> findAllDesignations();

    Designation findDesignation(String designation);
}
