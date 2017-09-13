package com.newage.iep.action.Enclosure;


import com.newage.iep.pojos.Enclousure.Circlecoordinate;
import com.newage.iep.pojos.Enclousure.Enclosure;
import com.newage.iep.pojos.Enclousure.Polygoncoordinate;
import com.newage.iep.pojos.checkwork.AttendanceTeam;
import com.newage.iep.serivce.Community.EnclosureService;
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
 * Created by Administrator on 2017/8/23.
 */
@Controller("enclosureAction")
public class EnclosureAction extends ActionSupport
{
    @Resource
    private EnclosureService enclosureService;//获取围栏服务层代码
    private String enclosureName;//获取围栏名称
    private int enclosureType;//获取围栏类型
    private List<String> polygonLng;//多边形经度
    private List<String> polygonLat;//多边形纬度
    private String circleRadius;//圆形半径
    private String circleCenterLng;//圆形圆心经度
    private String circleCenterLat;//圆形圆心纬度
    private String enclosureId;//围栏Id
    private String enclosureCurrentId;//围栏当前Id
    private int enclosureCurrentType;//围栏当前类型
    private String enclosureCurrentName;//围栏当前名称
    private String attendanceName;//考勤组名称
    private String enclosureDescriptions;//围栏描述
    private String result;//返回json数据
    private String status;//围栏状态

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getEnclosureDescriptions() {
        return enclosureDescriptions;
    }

    public void setEnclosureDescriptions(String enclosureDescriptions) {
        this.enclosureDescriptions = enclosureDescriptions;
    }

    public String getAttendanceName() {
        return attendanceName;
    }

    public void setAttendanceName(String attendanceName) {
        this.attendanceName = attendanceName;
    }

    public String getEnclosureCurrentName() {
        return enclosureCurrentName;
    }

    public void setEnclosureCurrentName(String enclosureCurrentName) {
        this.enclosureCurrentName = enclosureCurrentName;
    }

    public int getEnclosureCurrentType() {
        return enclosureCurrentType;
    }

    public void setEnclosureCurrentType(int enclosureCurrentType) {
        this.enclosureCurrentType = enclosureCurrentType;
    }

    public String getEnclosureCurrentId() {
        return enclosureCurrentId;
    }

    public void setEnclosureCurrentId(String enclosureCurrentId) {
        this.enclosureCurrentId = enclosureCurrentId;
    }

    public String getEnclosureId() {
        return enclosureId;
    }

    public void setEnclosureId(String enclosureId) {
        this.enclosureId = enclosureId;
    }

    public String getEnclosureName() {
        return enclosureName;
    }

    public void setEnclosureName(String enclosureName) {
        this.enclosureName = enclosureName;
    }

    public int getEnclosureType() {
        return enclosureType;
    }

