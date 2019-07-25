package com.hps;

import com.hps.dao.UserMapper;
import com.hps.model.User;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.test.ActivitiTestCase;
import org.activiti.engine.test.Deployment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ActivitidemoApplicationTests extends ActivitiTestCase {
    @Autowired
    private UserMapper userMapper;
    @Resource
    ProcessEngine processEngine;
    @Test
    public void contextLoads() {
    User user=userMapper.selectByPrimaryKey(1);
    if(user!=null){
        System.out.println(user.getName());
    }else{
        System.out.println("没有找到");
    }
    }
    //用户管理API
    @Test
    @Deployment
    public void tese1(){
        IdentityService identityService= processEngine.getIdentityService();
        org.activiti.engine.identity.User user=identityService.newUser("3");
        user.setFirstName("su");
        user.setLastName("jinquan");
        user.setEmail("133232@qq.com");
        identityService.saveUser(user);
        //检验是否创建成功
        org.activiti.engine.identity.User userindb=identityService.createUserQuery().userId("8989").singleResult();
        assertNotNull(user);
        //删除用户
        identityService.deleteUser("3");
   }
    @Test
    @Deployment
   public void test2(){
     //创建组
      IdentityService identityService=processEngine.getIdentityService();
   }

}
