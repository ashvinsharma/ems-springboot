package com.ems.springboot.dao;

import com.ems.springboot.model.Designation;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository("designationDao")
public class DesignationDaoImpl extends AbstractDao implements DesignationDao {
    @Override
    public List<Designation> findAllDesignations() {
        CriteriaBuilder cb = getSession().getCriteriaBuilder();
        CriteriaQuery<Designation> cq = cb.createQuery(Designation.class);
        Root<Designation> root = cq.from(Designation.class);
        TypedQuery<Designation> tq = getSession().createQuery(cq);
        return tq.getResultList();
    }

    @Override
    public Designation findDesignation(String designation) {
        CriteriaBuilder cb = getSession().getCriteriaBuilder();
        CriteriaQuery<Designation> cq = cb.createQuery(Designation.class);
        Root<Designation> root = cq.from(Designation.class);
        cq.where(cb.equal(root.get("name"), designation));
        TypedQuery<Designation> tq = getSession().createQuery(cq);
        return tq.getSingleResult();
    }
}
