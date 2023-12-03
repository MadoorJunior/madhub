package com.madoor.madhub.util;

public class RedisKeyUtils {
    public static final String MAP_KEY_USER_POST_LIKED = "MAP_USER_POST_LIKED";
    public static final String MAP_KEY_POST_USER_LIKED = "MAP_POST_USER_LIKED";
    public static final String MAP_KEY_POST_LIKED_COUNT = "MAP_POST_LIKED_COUNT";
    public static final String MAP_KEY_USER_COMMENT_LIKED = "MAP_USER_COMMENT_LIKED";
    public static final String MAP_KEY_COMMENT_LIKED_COUNT = "MAP_USER_COMMENT_COUNT";
    public static final String MAP_KEY_POST_VIEW_COUNT = "MAP_POST_VIEW_COUNT";
    public static String getUserLikePostKey(String userId){
        return MAP_KEY_USER_POST_LIKED + "::" + userId;
    }
    public static String getPostLikeUserKey(String postId){
        return MAP_KEY_POST_USER_LIKED + "::" + postId;
    }
    public static String getUserLikeCommentKey(String userId){
        return MAP_KEY_USER_COMMENT_LIKED + "::" + userId;
    }
}
