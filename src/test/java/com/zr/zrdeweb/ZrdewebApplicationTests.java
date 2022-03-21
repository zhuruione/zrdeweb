package com.zr.zrdeweb;

import com.zr.zrdeweb.system.dao.UserDataMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ZrdewebApplicationTests {

    @Autowired
    UserDataMapper dataMapper;
    @Test
    void contextLoads() {
    }

}
