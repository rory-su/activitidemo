package com.hps;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.identity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserTest {


    @Resource
    ProcessEngine processEngine;
    @Test
    public void test1(){
        //创建用户
        IdentityService identityService=processEngine.getIdentityService();
        User user= identityService.newUser("zhanjuan");
        user.setFirstName("zhan");
        user.setLastName("juan");
        user.setEmail("136471676@qq.com");
        identityService.saveUser(user);
    }
    @Test
    public void test2(){
        //查询用户
        IdentityService identityService=processEngine.getIdentityService();
        User user=identityService.createUserQuery().userId("sujinquan").singleResult();
        System.out.println(user.getFirstName()+user.getLastName());
    }
    @Test
    public void test3(){
        //删除用户
        IdentityService identityService=processEngine.getIdentityService();
        identityService.deleteUser("test");
        User user=identityService.createUserQuery().userId("test").singleResult();
        if(user==null){
            System.out.println("用户已经被删除");
        }
    }
}
