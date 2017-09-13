package com.newage.iep.action.Community;

import com.newage.iep.pojos.Community.Community;
import com.newage.iep.pojos.Community.Communitycirclecoordinate;
import com.newage.iep.pojos.Community.Communitypolygoncoordinate;
import com.newage.iep.pojos.city.City;
import com.newage.iep.serivce.Community.CommunityService;
import com.newage.iep.util.page.Page;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/2.
 */
@Controller("communityAction")
public class CommunityAction extends ActionSupport
{
    @Resource
    private CommunityService communityService;//小区服务层
    private String communityName;//小区名称
    private String communityAddress;//小区详细地址
    private String belongDistrict;//小区所属区域
    private String belongCity;//小区所属城市
    private Byte coordinateType;//小区使用围栏类型
    private List<String> polygonLng;//小区多边形经度
    private List<String> polygonLat;//小区多边形纬度
    private String circleRadius;//小区圆形半径
    private String circleCenterLng;//小区圆形经度
    private String circleCenterLat;//小区圆形纬度
    private String communityCurrentName;//小区当前名称
    private Byte communityCurrentType;//小区当前围栏类型
    private String provinceId;
    private String cityId;
    private String result;

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getCommunityCurrentName() {
        return communityCurrentName;
    }

    public void setCommunityCurrentName(String communityCurrentName) {
        this.communityCurrentName = communityCurrentName;
    }

    public Byte getCommunityCurrentType() {
        return communityCurrentType;
    }

    public void setCommunityCurrentType(Byte communityCurrentType) {
        this.communityCurrentType = communityCurrentType;
    }

    public List<String> getPolygonLng() {
        return polygonLng;
    }

    public void setPolygonLng(List<String> polygonLng) {
        this.polygonLng = polygonLng;
    }

    public List<String> getPolygonLat() {
        return polygonLat;
    }

    public void setPolygonLat(List<String> polygonLat) {
        this.polygonLat = polygonLat;
    }

    public String getCircleRadius() {
        return circleRadius;
    }

    public void setCircleRadius(String circleRadius) {
        this.circleRadius = circleRadius;
    }

    public String getCircleCenterLng() {
        return circleCenterLng;
    }

    public void setCircleCenterLng(String circleCenterLng) {
        this.circleCenterLng = circleCenterLng;
    }

    public String getCircleCenterLat() {
        return circleCenterLat;
    }

    public void setCircleCenterLat(String circleCenterLat) {
        this.circleCenterLat = circleCenterLat;
    }

    public Byte getCoordinateType() {
        return coordinateType;
    }

