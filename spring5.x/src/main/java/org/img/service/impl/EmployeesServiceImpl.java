package org.img.service.impl;

import org.img.entity.TbEmployees;
import org.img.mapper.IEmployeesMapper;
import org.img.service.IEmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * EmployeesServiceImpl
 *
 * @author IIIDelay
 * @createTime 2023年03月19日 21:35:00
 */
@Service
public class EmployeesServiceImpl implements IEmployeesService {
    @Autowired
    private IEmployeesMapper employeesMapper;

    @Override
    public List<TbEmployees> queryEmps() {
        return employeesMapper.selectEmps();
    }

    @Override
    public void insertEmps(List<TbEmployees> tbEmployees) {
        employeesMapper.insertEmps(tbEmployees);
    }
}
