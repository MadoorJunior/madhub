package com.madoor.madhub.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.madoor.madhub.entity.Post;
import com.madoor.madhub.entity.Say;
import com.madoor.madhub.mapper.SayMapper;
import com.madoor.madhub.model.dto.SayPageQueryDTO;
import com.madoor.madhub.model.dto.SaySaveDTO;
import com.madoor.madhub.model.vo.QueryPageVO;
import com.madoor.madhub.model.vo.ResultVO;
import com.madoor.madhub.util.IpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SayService {
    private final SayMapper sayMapper;
    public ResultVO<?> getSayList(Integer current, Integer pageSize){
        Page<Say> page = new Page<>(current, pageSize);
        sayMapper.selectPage(page, new LambdaQueryWrapper<Say>().orderByDesc(Say::getCreateTime));
        List<Say> says = sayMapper.selectList(new LambdaQueryWrapper<>());
        return ResultVO.ok(
                QueryPageVO.<Say>builder()
                        .count(page.getTotal())
                        .pageSize(page.getSize())
                        .curPage(page.getCurrent())
                        .hasNext(page.hasNext()).hasPre(page.hasPrevious())
                        .records(page.getRecords()).build()
        );
    }
    public ResultVO<?> addSay(SaySaveDTO saySaveDTO, HttpServletRequest request){
        String ipAddress = IpUtil.getIpAddress(request);
        String ipSource = IpUtil.getIpSource(ipAddress);
        Say say = Say.builder().content(saySaveDTO.getContent()).ipSource(ipSource).build();
        int effectedRows = sayMapper.insert(say);
        return effectedRows!=0?ResultVO.ok():ResultVO.fail();
    }
}
