package com.newage.iep.serivce.impl.account.Community;

import com.newage.iep.business.dao.GenericHibernateDAO;
import com.newage.iep.pojos.Enclousure.Circlecoordinate;
import com.newage.iep.pojos.Enclousure.Enclosure;
import com.newage.iep.pojos.Enclousure.Polygoncoordinate;
import com.newage.iep.pojos.account.Account;
import com.newage.iep.pojos.checkwork.AttendanceTeam;
import com.newage.iep.serivce.Community.EnclosureService;
import com.newage.iep.util.page.Page;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2017/8/23.
 */
@Service("enclosureService")
public class EnclosureServiceImpl extends GenericHibernateDAO implements EnclosureService
{
    /**
     * 判断围栏名称是否存在
     * @param enclosureName 围栏名称
     * @return
     */
    @Override
    public boolean checkHasEnclosure(String enclosureName)
    {
        Iterator<Enclosure> itCheck;
        String hqlCheck = "FROM Enclosure e where e.enclosureName=?";
        Query queryCheck = this.createQuery(hqlCheck);
        queryCheck.setString(0,enclosureName);
        itCheck = queryCheck.iterate();
        if(itCheck.hasNext())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * 保存围栏
     * @param enclosureName  围栏名称
     * @param coordinateType 围栏类型
     * @return
     */
    @Transactional
    @Override
    public boolean saveEnclosure(String enclosureName, int coordinateType, String attendanceName, String enclosureDescriptions, String status)
    {
        String attendanceId;
        if(attendanceName == null)
        {
            return false;
        }
        else
        {
            attendanceId = getAttendanceIdByName(attendanceName);
            if(attendanceId == null)
            {
                return false;
            }
        }

        if(enclosureName == null)
        {
            return false;
        }

        if(coordinateType != 1 && coordinateType != 0)
        {
            return false;
        }

        if(status == null)
        {
            return false;
        }

        if(checkHasEnclosure(enclosureName))
        {
            return false;
        }
        else
        {
            String accountId = null;
            HttpServletRequest request = ServletActionContext.getRequest();
            HttpSession session = request.getSession();
            Iterator<Account> accountIterator;
            String accountName = (String)session.getAttribute("accountName");
            String hqlFindAccount = "FROM Account a where a.name=?";
            Query queryFindAccount = this.createQuery(hqlFindAccount);
            queryFindAccount.setString(0,accountName);
            accountIterator = queryFindAccount.iterate();
            if(accountIterator.hasNext())
            {
                accountId = accountIterator.next().getAccount_0_id();
            }
            else
            {
                System.out.println("account is null");
                return false;
            }
            Enclosure enclosure = new Enclosure();
            System.out.println("EnclosureServiceImpl saveEnclosure() enclosureName = " + enclosureName +" coordinateType = " + coordinateType + " attendanceId = " + attendanceId );
            enclosure.setDescriptions(enclosureDescriptions);
            enclosure.setStatus(Byte.parseByte(status));
            enclosure.setModifyDate(new java.util.Date());
            enclosure.setEnclosureName(enclosureName);
            enclosure.setCoordinateType(coordinateType);
            enclosure.setModifyId(accountId);
            enclosure.setAttendanceId(attendanceId);
            this.save(enclosure);
            return true;
        }
    }

    /**
     * 保存圆形坐标
     * @param circleRaidus    圆形半径
     * @param circleCenterLng 圆形圆心经度
     * @param circleCenterLat 圆形圆心纬度
     * @return
     */
    @Transactional
    @Override
    public boolean saveCirclecoordinate(String enclosureName, String circleRaidus, String circleCenterLng, String circleCenterLat)
    {
        Enclosure enclosure = getIdByName(enclosureName);
        Circlecoordinate circlecoordinate = new Circlecoordinate();
        circlecoordinate.setEnclosureId(enclosure.getId());
        circlecoordinate.setCircleLat(circleCenterLat);
        circlecoordinate.setCircleLng(circleCenterLng);
        circlecoordinate.setCircleRadius(circleRaidus);
        this.save(circlecoordinate);
        return true;
    }

    /**
     * 保存多边形坐标
     * @param polygonLng 多边形经度
     * @param polygonLat 多边形纬度
     * @return
     */
    @Transactional
    @Override
    public void savePolygoncoordinate(String enclosureName, String polygonLng, String polygonLat)
    {
        Enclosure enclosure = getIdByName(enclosureName);
        Polygoncoordinate polygoncoordinate = new Polygoncoordinate();
        polygoncoordinate.setEnclosureId(enclosure.getId());
        polygoncoordinate.setPolygonLat(polygonLat);
        polygoncoordinate.setPolygonLng(polygonLng);
        System.out.println("EnclosureServiceImpl savePolygoncoordinate() polygoncoordinateLat = "+ polygonLat);
        this.save(polygoncoordinate);
    }

    /**
     * 获取EnclosureList
     * @return
     */
    @Override
    public List<Enclosure> getAllEnclosure()
    {
        String hqlGetAllEnclosure = "FROM Enclosure";
        Query queryGetAllEnclosure = this.createQuery(hqlGetAllEnclosure);
        List<Enclosure> enclosureList = queryGetAllEnclosure.list();
        return enclosureList;
    }

    /**
     * 获取圆形坐标
     * @param enclosureId 围栏Id
     * @return
     */
    @Override
    public List<Circlecoordinate> getCirclecoordinate(String enclosureId)
    {
        String hqlGetCirclecoordinate = "FROM Circlecoordinate cc where cc.enclosureId=?";
        Query queryGetCirclecoordinate = this.createQuery(hqlGetCirclecoordinate);
        queryGetCirclecoordinate.setString(0,enclosureId);
        List<Circlecoordinate> circlecoordinateList = queryGetCirclecoordinate.list();
        return circlecoordinateList;
    }

    /**
     * 获取多边形坐标
     * @param enclosureId 围栏id
     * @return
     */
    @Override
    public List<Polygoncoordinate> getPolygoncoordinate(String enclosureId)
    {
        String hqlGetPolygoncoordinate = "FROM Polygoncoordinate pc where pc.enclosureId=?";
        Query queryGetPolygoncoordinate = this.createQuery(hqlGetPolygoncoordinate);
        queryGetPolygoncoordinate.setString(0,enclosureId);
        List<Polygoncoordinate> polygoncoordinateList = queryGetPolygoncoordinate.list();
        return polygoncoordinateList;
    }


    /**
     * 根据Name获取围栏
     * @param enclosureName 围栏名称
     * @return
     */
    @Override
    public Enclosure getIdByName(String enclosureName)
    {
        Iterator<Enclosure> it;
        String hql = "FROM Enclosure e where e.enclosureName=?";
        Query query = this.createQuery(hql);
        query.setString(0,enclosureName);
        it = query.iterate();
        if(it.hasNext())
        {
            return it.next();
        }
        else
        {
            return null;
        }
    }

    /**
     * 删除围栏
     * @param enclosureId   围栏id
     * @param enclosureType 围栏类型
     * @return
     */
    @Transactional
    @Override
    public boolean deleteEnclosure(String enclosureId, int enclosureType)
    {
        if(enclosureId == null)
        {
            return false;
        }
        if(enclosureType == 0)
        {
            String hqlDeletePolygon = "DELETE FROM Polygoncoordinate pc where pc.enclosureId=?";
            Query queryDeletePolygon = this.createQuery(hqlDeletePolygon);
            queryDeletePolygon.setString(0, enclosureId);
            queryDeletePolygon.executeUpdate();
        }
        else if(enclosureType == 1)
        {
            String hqlDeleteCircle = "DELETE FROM Circlecoordinate cc where cc.enclosureId=?";
            Query queryDeleteCircle = this.createQuery(hqlDeleteCircle);
            queryDeleteCircle.setString(0, enclosureId);
            queryDeleteCircle.executeUpdate();
        }
        String hqlDeleteEnclosure = "DELETE FROM Enclosure e where e.id=?";
        Query queryDeleteEnclosure = this.createQuery(hqlDeleteEnclosure);
        queryDeleteEnclosure.setString(0, enclosureId);
        queryDeleteEnclosure.executeUpdate();
        return true;
    }

    /**
     * 根据考勤组id获取考勤组
     * @param attendanceteamId 考勤组id
     * @return
     */
    @Override
    public AttendanceTeam getAttendanceteamById(String attendanceteamId)
    {
        Iterator<AttendanceTeam> attendanceteamIterator;
        String hqlGetAttendanceteamById = "FROM AttendanceTeam adt where adt.id=?";
        Query queryGetAttendanceteamById = this.createQuery(hqlGetAttendanceteamById);
        queryGetAttendanceteamById.setString(0,attendanceteamId);
        attendanceteamIterator = queryGetAttendanceteamById.iterate();
        if(attendanceteamIterator.hasNext())
        {
            return attendanceteamIterator.next();
        }
        else
        {
            return null;
        }
    }

    /**
     * 获取所有考勤组
     * @return
     */
    @Override
    public List<AttendanceTeam> getAllAttendance()
    {
        String hqlGetAllAttendanceName = "FROM AttendanceTeam";
        Query queryGetAllAttendanceName = this.createQuery(hqlGetAllAttendanceName);
        List<AttendanceTeam> attendanceteamList = queryGetAllAttendanceName.list();
        return attendanceteamList;
    }

    /**
     * 根据考勤组名称获取考勤组
     * @param attendanceName 考勤组名称
     * @return
     */
    String getAttendanceIdByName(String attendanceName)
    {
        Iterator<AttendanceTeam> attendanceteamIterator;
        String hqlGetAttendanceNameById = "FROM AttendanceTeam at where at.rolename=?";
        Query queryGetAttendanceNameById = this.createQuery(hqlGetAttendanceNameById);
        queryGetAttendanceNameById.setString(0,attendanceName);
        attendanceteamIterator = queryGetAttendanceNameById.iterate();
        if(attendanceteamIterator.hasNext())
        {
            String id = attendanceteamIterator.next().getId();
            return id;
        }
        else
        {
            return null;
        }
    }

    public List<Enclosure> selectcheckWorkPages(Page page) {
        Query query = this.createQuery("from Enclosure ");
        query.setFirstResult(page.getStart());//是int值，是开始查询的位置
        query.setMaxResults(page.getPageSize());//最大容量
        //返回分页后的数据
        List list = query.list();
        return list;
    }

    @Override
    public String getModifyNameById(String modifyId) {
        Iterator<Account> accountIterator;
        String hql = "FROM Account a where a.account_0_id=?";
        Query query = this.createQuery(hql);
        query.setString(0,modifyId);
        accountIterator = query.iterate();
        if(accountIterator.hasNext())
        {
            return accountIterator.next().getName();
        }
        else
            return null;
    }
}
