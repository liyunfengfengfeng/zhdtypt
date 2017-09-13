package com.newage.iep.serivce.impl.account.Community;

import com.newage.iep.business.dao.GenericHibernateDAO;
import com.newage.iep.pojos.Community.Community;
import com.newage.iep.pojos.Community.Communitycirclecoordinate;
import com.newage.iep.pojos.Community.Communitypolygoncoordinate;
import com.newage.iep.pojos.city.City;
import com.newage.iep.serivce.Community.CommunityService;
import com.newage.iep.util.page.Page;
import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2017/9/2.
 */
@Service("communityService")
public class CommunityServiceImpl extends GenericHibernateDAO implements CommunityService
{
    /**
     * 获取所有小区
     * @return
     */
    @Override
    public List<Community> getAllCommunity()
    {
        String hqlGetAllCommunity = "FROM Community";
        Query queryGetAllCommunity = this.createQuery(hqlGetAllCommunity);
        List<Community> communityList = queryGetAllCommunity.list();
        return communityList;
    }

    /**
     * 保存小区
     * @param communityName    小区名称
     * @param communityAddress 小区详细地址
     * @param belongDistrict   小区所属区域
     * @param belongCity       小区所属城市
     * @param coordinateType   小区所属类型
     * @return
     */
    @Transactional
    @Override
    public boolean saveCommunity(String communityName, String communityAddress, String belongDistrict, String belongCity, Byte coordinateType)
    {
        if(checkHasIt(communityName))
        {
            return false;
        }
        else
        {
            Community community = new Community();
            community.setCommunityName(communityName);
            community.setCommunityAddress(communityAddress);
            community.setBelongCity(belongCity);
            community.setBelongDistrict(belongDistrict);
            community.setCoordinateType(coordinateType);
            this.save(community);
            return true;
        }
    }

    /**
     * 使用多边形围栏保存小区
     * @param communityName       小区名称
     * @param communityPolygonLng 小区多边形经度
     * @param communityPolygonLat 小区多边形纬度
     * @return
     */
    @Transactional
    @Override
    public boolean saveCommunityPolygonCoordinate(String communityName, String communityPolygonLng, String communityPolygonLat)
    {
        Community community = getCommunityIdByName(communityName);
        String id = community.getCommunityId();
        Communitypolygoncoordinate communitypolygoncoordinate = new Communitypolygoncoordinate();
        communitypolygoncoordinate.setCommunityId(id);
        communitypolygoncoordinate.setCommunityPolygonLng(communityPolygonLng);
        communitypolygoncoordinate.setCommuntiyPolygonLat(communityPolygonLat);
        this.save(communitypolygoncoordinate);
        return true;
    }

    /**
     * 使用圆形围栏保存小区
     * @param communityName         小区名称
     * @param communityCircleLng    圆形围栏经度
     * @param communityCircleLat    圆形围栏纬度
     * @param communityCircleRadius 圆形围栏半径
     * @return
     */
    @Transactional
    @Override
    public boolean saveCommunityCircleCoordinate(String communityName, String communityCircleLng, String communityCircleLat, String communityCircleRadius)
    {
        Community community = getCommunityIdByName(communityName);
        String id = community.getCommunityId();
        Communitycirclecoordinate communitycirclecoordinate = new Communitycirclecoordinate();
        communitycirclecoordinate.setCommunityId(id);
        System.out.println("CommunityServiceImpl saveCommunityCircleCoordinate() id = " + id);
        communitycirclecoordinate.setCommunityCircleLat(communityCircleLat);
        communitycirclecoordinate.setCommunityCircleLng(communityCircleLng);
        communitycirclecoordinate.setCommunityCircleRadius(communityCircleRadius);
        this.save(communitycirclecoordinate);
        return true;
    }

    /**
     * 删除小区
     * @param id             小区ID
     * @param coordinateType 小区围栏类型
     * @return
     */
    @Transactional
    @Override
    public boolean deleteCommunity(String id, Byte coordinateType)
    {
        if(id == null)
        {
            return false;
        }
        if(coordinateType == 0)
        {
            String hqlDeletePolygon = "DELETE FROM Communitypolygoncoordinate cpc where cpc.communityId=?";
            Query queryDeletePolygon = this.createQuery(hqlDeletePolygon);
            System.out.println("CommunityServiceImpl deleteCommunity() id = " + id);
            queryDeletePolygon.setString(0,id);
            queryDeletePolygon.executeUpdate();
        }
        else if(coordinateType == 1)
        {
            String hqlDeleteCircle = "DELETE FROM Communitycirclecoordinate ccc where ccc.communityId=?";
            Query queryDeleteCircle = this.createQuery(hqlDeleteCircle);
            queryDeleteCircle.setString(0,id);
            System.out.println("CommunityServiceImpl deleteCommunity() id = " + id);
            queryDeleteCircle.executeUpdate();
        }
        else
        {
            return false;
        }
        String hqlDeleteCommunity = "DELETE FROM Community c where c.communityId=?";
        Query queryDeleteCommunity = this.createQuery(hqlDeleteCommunity);
        queryDeleteCommunity.setString(0,id);
        queryDeleteCommunity.executeUpdate();
        return true;
    }

