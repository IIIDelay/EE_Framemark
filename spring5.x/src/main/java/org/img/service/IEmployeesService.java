package org.img.service;

import org.img.entity.TbEmployees;

import java.util.List;

/**
 * IEmployeesService
 *
 * @author IIIDelay
 * @createTime 2023年03月19日 21:34:00
 */
public interface IEmployeesService {
    List<TbEmployees> queryEmps();

    void insertEmps(List<TbEmployees> tbEmployees);
}
