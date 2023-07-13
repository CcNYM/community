package com.nowcoder.community;


import org.junit.Test;

import java.util.Date;

public class Test1 {

    @Test
    public void testTime(){
        System.out.println(new Date());
        System.out.println(new Date((long) 10.123));
    }
}
