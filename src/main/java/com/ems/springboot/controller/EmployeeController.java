package com.ems.springboot.controller;

import com.ems.springboot.model.Employee;
import com.ems.springboot.service.EmployeeService;
import com.ems.springboot.utils.JSONUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/employee/*")
public class EmployeeController {

    @Autowired
    private ApplicationContext context;
    private EmployeeService employeeService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity createRecord(@Valid @ModelAttribute Employee em, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ObjectNode objectNode = new JSONUtils().addArrayNode("content", bindingResult.getAllErrors());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(objectNode);
        }

        employeeService = (EmployeeService) context.getBean("employeeService");
        employeeService.saveEmployee(em);
        return ResponseEntity.status(HttpStatus.OK).body(new ObjectMapper()
                .createObjectNode()
                .put("content", "Success!")
        );
    }
    /**
     * Returns one employee object based on email provided from the database
     *
     * @return {@link ResponseEntity} containing {@link HttpStatus} and {@link com.fasterxml.jackson.databind.node.ObjectNode}
     * containing employee objects in JSON form
     */
    @RequestMapping(value = "/read/all", method = RequestMethod.GET)
    public ResponseEntity readAllEmployeeRecord() {
        employeeService = (EmployeeService) context.getBean("employeeService");
        List<Employee> list = employeeService.findAllEmployees();
        ObjectNode objectNode = new JSONUtils().addArrayNode("content", list);
        return ResponseEntity.status(HttpStatus.OK).body(objectNode);
    }

    /**
     * Returns one employee object based on email provided from the database
     *
     * @param email used to search the entry in the database
     * @return {@link ResponseEntity} containing {@link HttpStatus} and {@link com.fasterxml.jackson.databind.node.ObjectNode}
     * containing employee object in JSON form
     */
    @RequestMapping(value = "/read/{email}", method = RequestMethod.GET)
    public ResponseEntity readEmployeeRecord(@PathVariable("email") String email) {
        employeeService = (EmployeeService) context.getBean("employeeService");
        Employee employee;

        try {
            employee = employeeService.findEmployee(email);
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ObjectMapper()
                    .createObjectNode()
                    .put("content", e.getLocalizedMessage())
            );
        }

        return ResponseEntity.status(HttpStatus.OK).body(Objects.requireNonNull(employee));
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity updateRecord(@Valid @ModelAttribute Employee em, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ObjectNode objectNode = new JSONUtils().addArrayNode("content", bindingResult.getAllErrors());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(objectNode);
        }
        employeeService = (EmployeeService) context.getBean("employeeService");
        employeeService.updateEmployee(em);

        return ResponseEntity.status(HttpStatus.OK).body(new ObjectMapper()
                .createObjectNode()
                .put("content", "Success!")
        );
    }

    /**
     * Deletes one employee object based on id provided to the database
     *
     * @param id used to search the entry in the database
     * @return {@link ResponseEntity} containing {@link HttpStatus}
     * Returns {@code HttpStatus.OK} even if {@code id} doesnt match with any record in database
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ResponseEntity deleteEmployeeRecord(@PathVariable("id") int id) {
        employeeService = (EmployeeService) context.getBean("employeeService");
        employeeService.deleteEmployee(id);

        return ResponseEntity.status(HttpStatus.OK).body(new ObjectMapper()
                .createObjectNode()
                .put("content", "Record deleted")
        );
    }
}
