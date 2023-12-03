package com.madoor.madhub.controller;

import com.madoor.madhub.model.dto.PostSaveDTO;
import com.madoor.madhub.model.dto.SayPageQueryDTO;
import com.madoor.madhub.model.dto.SaySaveDTO;
import com.madoor.madhub.model.vo.ResultVO;
import com.madoor.madhub.service.SayService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/blog/say")
@RequiredArgsConstructor
public class SayController {
    private final SayService sayService;
    @GetMapping
    public ResultVO<?> getSays(@RequestParam(value="current", required=true) Integer current,
                               @RequestParam(value="pageSize", required=true) Integer pageSize){
        return sayService.getSayList(current, pageSize);
    }
    @PostMapping
    public ResultVO<?> addSay(@RequestBody SaySaveDTO saySaveDTO, HttpServletRequest request){
        return sayService.addSay(saySaveDTO, request);
    }
}
