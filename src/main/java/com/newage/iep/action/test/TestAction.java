package com.newage.iep.action.test;

import com.newage.iep.serivce.account.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import static com.opensymphony.xwork2.Action.SUCCESS;

/**
 * Created by Administrator on 2017-08-06.
 */
public class TestAction {

    @Autowired
    @Qualifier("testService")
    private TestService testServiceImpl;

    private String testValue ;

    private String rresult ;

    public String index(){
        testServiceImpl.findList();
        setTestValue("222");
        return SUCCESS;
    }

    public String testAjax(){
        System.out.println(getRresult());
        setRresult("1231");
        return SUCCESS;
    }

    public String getTestValue() {
        return testValue;
    }

    public void setTestValue(String testValue) {
        this.testValue = testValue;
    }

    public String getRresult() {
        return rresult;
    }

    public void setRresult(String rresult) {
        this.rresult = rresult;
    }
}
