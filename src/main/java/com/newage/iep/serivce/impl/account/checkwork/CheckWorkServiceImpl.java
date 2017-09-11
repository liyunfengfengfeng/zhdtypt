package com.newage.iep.serivce.impl.account.checkwork;

import com.newage.iep.business.dao.GenericHibernateDAO;
import com.newage.iep.pojos.checkwork.AttendanceTeam;
import com.newage.iep.serivce.checkwork.CheckWorkService;
import com.newage.iep.util.page.Page;
import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by a1996_000 on 2017/9/6.
 */
@Service("checkWorkService")
public class CheckWorkServiceImpl extends GenericHibernateDAO implements CheckWorkService {
    //查询所有的考勤组信息
    @Override
    public List selectAllCheckInfo() {
        Query query = this.createQuery("from AttendanceTeam ");
        List list = query.list();
        return list;
    }
    //删除考勤组信息
    @Transactional
    @Override
    public void delCheckWork(String checkWorkId) {
        Query query = this.createQuery("from AttendanceTeam attendanceTeam where attendanceTeam.id=:checkWorkId1");
        query.setString("checkWorkId1",checkWorkId);
        List list = query.list();
        if(list!=null&&list.size()==1){
            AttendanceTeam attendanceTeam = (AttendanceTeam)list.get(0);
            this.delete(attendanceTeam);
        }
    }

    /**
     * 分页查询出考勤组信息
     * @param page
     * @return
     */
    @Override
    public List<AttendanceTeam> selectcheckWorkPages(Page page) {
        Query query = this.createQuery("from AttendanceTeam ");
        query.setFirstResult(page.getStart());//是int值，是开始查询的位置
        query.setMaxResults(page.getPageSize());//最大容量
        //返回分页后的数据
        List list = query.list();
        return list;
    }
    //保存考勤组信息
    @Transactional
    @Override
    public void saveCheckWork(AttendanceTeam attendanceTeam) {
        this.save(attendanceTeam);
    }

    /**
     * 通过考勤组名称查询寻其id
     * @param checkworkname
     * @return
     */
    @Override
    public String selectAttendencrIdByName(String checkworkname) {
        Query query = this.createQuery("from AttendanceTeam attendanceTeam where attendanceTeam.rolename=:rolenameId1");
        query.setString("rolenameId1",checkworkname);
        List list = query.list();
        AttendanceTeam attendanceTeam = null;
        if(list!=null&&list.size()==1){
            attendanceTeam = (AttendanceTeam)list.get(0);
        }
        if(attendanceTeam!=null){
            return attendanceTeam.getId();
        }
        return null;
    }

    /**
     * 通过id查询出考勤组信息
     * @param checkWorkId
     * @return
     */
    @Override
    public AttendanceTeam selectCheckWorkById(String checkWorkId) {
        Query query = this.createQuery("from AttendanceTeam attendanceTeam where attendanceTeam.id=:id1");
        query.setString("id1",checkWorkId);
        List list = query.list();
        if(list!=null&&list.size()==1){
            AttendanceTeam attendanceTeam = (AttendanceTeam)list.get(0);
            return attendanceTeam;
        }
        return null;
    }

    /**
     * 更新考勤组
     * @param attendanceTeam
     */
    @Transactional
    @Override
    public void updateAttendanceTeam(AttendanceTeam attendanceTeam) {
        this.update(attendanceTeam);
    }
}