    /**
     * 获取圆形围栏
     * @param id 小区id
     * @return
     */
    @Override
    public List<Communitycirclecoordinate> getCircle(String id)
    {
        String hqlGetCircle = "FROM Communitycirclecoordinate ccc where ccc.communityId=?";
        Query queryGetCircle = this.createQuery(hqlGetCircle);
        queryGetCircle.setString(0,id);
        List<Communitycirclecoordinate> communitycirclecoordinateList = queryGetCircle.list();
        return communitycirclecoordinateList;
    }

    /**
     * 获取多边形围栏
     * @param id 小区id
     * @return
     */
    @Override
    public List<Communitypolygoncoordinate> getPolygon(String id)
    {
        String hqlGetPolygon = "FROM Communitypolygoncoordinate cpc where cpc.communityId=?";
        Query queryGetPolygon = this.createQuery(hqlGetPolygon);
        queryGetPolygon.setString(0,id);
        List<Communitypolygoncoordinate> communitypolygoncoordinateList = queryGetPolygon.list();
        return communitypolygoncoordinateList;
    }

    /**
     * 根据名称获取小区Id
     * @param communityName 小区名称
     * @return
     */
    @Override
    public Community getCommunityIdByName(String communityName)
    {
        Iterator<Community> communityIterator;
        String hqlGetCommunityIdByName = "FROM Community c where c.communityName=?";
        Query queryGetCommunityIdByName = this.createQuery(hqlGetCommunityIdByName);
        queryGetCommunityIdByName.setString(0,communityName);
        communityIterator = queryGetCommunityIdByName.iterate();
        if(communityIterator.hasNext())
        {
            return communityIterator.next();
        }
        else
        {
            return null;
        }
    }

    @Override
    public List<City> getAllProvince()
    {
        String hql = "FROM City c where c.level=0";
        Query query = this.createQuery(hql);
        return query.list();
    }

    @Override
    public List<City> getCity(String id)
    {
        String hql = "FROM City c where c.parent_id=?";
        Query query = this.createQuery(hql);
        query.setString(0,id);
        return query.list();
    }

    @Override
    public List<City> getDistrict(String id)
    {
        String hql = "FROM City c where c.parent_id=?";
        Query query = this.createQuery(hql);
        query.setString(0,id);
        return query.list();
    }

    @Override
    public String getCityNameById(String id)
    {
        Iterator<City> cityIterator;
        String hql = "FROM City c where c.id=?";
        Query query = this.createQuery(hql);
        query.setString(0,id);
        cityIterator = query.iterate();
        if(cityIterator.hasNext())
        {
            return cityIterator.next().getName();
        }
        else
            return null;
    }

    @Override
    public List<City> getCityListById(String id) {
        int parentId;
        Iterator<City> cityIterator;
        String hqlGetParentId = "FROM City c where c.id=?";
        Query query = this.createQuery(hqlGetParentId);
        query.setString(0,id);
        cityIterator = query.iterate();
        if(cityIterator.hasNext())
        {
            parentId = cityIterator.next().getParent_id();
        }
        else
        {
            return null;
        }
        String hqlGetListByParentId = "FROM City c where c.parent_id=?";
        Query queryGetListByParentId = this.createQuery(hqlGetListByParentId);
        queryGetListByParentId.setInteger(0,parentId);
        return queryGetListByParentId.list();
    }

    @Override
    public List<Community> selectcheckWorkPages(Page page) {
        Query query = this.createQuery("from Community ");
        query.setFirstResult(page.getStart());//是int值，是开始查询的位置
        query.setMaxResults(page.getPageSize());//最大容量
        //返回分页后的数据
        List list = query.list();
        return list;
    }

    /**
     * 检查小区名称是否重复
     * @param communityName
     * @return
     */
    public boolean checkHasIt(String communityName)
    {
        Iterator<Community> communityIterator;
        String hqlCheck = "FROM Community c where c.communityName=?";
        Query queryCheck = this.createQuery(hqlCheck);
        queryCheck.setString(0,communityName);
        communityIterator = queryCheck.iterate();
        if(communityIterator.hasNext())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
