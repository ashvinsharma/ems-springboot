package com.ems.springboot.dao;

import com.ems.springboot.model.Employee;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository("employeeDao")
public class EmployeeDaoImpl extends AbstractDao implements EmployeeDao {

    @Override
    public void saveEmployee(Employee employee) {
        persist(employee);
    }

    @Override
    public List<Employee> findAllEmployees() {
        CriteriaQuery<Employee> criteriaQuery = getSession().getCriteriaBuilder().createQuery(Employee.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);
        criteriaQuery.select(root);
        TypedQuery<Employee> tq = getSession().createQuery(criteriaQuery);
        return tq.getResultList();
    }

    @Override
    public void deleteEmployee(int id) {
        CriteriaBuilder cb = getSession().getCriteriaBuilder();
        CriteriaDelete<Employee> delete = cb.createCriteriaDelete(Employee.class);
        Root<Employee> root = delete.from(Employee.class);
        delete.where(cb.equal(root.get("id"), id));
        getSession().createQuery(delete).executeUpdate();
    }

    @Override
    public Employee findEmployee(String email) {
        CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("email"), email));

        Query<Employee> query = getSession().createQuery(criteriaQuery);
        return query.getSingleResult();
    }

    @Override
    public void updateEmployee(Employee employee) {
        getSession().update(employee);
    }
}