    public void setCoordinateType(Byte coordinateType) {
        this.coordinateType = coordinateType;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getCommunityAddress() {
        return communityAddress;
    }

    public void setCommunityAddress(String communityAddress) {
        this.communityAddress = communityAddress;
    }

    public String getBelongDistrict() {
        return belongDistrict;
    }

    public void setBelongDistrict(String belongDistrict) {
        this.belongDistrict = belongDistrict;
    }

    public String getBelongCity() {
        return belongCity;
    }

    public void setBelongCity(String belongCity) {
        this.belongCity = belongCity;
    }

    /**
     * 获取所有小区   菜单管理
     * @return
     */
    public String getAllCommunity()
    {
        HttpServletRequest request = ServletActionContext.getRequest();
        List<Community> communityList = communityService.getAllCommunity();
        List<String> cityNameList = new ArrayList<String>();
        List<String> districtNameList = new ArrayList<String>();

        //查询出所有的考勤组信息
        List checkWorks = communityService.getAllCommunity();
        //分页
        Page page = new Page();
        int totalRecord = checkWorks.size();
        int pageSize = 10;
        //获取当前页
        String pno1 = (String)request.getParameter("pno");

        int pno = 1;
        if(pno1!=null){
            pno = Integer.parseInt(pno1);
        }
        String pagesize1 = (String)request.getParameter("pagesize");
        System.out.println("pagesize is " + pagesize1);
        if(pagesize1!=null){
            pageSize = Integer.parseInt(pagesize1);
        }
        //设置当前页
        page.setPageNo(pno);
        //每页容量
        page.setPageSize(pageSize);
        //总页数
        int totalPageNum = (totalRecord  +  pageSize  - 1) / pageSize;
        page.setTotalPage(totalPageNum);
        //总记录数
        page.setTotalRecord(totalRecord);

        //分页需要的起始与终止位置
        int start = (pno-1)* pageSize;
        int end = pageSize;
        page.setStart(start);
        page.setEnd(end);
        //依据分页查询出条件  分页后的考勤组
        List<Community> checkWorkPages = communityService.selectcheckWorkPages(page);


        for(int i = 0; i < communityList.size(); i++)
        {
            cityNameList.add(communityService.getCityNameById(communityList.get(i).getBelongCity()));
            districtNameList.add(communityService.getCityNameById(communityList.get(i).getBelongDistrict()));
        }

        request.setAttribute("cityNameList",cityNameList);
        request.setAttribute("districtNameList",districtNameList);
        request.setAttribute("communityList",checkWorkPages);
        request.setAttribute("page", page);
        request.setAttribute("pageSize",pageSize);
        return "getAllCommunity";
    }

    /**
     * 存储小区 新建保存
     * @return
     */
    public String saveCommunity()
    {
        System.out.println("CommunityAction saveCommunity() coordinateType = " + coordinateType);
        if(communityService.saveCommunity(communityName,communityAddress,belongDistrict,belongCity,coordinateType))
        {
            if(coordinateType == 0)
            {
                for(int i = 0; i < polygonLat.size(); i++)
                {
                    communityService.saveCommunityPolygonCoordinate(communityName,polygonLng.get(i),polygonLat.get(i));
                }
                return "saveCommunity";
            }
            else if(coordinateType == 1)
            {
                communityService.saveCommunityCircleCoordinate(communityName,circleCenterLng,circleCenterLat,circleRadius);
                return "saveCommunity";
            }
            else
            {
                return "error";
            }
        }
        else
        {
            return "error";
        }
    }

    /**
     * 换页   新增
     * @return
     */
    public String changePage()
    {
        HttpServletRequest request = ServletActionContext.getRequest();
        List<City> provinceList = communityService.getAllProvince();
        request.setAttribute("provinceList", provinceList);
        return "changePage";
    }

    /**
     * 删除小区
     * @return
     */
    public String deleteCommunity()
    {
        HttpServletRequest request = ServletActionContext.getRequest();
        String communityId = request.getParameter("communityId");
        Byte coordinateType = Byte.parseByte(request.getParameter("coordinateType"));
        if(communityId == null || coordinateType == null)
        {
            return "error";
        }

        if(communityService.deleteCommunity(communityId, coordinateType))
        {
            return "deleteCommunity";
        }
        else
        {
            return "error";
        }
    }

    /**
     * 展示小区   编辑 名称链接
     * @return
     */
    public String showCommunity()
    {
        HttpServletRequest request = ServletActionContext.getRequest();
        String communityCurrentName = request.getParameter("communityName");
        Community community = communityService.getCommunityIdByName(communityCurrentName);
        String id = community.getCommunityId();
        Byte coordinateType = community.getCoordinateType();
        String cityName = communityService.getCityNameById(community.getBelongCity());
        String districtName = communityService.getCityNameById(community.getBelongDistrict());
        if(coordinateType == 0)
        {
            List<Communitypolygoncoordinate> communitypolygoncoordinateList = communityService.getPolygon(id);
            Communitypolygoncoordinate communitypolygoncoordinate = communitypolygoncoordinateList.get(0);
            Communitycirclecoordinate communitycirclecoordinate = new Communitycirclecoordinate();
            communitycirclecoordinate.setCommunityCircleRadius("0");
            communitycirclecoordinate.setCommunityCircleLng("0");
            communitycirclecoordinate.setCommunityCircleLat("0");
            request.setAttribute("community", community);
            request.setAttribute("cityName", cityName);
            request.setAttribute("districtName", districtName);
            request.setAttribute("communitycirclecoordinate", communitycirclecoordinate);
            request.setAttribute("communitypolygoncoordinate", communitypolygoncoordinate);
            request.setAttribute("communitypolygoncoordinateList", communitypolygoncoordinateList);
            return "showCommunity";
        }
        else if(coordinateType == 1)
        {
            List<Communitycirclecoordinate> communitycirclecoordinateList = communityService.getCircle(id);
            Communitypolygoncoordinate communitypolygoncoordinate = new Communitypolygoncoordinate();
            communitypolygoncoordinate.setCommuntiyPolygonLat("0");
            communitypolygoncoordinate.setCommunityPolygonLng("0");
            Communitycirclecoordinate communitycirclecoordinate = communitycirclecoordinateList.get(0);
            request.setAttribute("communitypolygoncoordinate",communitypolygoncoordinate);
            request.setAttribute("cityName", cityName);
            request.setAttribute("districtName", districtName);
            request.setAttribute("community", community);
            request.setAttribute("communitycirclecoordinate", communitycirclecoordinate);
            return "showCommunity";
        }
        else
        {
            return "error";
        }
    }

    /**
     * 更新小区  编辑之后修改
     * @return
     */
    public String  updateCommunity()
    {
        System.out.println("CommunityAction updateCommunity() communityCurrentType = " + communityCurrentType + " coordinateType = " + coordinateType);
        String id;
        Community community = communityService.getCommunityIdByName(communityCurrentName);
        if(community != null)
        {
            id =community.getCommunityId();
            communityService.deleteCommunity(id, communityCurrentType);
        }
        if(communityService.saveCommunity(communityName,communityAddress,belongDistrict,belongCity,coordinateType))
        {
            if(coordinateType == 0)
            {
                for(int i = 0; i < polygonLat.size(); i++)
                {
                    communityService.saveCommunityPolygonCoordinate(communityName,polygonLng.get(i),polygonLat.get(i));
                }
                return "updateCommunity";
            }
            else if(coordinateType == 1)
            {
                communityService.saveCommunityCircleCoordinate(communityName,circleCenterLng,circleCenterLat,circleRadius);
                return "updateCommunity";
            }
            else
            {
                return "error";
            }
        }
        else
        {
            return "error";
        }
    }
    //获取城市
    public String getCity()
    {
        Map<String, Object> map =new HashMap<String, Object>();
        List<City> cityList = communityService.getCity(provinceId);
        System.out.println("CommunityAction getCity() cityParentId = " + cityList.get(0).getParent_id());
        map.put("cityList", cityList);
        JSONObject json =JSONObject.fromObject(map);
        result = json.toString();
        return "getCity";
    }
    //具体区域
    public String getDistrict()
    {
        Map<String, Object> map = new HashMap<String, Object>();
        List<City> districtList = communityService.getDistrict(cityId);
        System.out.println("CommunityAction getCity() districtParentId = " + districtList.get(0).getParent_id());
        map.put("districtList", districtList);
        JSONObject json = JSONObject.fromObject(map);
        result = json.toString();
        return "getDistrict";
    }
}
