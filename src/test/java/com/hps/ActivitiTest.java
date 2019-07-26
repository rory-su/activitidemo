package com.hps;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ActivitiTest {

    @Resource
    RuntimeService runtimeService;
    @Resource
    TaskService taskService;
    @Test
    //@Deployment(resources = {"classpath:/processes/MyProcess.bpmn"})
    public void test(){
        ProcessInstance  processInstance=runtimeService.startProcessInstanceByKey("myProcess");
        Task task=taskService.createTaskQuery().taskCandidateUser("sujinquan").singleResult();
        taskService.claim(task.getId(),"sujinquan");
        taskService.complete(task.getId());
    }
    @Test
    public void startprocess(){
        //启动一个流程实例
        ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("ProcessTwo");
    }
    @Test
    public void candidateGroup(){
        //查询组的代办列表
        List<Task> task=taskService.createTaskQuery().taskCandidateGroup("admin").list();
        for (Task item: task) {
            System.out.println("fewewfefwefwefw"+item.getId());
        }
    }

    @Test
    public void candidateUser(){
        // 查询某个人的代办列表
       List<Task> task=taskService.createTaskQuery().taskCandidateUser("zhanjuan").list();
       System.out.println("ieieofeiof"+task.size());
        for (Task item :task) {
           System.out.println("fdfeofowiefowejfoiewfjoweifwo:"+item.getId());
        }
    }
    @Test
    public void getTasktoUser(){
        //分发任务给某个用户
        taskService.claim("25005","sujiqnuan");
    }
    @Test
    public void doTask(){
        taskService.complete("25005");
    }

}