    public void setEnclosureType(int enclosureType) {
        this.enclosureType = enclosureType;
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

    /**
     * 保存围栏
     * @return
     */
    public String saveEnclosure()
    {
        System.out.println("EnclosureAction saveEnclosure() enclosureName = " + enclosureName);
        Map<String,Object> map = new HashMap<String, Object>();
        if(attendanceName == null)
        {
            map.put("state",0);//state = 0时 请选择考勤组
            JSONObject json = JSONObject.fromObject(map);
            result = json.toString();
        }

        else if(status == null)
        {
            map.put("state",-2);//state = -2时 请设置围栏状态
            JSONObject json = JSONObject.fromObject(map);
            result = json.toString();
        }

        else if(enclosureName == "")
        {
            map.put("state",-1);//state = -1时围栏类型为null
            JSONObject json = JSONObject.fromObject(map);
            result = json.toString();
        }

        else if(enclosureType != 1 && enclosureType != 0)
        {
            map.put("state",1);//state = 1时围栏类型为null
            JSONObject json = JSONObject.fromObject(map);
            result = json.toString();
            System.out.println("EnclosureAction saveEnclosure() enclosureName = " + enclosureName + "enclosureType = " + enclosureType);
        }

        else if(enclosureService.saveEnclosure(enclosureName, enclosureType, attendanceName, enclosureDescriptions, status))
        {
            if(enclosureType == 0)
            {
                for(int i = 0; i < polygonLat.size(); i++)
                {
                    enclosureService.savePolygoncoordinate(enclosureName, polygonLng.get(i), polygonLat.get(i));
                    System.out.println("EnclosureAction saveEnclosure() i = " + i);
                }
                map.put("state",2);//state = 2时 围栏设置成功
                JSONObject json = JSONObject.fromObject(map);
                result = json.toString();
            }
            else if(enclosureType == 1)
            {
                enclosureService.saveCirclecoordinate(enclosureName,circleRadius,circleCenterLng,circleCenterLat);
                map.put("state",2);//state = 2时 围栏设置成功
                JSONObject json = JSONObject.fromObject(map);
                result = json.toString();
            }
            else
            {
                map.put("state",3);//state = 3时 请选择使用围栏类型
                JSONObject json = JSONObject.fromObject(map);
                result = json.toString();
            }
        }
        else
        {
            System.out.println("草里来来");
            map.put("state",4);//state = 4时 围栏设置失败，围栏名称已存在，请更改
            JSONObject json = JSONObject.fromObject(map);
            result = json.toString();
        }
        return "success";
    }

    /**
     * 获取所有围栏
     * @return
     */
    public String getAllEnclosure()
    {
        HttpServletRequest request = ServletActionContext.getRequest();
        List<Enclosure> enclosureList = enclosureService.getAllEnclosure();
        List<AttendanceTeam> attendanceList = new ArrayList<AttendanceTeam>();
        List<String> modifyNameList = new ArrayList<String>();

        //查询出所有的考勤组信息
        List checkWorks = enclosureService.getAllEnclosure();
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
        List<Enclosure> checkWorkPages = enclosureService.selectcheckWorkPages(page);
        if(enclosureList == null)
        {
            return "error";
        }
        else
        {
            for(int i = 0; i < enclosureList.size(); i++)
            {
                attendanceList.add(enclosureService.getAttendanceteamById(enclosureList.get(i).getAttendanceId()));
                modifyNameList.add(enclosureService.getModifyNameById(enclosureList.get(i).getModifyId()));
//                System.out.println("EnclosureAction getAllEnclosure() modifyName = " + modifyNameList.get(i) + " i = " + i);
//                System.out.println("EnclosureAction getAllEnclosure() attendanceName = " + attendanceList.get(i).getRolename() + " i = " + i);
            }
            request.setAttribute("enclosureList", checkWorkPages);
            request.setAttribute("attendanceList", attendanceList);
            request.setAttribute("modifyNameList", modifyNameList);
            request.setAttribute("page", page);
            request.setAttribute("pageSize",pageSize);
            return "getallenclosure";
        }
    }

    /**
     * 展示围栏
     * @return
     */
    public String showEnclosure()
    {
        HttpServletRequest request = ServletActionContext.getRequest();
        enclosureName = request.getParameter("enclosureName");
        System.out.println("EnclosureAction showEnclousre() enclosureName = " + enclosureName);
        Enclosure enclosure = enclosureService.getIdByName(enclosureName);

        System.out.println("EnclosureAction showEnclosure() enclosure = " + enclosure);
        int enclosureType = enclosure.getCoordinateType();
        String enclosureId = enclosure.getId();
        AttendanceTeam attendanceteam = enclosureService.getAttendanceteamById(enclosure.getAttendanceId());
        List<AttendanceTeam> attendanceteamList = enclosureService.getAllAttendance();

        if(enclosureType == 0)
        {
            List<Polygoncoordinate> polygoncoordinateList = enclosureService.getPolygoncoordinate(enclosureId);
            Polygoncoordinate polygoncoordinate = polygoncoordinateList.get(0);
            Circlecoordinate circlecoordinate = new Circlecoordinate();
            circlecoordinate.setCircleLng("0");
            circlecoordinate.setCircleLat("0");
            circlecoordinate.setCircleRadius("0");
            request.setAttribute("enclosure", enclosure);
            request.setAttribute("attendanceteamList",attendanceteamList);
            request.setAttribute("attendanceteamName", attendanceteam.getRolename());
            request.setAttribute("circlecoordinate", circlecoordinate);
            request.setAttribute("polygoncoordinate", polygoncoordinate);
            request.setAttribute("polygoncoordinateList", polygoncoordinateList);
            return "showEnclosure";
        }
        else if(enclosureType == 1)
        {
            List<Circlecoordinate> circlecoordinateList = enclosureService.getCirclecoordinate(enclosureId);
            Polygoncoordinate polygoncoordinate = new Polygoncoordinate();
            polygoncoordinate.setPolygonLng("0");
            polygoncoordinate.setPolygonLat("0");
            Circlecoordinate circlecoordinate = circlecoordinateList.get(0);
            System.out.println("Enclosure showEnclosure() circlecoordinate = " + circlecoordinate.getId());
            request.setAttribute("attendanceteamList",attendanceteamList);
            request.setAttribute("attendanceteamName", attendanceteam.getRolename());
            System.out.println("EnclosureAction showEnclosure() attendanceteamName = " + attendanceteam.getRolename());
            request.setAttribute("polygoncoordinate",polygoncoordinate);
            request.setAttribute("enclosure", enclosure);
            request.setAttribute("circlecoordinate", circlecoordinate);
            return "showEnclosure";
        }
        else
        {
            return "error";
        }
    }

    /**
     * 删除所有围栏
     * @return
     */
    public String deleteEnclosure()
    {
        HttpServletRequest request = ServletActionContext.getRequest();
        enclosureCurrentId = request.getParameter("enclosureCurrentId");
        enclosureCurrentType =Integer.parseInt(request.getParameter("enclosureCurrentType"));
        //System.out.println(request.getParameter("enclosureCurrentType"));
        System.out.println("EnclosureAction deleteEnclosure() enclosureId = " + enclosureCurrentId + "enclosureType = " + enclosureCurrentType);
        if(enclosureService.deleteEnclosure(enclosureCurrentId,enclosureCurrentType))
        {
            return "deleteEnclosure";
        }
        else
        {
            return "error";
        }
    }

    /**
     * 更新围栏
     * @return
     */
    public String updateEnclosure()
    {
        System.out.println("EnclosureAction updateEnclosure() enclosureCurrentName = " + enclosureCurrentName
        + " enclosureCurrentType = " + enclosureCurrentType);
        Enclosure enclosure = enclosureService.getIdByName(enclosureCurrentName);
        String id;
        if(enclosure != null)
        {
            id = enclosure.getId();
            enclosureService.deleteEnclosure(id,enclosureCurrentType);
        }
        if(enclosureService.saveEnclosure(enclosureName, enclosureType, attendanceName, enclosureDescriptions,status))
        {
            if(enclosureType == 0)
            {
                for(int i = 0; i < polygonLat.size(); i++)
                {
                    enclosureService.savePolygoncoordinate(enclosureName, polygonLng.get(i), polygonLat.get(i));
                    System.out.println("EnclosureAction saveEnclosure() i = " + i);
                }
                return "updateEnclosure";
            }
            else if(enclosureType == 1)
            {
                enclosureService.saveCirclecoordinate(enclosureName,circleRadius,circleCenterLng,circleCenterLat);
                return "updateEnclosure";
            }
            else
            {
                return "error";
            }
        }
        else
        {
            System.out.println("草里来来");
            return "error";
        }
    }

    public String toCheckWork(){

        HttpServletRequest request = ServletActionContext.getRequest();
        //查询出所有的考勤组信息
        List checkWorks = enclosureService.getAllEnclosure();
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
        request.setAttribute("page", page);
        //分页需要的起始与终止位置
        int start = (pno-1)* pageSize;
        int end = pageSize;
        page.setStart(start);
        page.setEnd(end);
        //依据分页查询出条件  分页后的考勤组
        List<Enclosure> checkWorkPages = enclosureService.selectcheckWorkPages(page);
        //返回考勤信息
        request.setAttribute("checkWorks",checkWorkPages);
        request.setAttribute("pageSize",pageSize);
        return "toCheckWork";
    }

    public String changePage()
    {
        HttpServletRequest request = ServletActionContext.getRequest();
        List<AttendanceTeam> attendanceNameList = enclosureService.getAllAttendance();
        request.setAttribute("attendanceNameList",attendanceNameList);
        return "changepage";
    }
}
