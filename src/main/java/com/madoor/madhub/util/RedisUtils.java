package com.madoor.madhub.util;

import com.madoor.madhub.model.dto.LikePostDTO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class RedisUtils {
    @Resource
    private RedisTemplate redisTemplate;
    public void likePost(String postId, String userId){
        redisTemplate.opsForHash().increment(RedisKeyUtils.MAP_KEY_POST_LIKED_COUNT, postId, 1);
        redisTemplate.opsForSet().add(RedisKeyUtils.getUserLikePostKey(userId), postId);
        redisTemplate.opsForSet().add(RedisKeyUtils.getPostLikeUserKey(postId), userId);
    }

    public void unlikePost(String postId, String userId){
        redisTemplate.opsForHash().increment(RedisKeyUtils.MAP_KEY_POST_LIKED_COUNT, postId, -1);
        redisTemplate.opsForSet().remove(RedisKeyUtils.getUserLikePostKey(userId), postId);
        redisTemplate.opsForSet().remove(RedisKeyUtils.getPostLikeUserKey(postId), userId);
    }

    public void likeComment(String commentId, String userId){
        redisTemplate.opsForHash().increment(RedisKeyUtils.MAP_KEY_COMMENT_LIKED_COUNT, commentId, 1);
        redisTemplate.opsForSet().add(RedisKeyUtils.getUserLikeCommentKey(userId), commentId);
    }
    public void unlikeComment(String commentId, String userId){
        redisTemplate.opsForHash().increment(RedisKeyUtils.MAP_KEY_COMMENT_LIKED_COUNT, commentId, -1);
        redisTemplate.opsForSet().remove(RedisKeyUtils.getUserLikeCommentKey(userId), commentId);
    }
    public Integer getLikes(Long postId){
        Object o = redisTemplate.opsForHash().get(RedisKeyUtils.MAP_KEY_POST_LIKED_COUNT, postId.toString());
        return Integer.parseInt(o!=null?o.toString():"0");
    }
    public Boolean isUserLiked(Integer userId, Long postId){
        return redisTemplate.opsForSet().isMember(RedisKeyUtils.getUserLikePostKey(userId.toString()), postId.toString());
    }
    public Integer getCommentLikes(Integer commentId){
        Object o = redisTemplate.opsForHash().get(RedisKeyUtils.MAP_KEY_COMMENT_LIKED_COUNT, commentId.toString());
        return Integer.parseInt(o!=null?o.toString():"0");
    }
    public Boolean isCommentLiked(Integer userId, Integer commentId){
        return redisTemplate.opsForSet().isMember(RedisKeyUtils.getUserLikeCommentKey(userId.toString()), commentId.toString());
    }
    public Integer increPostView(Long postId){
        redisTemplate.opsForHash().increment(RedisKeyUtils.MAP_KEY_POST_VIEW_COUNT, postId.toString(), 1);
        Object o = redisTemplate.opsForHash().get(RedisKeyUtils.MAP_KEY_POST_VIEW_COUNT, postId.toString());
        return Integer.parseInt(o!=null?o.toString():"0");
    }
}
