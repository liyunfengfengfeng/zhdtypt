package com.newage.iep.serivce.impl.account.checkwork;

import com.newage.iep.business.dao.GenericHibernateDAO;
import com.newage.iep.pojos.AttendenceOrg;
import com.newage.iep.serivce.checkwork.AttendenceOrgService;
import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by a1996_000 on 2017/9/9.
 */
@Service("attendenceOrgService")
public class AttendenceOrgServiceImpl extends GenericHibernateDAO implements AttendenceOrgService {
    /**
     * 保存考勤组与组织的业务层
     * @param attendenceOrg
     */
    @Transactional
    @Override
    public void saveAttendenceOrg(AttendenceOrg attendenceOrg) {
        this.save(attendenceOrg);
    }

    @Override
    public List<AttendenceOrg> selectAllOrgsBelongAttendence(String checkWorkId) {
        Query query = this.createQuery("from AttendenceOrg attendenceOrg where attendenceOrg.attenddenceid=:attenddenceid1");
        query.setString("attenddenceid1",checkWorkId);
        List list = query.list();
        return list;
    }
}
