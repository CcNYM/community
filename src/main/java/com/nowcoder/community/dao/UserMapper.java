package com.nowcoder.community.dao;

import com.nowcoder.community.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.ResponseBody;


@Mapper //mybatis标识一个bean的方式， UserMapper代表与一个实体和数据库交互的接口
public interface UserMapper {

    User selectById(int id);

    User selectByName(String username);

    User selectByEmail(String email);

    //这个int代表收影响的行数，下面的同理
    int insertUser(User user);

    int updateStatus(int id, int status);

    int updateHeader(int id, String headerUrl);

    int updatePassword(int id, String password);
}
