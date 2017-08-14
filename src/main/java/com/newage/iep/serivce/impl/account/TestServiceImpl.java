package com.newage.iep.serivce.impl.account;

import com.newage.iep.business.dao.GenericHibernateDAO;
import com.newage.iep.pojos.Seq;
import com.newage.iep.serivce.account.TestService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("testService")
public class TestServiceImpl extends GenericHibernateDAO implements TestService {
    @Override
    public void findList() {
        List<Seq> list = this.createQuery(" from Seq ").list();
        System.out.println(list.size());
    }
}
