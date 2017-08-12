package com.newage.iep.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;


/**
 * 获取系统内配置文件信息
 * 
 * @author
 */
public class IepConfig {
	private static String default_config = "/iep.properties";//默认属性文件名称
	
	private static Properties mConfig;
	
	private static Log log = LogFactory.getLog(IepConfig.class);
	
    /*
     * Static block run once at class loading
     *
     * We load the default properties and any custom properties we find
     */	
	static{
		 mConfig = new Properties();
		 try{
			 Class config_class = Class.forName("com.newage.iep.config.IepConfig");
			 InputStream is = config_class.getResourceAsStream(default_config);
			 if(is!=null){
				 mConfig.load(is);
				 log.info("successfully loaded default properties.");
			 }else{
				 log.info("no  properties file found.");
			 }
		 }catch(Exception e){
			 e.printStackTrace();
		 }
	}	
    /**
     * 在属性文件中根据key获取 属性值
     * @param     key Name of the property
     * @return    String Value of property requested, null if not found
     */
    public static String getProperty(String key) {
        log.debug("Fetching property ["+key+"="+mConfig.getProperty(key)+"]");
        return mConfig.getProperty(key);
    }	
    /**
     * 在属性文件中根据key获取 属性值
     * @param     key Name of the property
     * @param     defaultValue Default value of property if not found     
     * @return    String Value of property requested or defaultValue
     */
    public static String getProperty(String key, String defaultValue) {
        log.debug("Fetching property ["+key+"="+mConfig.getProperty(key)+",defaultValue="+defaultValue+"]");
        String value = mConfig.getProperty(key);
        if(value == null)
          return defaultValue;      
        return value;
    }		
    /**
     * 在属性文件中根据key获取 属性值boolean型
     * @param key -属性
     * return boolean
     */
    public static boolean getBooleanProperty(String key) {
        return getBooleanProperty(key,false);
    }
    /**
     * 在属性文件中根据key获取 属性值boolean型
     * 
     * @param key  -属性
     * @param defaultValue -如果不存在的 默认值
     * @return boolean
     */
    public static boolean getBooleanProperty(String key, boolean defaultValue) {
        // get the value first, then convert
        String value = IepConfig.getProperty(key);

        if(value == null)
            return defaultValue;

        return (new Boolean(value)).booleanValue();
    }	
    /**
     * 在属性文件中根据key获取 属性值int型
     * 
     * @param key  -属性
     * @return int
     */
    public static int getIntProperty(String key) {
        return getIntProperty(key, 0);
    }
    /**
     * 在属性文件中根据key获取 属性值int型
     * 
     * @param key
     * @param defaultValue
     * @return int
     */
    public static int getIntProperty(String key, int defaultValue) {
        // get the value first, then convert
        String value = IepConfig.getProperty(key);

        if (value == null||!value.matches("\\d+"))
            return defaultValue;
        return (new Integer(value)).intValue();
    }
    /**
     * 获取所属性文件中的key集合
     * 
     * @return Enumeration A list of all keys
     **/
    public static Enumeration keys() {
        return mConfig.keys();
    } 	
}
