package org.excel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.excel.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper extends BaseMapper<User> {

    @Select(value = "select dept_name deptName,count(u.id) num from tb_dept d LEFT JOIN tb_user u on  d.id=u.dept_id GROUP BY dept_name")
    List<Map> columnCharts();

    @Select("select m.name,IFNULL(t.num,0) num from tb_month m LEFT JOIN ( " +
            "select DATE_FORMAT(hire_date,'%m') months ,count(id) num from tb_user GROUP BY DATE_FORMAT(hire_date,'%m') " +
            ") t ON m.name=t.months")
    List<Map> lineCharts();
}
