package com.madoor.madhub.controller;

import com.madoor.madhub.model.dto.CommentDTO;
import com.madoor.madhub.model.dto.LikePostDTO;
import com.madoor.madhub.model.dto.PostSaveDTO;
import com.madoor.madhub.model.vo.ResultVO;
import com.madoor.madhub.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    @PostMapping
    public ResultVO<?> saveComment(@RequestBody CommentDTO commentDTO, HttpServletRequest request){
        return commentService.comment(commentDTO, request);
    }
    @GetMapping("/{postId}")
    public ResultVO<?> getComment(@PathVariable(name = "postId") Integer postId){
        return commentService.getComments(postId);
    }
    @PostMapping("/like")
    public ResultVO<?> likeComment(@RequestBody LikePostDTO likePostDTO){
        return commentService.likeComment(likePostDTO);
    }
}
