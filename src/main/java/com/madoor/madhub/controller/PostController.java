package com.madoor.madhub.controller;

import cn.hutool.http.server.HttpServerRequest;
import com.madoor.madhub.model.dto.LikePostDTO;
import com.madoor.madhub.model.dto.PostPageQueryDTO;
import com.madoor.madhub.model.dto.PostSaveDTO;
import com.madoor.madhub.model.vo.ResultVO;
import com.madoor.madhub.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    @PostMapping
    public ResultVO<?> savePost(@RequestBody PostSaveDTO postSaveDTO, HttpServletRequest request){
        System.out.println(postSaveDTO);
        return postService.savePost(postSaveDTO,request);
    }
    @PostMapping("/list")
    public ResultVO<?> getPosts(@RequestBody PostPageQueryDTO postPageQueryDTO){
        return postService.getPostList(postPageQueryDTO);
    }
    @PostMapping("/pic")
    public ResultVO<?> uploadPic(@RequestParam(name = "file") MultipartFile file,@RequestParam(name = "filename") String filename) throws Exception {
        return postService.uploadPic(file, filename);
    }
    @PostMapping("/like")
    public ResultVO<?> likePost(@RequestBody LikePostDTO likePostDTO){
        return postService.likePost(likePostDTO);
    }
}
