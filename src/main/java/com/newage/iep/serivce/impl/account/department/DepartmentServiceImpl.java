package com.newage.iep.serivce.impl.account.department;

import com.newage.iep.business.dao.GenericHibernateDAO;
import com.newage.iep.pojos.Department;
import com.newage.iep.serivce.department.DepartmentService;
import org.hibernate.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by a1996_000 on 2017/9/6.
 */
//部门业务层
@Service("departmentService")
public class DepartmentServiceImpl extends GenericHibernateDAO implements DepartmentService {
    //查询所有的部门
    @Override
    public List<Department> selectAllDepartments() {
        Query query = this.createQuery("from Department ");
        List departments = query.list();
        return departments;
    }
}
