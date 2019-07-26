package com.hps;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.identity.Group;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GroupTest {
     @Resource
     ProcessEngine processEngine;
     @Test
    public void test1(){
         //创建组
         IdentityService identityService=processEngine.getIdentityService();
         Group group=identityService.newGroup("user");
         group.setId("user");
         group.setName("普通用户1");
         group.setType("user");
         try{
             identityService.saveGroup(group);
         }catch (Exception e){
             e.printStackTrace();
         }
         System.out.println("hello world");
     }
     @Test
    public void test2(){
     //查询组
     IdentityService identityService=processEngine.getIdentityService();
     List<Group> group=identityService.createGroupQuery().groupId("admin").groupId("user").list();
     System.out.println("fefefe"+group.size());
     //assertEquals(2,group.size());
    // if(group==null){
      //   System.out.println("没有该组");
    // }else{
        // System.out.println(group.getName()+group.getType());
    // }
     }
     @Test
     public void test3(){
         //删除组
         IdentityService identityService=processEngine.getIdentityService();
         identityService.deleteGroup("user");
     }
     @Test
    public void test4(){

     }

}
