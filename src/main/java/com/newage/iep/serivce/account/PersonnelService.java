package com.newage.iep.serivce.account;

import com.newage.iep.pojos.Personnel;

/**
 * Created by a1996_000 on 2017/8/12.
 */
//人员信息业务层
public interface PersonnelService {
    /**
     * 查询所有的人员信息
     */
    void queryAllPersons();

    /**
     * 注册时添加人员信息
     * @param personnel
     */
    void registerPersonnelInfo(Personnel personnel);

    /**
     * 通过邮箱查询人员中对应的用户
     * @param mail
     * @return
     */
    Personnel queryPersonByMail(String mail);
    //更新用户信息
    void updatePersonnelInfo(Personnel personnel);
}
