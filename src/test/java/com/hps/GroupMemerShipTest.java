package com.hps;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GroupMemerShipTest {

    @Resource
    ProcessEngine processEngine;
    @Test
    public void test1(){
        IdentityService identityService=processEngine.getIdentityService();
        identityService.createMembership("zhanjuan","user");

    }

    @Test
    public void test2(){
        //查询组内的用户
        IdentityService identityService=processEngine.getIdentityService();
        List<User> list=identityService.createUserQuery().memberOfGroup("admin").list();
        for (User item:list
             ) {
            System.out.println("............"+item.getFirstName()+item.getLastName());
        }
    }

    @Test
    public void test3(){
        //查询用户所属的组
        IdentityService identityService=processEngine.getIdentityService();
       List<Group> list=identityService.createGroupQuery().groupMember("zhanjuan").list();
        for (Group item:list){
            System.out.println(",,,,,,,,,,,,,,"+item.getId());
        }
    }
}
