package ibatis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Emp {
    Integer empno;
    String ename;
    String job;
    Integer mgr;
    LocalDateTime hiredate;
    Double sal;
    Double comm;
    Integer deptno;
}
