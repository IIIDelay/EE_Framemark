package org.img.mapper;

import org.apache.ibatis.annotations.Param;
import org.img.entity.TbEmployees;

import java.util.List;

/**
 * IEmployeesMapper
 *
 * @author IIIDelay
 * @createTime 2023年03月19日 21:36:00
 */
public interface IEmployeesMapper {
    /**
     * selectEmps
     *
     * @return List<TbEmployees>
     */
    List<TbEmployees> selectEmps();

    /**
     * insertEmps
     *
     * @param tbEmployees tbEmployees
     */
    void insertEmps(@Param("tbEmployees") List<TbEmployees> tbEmployees);
}
