package com.nowcoder.community.dao;

import com.nowcoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {

    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);

    //@Param，即为参数起一个别名
    //如果方法只有一个参数，且在<if>里使用，则必须加别名
    int selectDiscussPostsRows(@Param("userId") int userId);

    /*
    * 增加帖子的方法
    * */
    int insertDiscussPost(DiscussPost discussPost);

    /*
    * 查询
    * */
    DiscussPost selectDiscussPostById(int id);

    int updateCommentCount(int id, int commentCount);

}
