package com.nowcoder.community;

import com.nowcoder.community.dao.AlphaDao;
import com.nowcoder.community.dao.AlphaDaoMyBatisImpl;
import com.nowcoder.community.service.AlphaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;

import java.text.SimpleDateFormat;
import java.util.Date;

//@SpringBootTest
//测试时需要和正式环境一样，使用相同的配置类。 这里就将CommunityApplication.class加进来，使用该类做配置类
//@ContextConfiguration(classes = CommunityApplication.class)
//class CommunityApplicationTests  {
//
//    @Autowired
//    AlphaDao alphaDao;
//
//    @Test
//    void contextLoads() {
//        System.out.println(alphaDao.select());
//    }
//
//}
@SpringBootTest
class CommunityApplicationTests implements ApplicationContextAware {

    @Test
    void contextLoads() {
    }

    private  ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Test
    //获取bean的方式一
    public void testApplicationContext1() {
        System.out.println(applicationContext);
        AlphaDao alphaDao = applicationContext.getBean(AlphaDao.class);
        System.out.println(alphaDao.select());
    }

    @Test
    //获取bean的方式二
    public void testApplicationContext2() {
        System.out.println(applicationContext);
        AlphaDao alphaDao = applicationContext.getBean(AlphaDaoMyBatisImpl.class);
        System.out.println(alphaDao.select());
    }

    @Test
    //获取bean的方式三,默认bean的名字是类名的首字母小写,当然也可以自定义bean的名称，如@Repository("apbm")
    public void testApplicationContext3() {
        System.out.println(applicationContext);
        AlphaDao alphaDao = (AlphaDao) applicationContext.getBean("alphaDaoMyBatisImpl");
        System.out.println(alphaDao.select());
    }

    //获取bean的方式4,通过自动装配获取
    @Autowired
    AlphaDaoMyBatisImpl alphaDaoMyBatis;

    @Test
    public void testApplicationContext4() {
        System.out.println(alphaDaoMyBatis.select());
    }

    @Test
    public  void testBeanManagement(){
        AlphaService alphaService = applicationContext.getBean(AlphaService.class);
    }

    @Test
    public void testBeanConfig(){
        SimpleDateFormat simpleDateFormat = applicationContext.getBean(SimpleDateFormat.class);
        System.out.println(simpleDateFormat.format(new Date()));
    }

    @Autowired
    @Qualifier("alohaDaoHibernateImpl")
    private AlphaDao alphaDao;


}
