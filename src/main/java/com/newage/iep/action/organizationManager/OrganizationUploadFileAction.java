package com.newage.iep.action.organizationManager;

import com.newage.iep.serivce.OrganizationManager.OrganizationManagerService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class OrganizationUploadFileAction extends ActionSupport {

    //上传文件存放路径
    private final static String UPLOADDIR = "/upload";
    //上传文件集合
    private List<File> file;
    //上传文件名集合
    private List<String> fileFileName;
    //上传文件内容类型集合
    private List<String> fileContentType;
    //文件保存路径
    private String savePath;
    @Autowired
    @Qualifier("organizationManagerService")
    OrganizationManagerService organizationManagerService;

    public List<File> getFile() {
        return file;
    }
    public void setFile(List<File> file) {
        this.file = file;
    }

    public List<String> getFileFileName() {
        return fileFileName;
    }
    public void setFileFileName(List<String> fileFileName) {
        this.fileFileName = fileFileName;
    }

    public List<String> getFileContentType() {
        return fileContentType;
    }
    public void setFileContentType(List<String> fileContentType) {
        this.fileContentType = fileContentType;
    }

    /**
     * 返回上传文件保存的位置
     *
     * @return
     * @throws Exception
     */
    public String getSavePath() throws Exception {
        return ServletActionContext.getServletContext().getRealPath(savePath);
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }
    public String upload() throws Exception {
        for (int i = 0; i < file.size(); i++) {
            //循环上传每个文件
            uploadFile(i);
        }
        return SUCCESS;
    }
    //执行上传功能
    private void uploadFile(int i) throws FileNotFoundException, IOException {
        try {
            InputStream in = new FileInputStream(file.get(i));
            String dir = ServletActionContext.getRequest().getRealPath(UPLOADDIR);
            File fileLocation = new File(dir);
            //此处也可以在应用根目录手动建立目标上传目录
            if(!fileLocation.exists()){
                boolean isCreated  = fileLocation.mkdir();
                if(!isCreated) {
                    System.out.println("创建文件夹失败");
                    //目标上传目录创建失败,可做其他处理,例如抛出自定义异常等,一般应该不会出现这种情况。
                }
            }
            String fileName=this.getFileFileName().get(i);
            File uploadFile = new File(dir, fileName);

            OutputStream out = new FileOutputStream(uploadFile);

            byte[] buffer = new byte[1024 * 1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            in.close();
            out.close();


            //保存文件路径处理模块
            String fn=fileFileName.get(i);//附件名称
            setSavePath(dir+"\\"+fn);//附件路径
            //获取表单中 组织id
            ActionContext context = ActionContext.getContext();
            //key是表单输入的name属性值，value是输入的值
            Map<String, Object> map = context.getParameters();

            Object[] obj = (Object[]) map.get("cmpId");
            String str1=Arrays.toString(obj);
            String cmpId=str1.substring(1,str1.length()-1);//组织id

            organizationManagerService.addAccessoryForOrganization(fn,savePath,cmpId);

        } catch (FileNotFoundException ex) {
            System.out.println("上传失败!");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("上传失败!");
            ex.printStackTrace();
        }
    }
}