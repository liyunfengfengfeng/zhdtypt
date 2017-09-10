package com.newage.iep.serivce.Role;

import com.newage.iep.pojos.Role;
import com.newage.iep.util.page.Page;

import java.util.List;

/**
 * Created by a1996_000 on 2017/9/9.
 */
public interface RoleService {
    /**
     * 保存角色信息
     * @param role
     */
    void saveRoleInfo(Role role);

    /**
     * 查询出所有的角色
     * @return
     */
    List<Role> selectAllRoles();

    /**
     * 分页查询角色
     * @param page
     * @return
     */
    List<Role> selectRolesByPage(Page page);

    /**
     * 通过id查询角色
     * @param roleId
     * @return
     */
    Role selectRoleById(String roleId);
}
