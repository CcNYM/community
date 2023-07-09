package com.nowcoder.community;

import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.dao.LoginTicketMapper;
import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.LoginTicket;
import com.nowcoder.community.entity.User;
import lombok.Data;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CommunityApplication.class)
@SpringBootTest
public class MapperTests {

    //使用mybatis和springboot整合后，直接就可以通过自动注入获取到mapper对象，无需再创建sqlsession用于获取mapper对象。
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Autowired
    private LoginTicketMapper loginTicketMapper;

    @Test
    public void testSelectUser(){
        User user = userMapper.selectById(101);
        System.out.println(user);
    }

    @Test
    public void testUpdataStatus(){
        int res = userMapper.updateStatus(101,1);
        System.out.println(res);
    }

    @Test
    public void testInsertUser(){
        User user = new User();
        user.setUsername("test");
        user.setPassword("123456");
        user.setSalt("abc");
        user.setEmail("test@qq.com");
        user.setHeaderUrl("http://www.nowcoder.com/101.png");
        user.setCreateTime(new Date());

        int rows = userMapper.insertUser(user);
        System.out.println(rows);
        System.out.println(user.getId());
    }

    @Test
    public void testSeletPosts(){
        List<DiscussPost> list = discussPostMapper.selectDiscussPosts(149,0,10);
        for(DiscussPost post : list){
            System.out.println(post);
        }

        int rows = discussPostMapper.selectDiscussPostsRows(149);
        System.out.println(rows);
    }

    @Test
    public void testInsertLoginTicket(){
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setUserId(101);
        loginTicket.setTicket("abc");
        loginTicket.setStatus(0);
        loginTicket.setExpired(new Date(System.currentTimeMillis() + 1000 * 60));

        loginTicketMapper.insertLoginTicket(loginTicket);
    }

    @Test
    public void testSelectLoginTicket(){
        LoginTicket loginTicket = loginTicketMapper.selectByTicket("abc");
        System.out.println(loginTicket);

        loginTicketMapper.updataStatus("abc",1);
        loginTicket = loginTicketMapper.selectByTicket("abc");
        System.out.println(loginTicket);
    }

    @Test
    public void testInsertDiscussPost(){
        DiscussPost discussPost = new DiscussPost();
        discussPost.setUserId(1);
        discussPost.setCreateTime(new Date());
        discussPost.setStatus(1);
        discussPost.setContent("这是一个测试");
        discussPost.setScore(100);
        discussPost.setTitle("test");
        discussPost.setType(1);
        int res = discussPostMapper.insertDiscussPost(discussPost);
        System.out.println("插入成功");
    }

}
