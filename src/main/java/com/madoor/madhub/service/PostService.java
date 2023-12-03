package com.madoor.madhub.service;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.net.Ipv4Util;
import cn.hutool.http.server.HttpServerRequest;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.madoor.madhub.config.MinioProperties;
import com.madoor.madhub.entity.Post;
import com.madoor.madhub.mapper.PostMapper;
import com.madoor.madhub.model.dto.LikePostDTO;
import com.madoor.madhub.model.dto.PostPageQueryDTO;
import com.madoor.madhub.model.dto.PostSaveDTO;
import com.madoor.madhub.model.vo.PostVo;
import com.madoor.madhub.model.vo.QueryPageVO;
import com.madoor.madhub.model.vo.ResultVO;
import com.madoor.madhub.util.IpUtil;
import com.madoor.madhub.util.MinioUtil;
import com.madoor.madhub.util.RedisUtils;
import io.minio.ObjectWriteResponse;
import io.minio.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sun.net.util.IPAddressUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostMapper postMapper;
    private final MinioProperties minioProperties;
    private final RedisUtils redisUtils;
    public ResultVO<?> savePost(PostSaveDTO postSaveDTO, HttpServletRequest request){
        int loginIdAsInt = StpUtil.getLoginIdAsInt();
        String ipAddress = IpUtil.getIpAddress(request);
        String ipSource = IpUtil.getIpSource(ipAddress);
        Post post = Post.builder().content(postSaveDTO.getContent())
                .isAnonymous(postSaveDTO.getIsAnonymous()).media(postSaveDTO.getMedia())
                .userId(loginIdAsInt)
                .thumbUp(0).sucks(0).isDeleted(false).comment(0).forward(0).view(0)
                .ipAddress(ipAddress).ipSource(ipSource).build();
        System.out.println(post);
        postMapper.insert(post);
        return ResultVO.ok();
    }

    public ResultVO<?> getPostList(PostPageQueryDTO postPageQueryDTO) {
//        Page<Post> postPage = new Page<>(postPageQueryDTO.getCurPage(), postPageQueryDTO.getPageSize());
//        postMapper.selectPage(postPage,null);
//        return ResultVO.ok(
//                QueryPageVO.<Post>builder()
//                        .count(postPage.getTotal())
//                        .pageSize(postPage.getSize())
//                        .curPage(postPage.getCurrent())
//                        .hasNext(postPage.hasNext()).hasPre(postPage.hasPrevious())
//                        .records(postPage.getRecords()).build()
//        );
        int loginIdAsInt = StpUtil.getLoginIdAsInt();
        Page<PostVo> postPage = new Page<>(postPageQueryDTO.getCurPage(), postPageQueryDTO.getPageSize());
        List<PostVo> postVos = postMapper.selectPostList(postPage);
        //处理点赞数字
        for (PostVo postVo : postVos) {
            Long postId = postVo.getId();
            Integer likes = redisUtils.getLikes(postId);
            postVo.setThumbUp(likes);
            Boolean userLiked = redisUtils.isUserLiked(loginIdAsInt, postId);
            postVo.setIsLiked(userLiked);
            postVo.setView(redisUtils.increPostView(postId));
        }
        return ResultVO.ok(
                QueryPageVO.<PostVo>builder()
                        .count(postPage.getTotal())
                        .pageSize(postPage.getSize())
                        .curPage(postPage.getCurrent())
                        .hasNext(postPage.hasNext()).hasPre(postPage.hasPrevious())
                        .records(postVos).build()
        );
    }
    public ResultVO<?> uploadPic(MultipartFile file, String filename) throws Exception {
        MinioUtil.uploadFile(minioProperties.getBucket(), file, filename);
        return ResultVO.ok();
    }
    public ResultVO<?> likePost(LikePostDTO likePostDTO){
        if (likePostDTO.getIsLiked()){
            redisUtils.unlikePost(likePostDTO.getPostId(), likePostDTO.getUserId());
        }else {
            redisUtils.likePost(likePostDTO.getPostId(), likePostDTO.getUserId());
        }
        return ResultVO.ok();
    }
}
