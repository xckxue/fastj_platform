package com.wow.test.redis;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.statement.delete.Delete;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import java.io.StringReader;
import java.util.Objects;

/**
 * Created by wow on 2018/1/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration()
public class TestRedis {
    public static void main(String[] args) {
        Integer a = 127;
        Integer b = 127;
        System.out.println(Objects.equals(a, b));
    }
}
