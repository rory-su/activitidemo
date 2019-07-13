package com.hps;

import com.hps.dao.TestMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivitidemoApplicationTests {
    @Autowired
    private TestMapper testMapper;
    @Test
    public void contextLoads() {

    com.hps.model.Test test=testMapper.selectByPrimaryKey(1);
    System.out.println(test.getName());

    }

}
