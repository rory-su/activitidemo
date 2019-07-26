package com.hps;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;
import java.io.FileInputStream;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NewProcessTest {
    @Resource
    RepositoryService repositoryService;
    @Test
    public void InitProcessByClassPath(){
        //classpatch 方式部署资源
        String bpmnPath="processes/ProcessNew.bpmn";
        String bpmnPng="processes/ProcessNew.png";
        DeploymentBuilder deploymentBuilder=repositoryService.createDeployment();
        deploymentBuilder.addClasspathResource(bpmnPath);
        deploymentBuilder.addClasspathResource(bpmnPng);
        deploymentBuilder.deploy();
    }
    @Test
    public void InitProcessByInputStream() throws  Exception{
        String bpmnpath="F:\\WORKPLACE\\ideaworkplace\\activitidemo\\src\\main\\resources\\processes\\ProcessNew.bpmn";
        FileInputStream fileInputStream=new FileInputStream(bpmnpath);
        repositoryService.createDeployment().addInputStream("ProcessNew.bpmn",fileInputStream);
    }
    @Test
    public void InitProcessByText(){
        //以字符串的方式部署
        String bpmnText="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<definitions xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:activiti=\"http://activiti.org/bpmn\" xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" xmlns:omgdc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:omgdi=\"http://www.omg.org/spec/DD/20100524/DI\" typeLanguage=\"http://www.w3.org/2001/XMLSchema\" expressionLanguage=\"http://www.w3.org/1999/XPath\" targetNamespace=\"http://www.activiti.org/test\">\n" +
                "  <process id=\"ProcessNew\" name=\"My process\" isExecutable=\"true\">\n" +
                "    <startEvent id=\"startevent1\" name=\"Start\"></startEvent>\n" +
                "    <userTask id=\"usertask1\" name=\"User Task\" activiti:candidateGroups=\"user\"></userTask>\n" +
                "    <endEvent id=\"endevent1\" name=\"End\"></endEvent>\n" +
                "    <sequenceFlow id=\"flow1\" sourceRef=\"startevent1\" targetRef=\"usertask1\"></sequenceFlow>\n" +
                "    <sequenceFlow id=\"flow2\" sourceRef=\"usertask1\" targetRef=\"endevent1\"></sequenceFlow>\n" +
                "  </process></definitions>";
        repositoryService.createDeployment().addString("ProcessNew.bpmn",bpmnText).deploy();
    }

    @Test
    public void CheckIfSuccess(){
        //验证流程定义是否部署成功
        ProcessDefinitionQuery  pdq=repositoryService.createProcessDefinitionQuery();
        long count=pdq.processDefinitionKey("ProcessNew").count();
        System.out.println("fefefefe"+count);
    }
    @Test
    public void readPng(){
        //读取图片文件
        ProcessDefinitionQuery  pdq=repositoryService.createProcessDefinitionQuery();
        List<ProcessDefinition> processDefinition=pdq.processDefinitionId("ProcessNew:1:62511").list();
        for (ProcessDefinition item:processDefinition) {
            String diagramresourcename=item.getDiagramResourceName();
            System.out.println("pictury:"+diagramresourcename);
        }

    }
}
