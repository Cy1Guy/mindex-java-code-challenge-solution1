package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportingSerivceImpl implements ReportingService {
    private static final Logger LOG = LoggerFactory.getLogger(ReportingService.class);
    @Autowired
    private EmployeeRepository employeeRepository;



    public ReportingStructure read(String id) {
        LOG.debug("Creating employee with id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);
        ReportingStructure reportingStructure = new ReportingStructure();
        reportingStructure.setEmployee(employee);
        reportingStructure.setNumberOfReports(employee.getDirectReports().size());

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        return reportingStructure;
    }
}
