package com.newage.iep.serivce.account;

import com.newage.iep.pojos.Organization;

import java.util.List;

/**
 * Created by a1996_000 on 2017/8/11.
 */
//组织业务层
public interface OrganizationService {
    //检查用户输入的组织代号是否有效
    boolean checkCmpCode(String cmp_code);
    //查询出所有的组织
    List<Organization> queryAllOrgs();
}
