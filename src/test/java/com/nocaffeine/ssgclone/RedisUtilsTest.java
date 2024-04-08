package com.nocaffeine.ssgclone;

import com.nocaffeine.ssgclone.common.redis.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class RedisUtilsTest {

    @Autowired
    private RedisUtils redisUtils;

    @Test
    public void testRedisOperations() {
        // 데이터 저장
        redisUtils.setData("testKey", "testValue", 1000);

        // 데이터 검색
        String value = redisUtils.getData("testKey");
        assertEquals("testValue", value);

        // 데이터 삭제
        redisUtils.deleteData("testKey");
        value = redisUtils.getData("testKey");
        assertNull(value);
    }
}
