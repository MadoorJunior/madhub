package com.madoor.madhub.service;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.madoor.madhub.entity.FollowPost;
import com.madoor.madhub.mapper.CommentMapper;
import com.madoor.madhub.mapper.PostMapper;
import com.madoor.madhub.model.dto.CommentDTO;
import com.madoor.madhub.model.dto.LikePostDTO;
import com.madoor.madhub.model.vo.CommentVo;
import com.madoor.madhub.model.vo.ResultVO;
import com.madoor.madhub.util.IpUtil;
import com.madoor.madhub.util.RedisUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentMapper commentMapper;
    private final PostMapper postMapper;
    private final RedisUtils redisUtils;
    public ResultVO<?> comment(CommentDTO commentDTO, HttpServletRequest request) {
        String ipAddress = IpUtil.getIpAddress(request);
        FollowPost followPost = new FollowPost().setContent(commentDTO.getContent())
                .setPostId(commentDTO.getPostId()).setReplyTo(commentDTO.getReplyTo())
                .setLikes(0).setUserId(commentDTO.getUserId()).setIpAddress(ipAddress);
        commentMapper.insert(followPost);
        postMapper.increCommentCount(followPost.getPostId());
        return ResultVO.ok();
    }
    public ResultVO<?> getComments(Integer postId){
        int loginIdAsInt = StpUtil.getLoginIdAsInt();
        List<CommentVo> commentVoList = commentMapper.getCommentByPostId(postId);
        for (CommentVo commentVo : commentVoList) {
            Integer commentLikes = redisUtils.getCommentLikes(commentVo.getId());
            commentVo.setLikes(commentLikes);
            commentVo.setIsLiked(redisUtils.isCommentLiked(loginIdAsInt, commentVo.getId()));
        }
        return ResultVO.ok(commentVoList);
    }
    public ResultVO<?> likeComment(LikePostDTO likePostDTO){
        if (likePostDTO.getIsLiked()){
            redisUtils.unlikeComment(likePostDTO.getPostId(), likePostDTO.getUserId());
        }else {
            redisUtils.likeComment(likePostDTO.getPostId(), likePostDTO.getUserId());
        }
        return ResultVO.ok();
    }
}
