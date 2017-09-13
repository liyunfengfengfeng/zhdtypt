package com.newage.iep.serivce.OrganizationManager;


import com.newage.iep.pojos.organization.Organization;
import com.newage.iep.pojos.organization.OrganizationType;
import com.newage.iep.util.page.Page;

import java.util.List;

/**
 * Created by qq101 on 2017/8/21.
 */
public interface OrganizationManagerService {
    /*
       查询所有组织档案
     */
    List<Organization> queryOrganization();
    /*
        查询组织档案
     */
    Organization queryOrganization(String cmpId);
    /*
        查询组织档案通过名字
   */
    boolean queryOrganizationByName(String cmp_name);
    /*
     查询组织档案通过名字
*/
    boolean queryOrganizationById(String cmp_id);
    /*
       新增组织档案
     */
    boolean addOrganization(Organization organization);
    /*
      修改组织档案
    */
    boolean updateOrganization(Organization organization);
    /*
    删除组织档案
    */
    boolean deleteOrganization(Organization organization);
    /*
       获取保存成功的组织的id
     */
    String queryOrganizationId(Organization organization);
    /*
        添加组织附件
     */
    boolean addAccessoryForOrganization(String fileName, String savePath, String cmpId);
     /*
      查询组织类别
     */
     List<OrganizationType> queryOrganizationType(String id);
    /*
        分页查询
     */
    List<Organization> selectOrganizationsByPage(Page page);
}
