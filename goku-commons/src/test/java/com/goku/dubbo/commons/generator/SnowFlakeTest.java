package com.goku.dubbo.commons.generator;

import com.goku.dubbo.commons.consts.DatePattern;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * SnowFlake Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>���� 2, 2018</pre>
 */
public class SnowFlakeTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: next()
     */
    @Test
    public void testNext() throws Exception {
        SnowFlake snowFlake = new SnowFlake(1);
        List<Map<String, Object>> list = Lists.newArrayList();

        for (int i = 0; i < 500000; i++) {
            Map<String, Object> result = Maps.newConcurrentMap();
            Long timeStamp = System.currentTimeMillis();
            long id = snowFlake.next();
            result.put("timeStamp", timeStamp);
            result.put("id", id);
            result.put("time", DateFormatUtils.format(timeStamp, DatePattern.PATTERN_1));

            list.add(result);
        }

        Set<String> dateSet = Sets.newHashSet();
        for (Map<String, Object> objectMap : list) {
            dateSet.add((String) objectMap.get("time"));
        }

        for (String s : dateSet) {
            int count = 0;
            for (Map<String, Object> objectMap : list) {
                if (StringUtils.equals(s, (CharSequence) objectMap.get("time"))) {
                    count++;
                }
            }
            System.out.println(s + " : " + count);
        }

        Set<String> set = Sets.newHashSet();
        for (Map<String, Object> objectMap : list) {
            long timeStamp = (long) objectMap.get("timeStamp");
            long id = (long) objectMap.get("id");
            set.add(timeStamp + "-" + String.valueOf(id));
        }
        System.out.println("长度：" + set.size());

        Set<Long> timeStampSet = Sets.newHashSet();
        for (Map<String, Object> objectMap : list) {
            timeStampSet.add((Long) objectMap.get("timeStamp"));
        }

        for (Long l : timeStampSet) {
            int count = 0;
            for (Map<String, Object> objectMap : list) {
                if (l == (long) objectMap.get("timeStamp")) {
                    count++;
                }
            }
            System.out.println(l + " : " + count);
        }
    }

} 
