package com.madoor.madhub.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.madoor.madhub.entity.Post;
import com.madoor.madhub.model.vo.PostVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostMapper extends BaseMapper<Post> {
    List<PostVo> selectPostList(IPage<PostVo> page);
    Integer increCommentCount(Integer postId);
}
