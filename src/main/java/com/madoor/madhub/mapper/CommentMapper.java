package com.madoor.madhub.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.madoor.madhub.entity.FollowPost;
import com.madoor.madhub.model.vo.CommentVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentMapper extends BaseMapper<FollowPost> {
    List<CommentVo> getCommentByPostId(Integer postId);
}
