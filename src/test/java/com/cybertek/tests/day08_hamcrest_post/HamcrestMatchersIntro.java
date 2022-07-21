package com.cybertek.tests.day08_hamcrest_post;

import com.cybertek.tests.pojo.countries_us.Links;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchersIntro {

    @Test
    public void hamcrestTest(){
        //numbers
        assertThat(4+3, equalTo(7));
        assertThat(11+23, is(34));
        assertThat(5+2,is(equalTo(7)));
        assertThat(5+5,not(8));
        assertThat(10+5, is(greaterThan(11)));
        //they all do the same thing. its just for readability

        //string assertions
        String str = "B24 - wooden spoon";
        assertThat(str,equalTo("B24 - wooden spoon"));
        assertThat(str,startsWith("B24"));
        assertThat(str,endsWith("spoon"));
        assertThat(str,containsStringIgnoringCase("WOODEN"));
        str="";
        assertThat(str,emptyString());

        //list
        List<Integer> nums = Arrays.asList(3,1,7,9,10,55);
        assertThat(nums, hasSize(6));
        assertThat(nums,hasItem(10));
        assertThat(nums,hasItems(1,3,55));
        assertThat(nums,everyItem(lessThan(100)));


    }
}
