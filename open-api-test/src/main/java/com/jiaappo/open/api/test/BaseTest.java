package com.jiaappo.open.api.test;

/**
 * @Author: lee
 * @version:1.0.0
 * @Date: 2019/6/28 11:18
 **/
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationTest.class,webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseTest {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
}
