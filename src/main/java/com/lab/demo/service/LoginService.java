package com.lab.demo.service;

import com.lab.demo.entity.Usr;
import com.lab.demo.repository.UsrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class LoginService {

    @Autowired
    private UsrRepository usrRepository;

    /**
     * 查询数据库判断用户名密码是否匹配
     * @return
     */
    @Transactional
    public Usr validLogin(String usrName, String inputpswd) {

//        System.out.println(usrName);
        //从数据库取出该用户数据
        Usr usr = usrRepository.findByUsrName(usrName);
//        Usr usr = usrRepository.findByEmail("root@163.com");

        if (usr == null) { //没有该用户
            return null;
        }
        else if (inputpswd.equals(usr.getPswd())) { //用户名密码匹配
//            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaa");
            return usr;
        }

        return null;
    }
}
