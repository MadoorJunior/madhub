package com.madoor.madhub;

import cn.hutool.core.util.StrUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MadhubApplicationTests {

    @Test
    void contextLoads() {
        String s="madoor.junior.test";
        System.out.println(StrUtil.subBefore(s,".",true));
    }

}
