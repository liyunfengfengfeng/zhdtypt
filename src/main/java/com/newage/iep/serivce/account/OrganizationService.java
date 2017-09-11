package com.newage.iep.serivce.account;

import com.newage.iep.pojos.organization.Organization;
import com.newage.iep.util.page.Page;

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
    //根据id查询组织单位
    Organization queryOrgById(String orgid);
    //模糊查询标题与内容
    List searchInfor(String searchecontent);
    //查询所有信息
    List searchInfor();
    //通过id查询组织信息
    Organization searchOrgInfor(String cmpid);
    //分页查询出所有数据
    List<Organization> selectOrganizationsByPage(Page page);
}
