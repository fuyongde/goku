package com.goku.dubbo.commons.utils;

import com.goku.dubbo.commons.consts.DatePattern;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author fuyongde
 * @desc DateUtils的用法
 * @date 2017/11/7 11:40
 */
public class DateUtilsTest {


    private Date date;

    @Before
    public void before() throws Exception {
        date = new Date();
    }

    @After
    public void after() throws Exception {

    }

    @Test
    public void testIsSameDay() throws Exception {
        Date today = new Date();
        boolean isSameDay = DateUtils.isSameDay(date, today);
        assertTrue(isSameDay);

        Date yestoday = DateUtils.addDays(today, -1);
        isSameDay = DateUtils.isSameDay(date, yestoday);
        assertFalse(isSameDay);
    }

    @Test
    public void testParse() throws Exception {
        Date date = DateUtils.parseDate("2017-11-07 11:47:59", DatePattern.PATTERN_1);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        assertEquals(2017, calendar.get(Calendar.YEAR));
    }

}
