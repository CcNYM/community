package com.nowcoder.community;

import com.nowcoder.community.service.AlphaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
public class TrainsactionTests {

    @Autowired
    AlphaService alphaService;

    //故意出错，验证事务的回滚是否成功
    @Test
    public void testSave1(){
        Object obj = alphaService.save1();
        System.out.println(obj);
    }
}
