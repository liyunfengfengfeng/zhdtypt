package com.newage.iep.serivce.department;

import com.newage.iep.pojos.department.Department;

import java.util.List;

/**
 * Created by a1996_000 on 2017/9/6.
 */
//部门业务层
public interface DepartmentService {
    //查询所有的部门
    List<Department> selectAllDepartments();
}
