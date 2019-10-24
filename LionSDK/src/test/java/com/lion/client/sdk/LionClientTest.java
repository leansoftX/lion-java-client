package com.lion.client.sdk;

import org.apache.hc.core5.http.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import  com.lion.client.sdk.common.*;
import  com.lion.client.sdk.models.*;

import java.io.IOException;
import java.util.HashMap;

public class LionClientTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    //Default Project
    //Production Env sdk key: sdk-1a18e3cb-0c43-4120-af29-a7257d6fb624
    //Test Env sdk key: sdk-fd36d100-a98c-42b8-9e2d-af535e06acb2

    // Features key: test-fuction1, all user enable
    // test-fuction2,
    @Test
    public void boolVariation() throws IOException, ParseException {
        LionClient client = new LionClient("sdk-fd36d100-a98c-42b8-9e2d-af535e06acb2");
        final boolean result = client.BoolVariation("test-fuction1");
        assertEquals(result, true);
    }

    @Test
    public void boolVariation1() throws IOException, ParseException {
        String testUser1 = "lixiaoming@leansoft.com";
        LionUser lionUser = new LionUser(testUser1);
        lionUser.setName(testUser1);
        lionUser.setEmail(testUser1);
        HashMap<String, String> map = lionUser.getCustom();
        map.put("Gender", "male");
        //map.put("Age", "31");
        //map.put("Phone", "123456789");
        //map.put("City", "GuangZhou");
        LionClient client = new LionClient("sdk-fd36d100-a98c-42b8-9e2d-af535e06acb2");//"http://localhost/api"
        final boolean result = client.BoolVariation("test-fuction2",lionUser);
        assertEquals(result, true);

        /*
        String testUser2 = "lixiaoming-disable@leansoft.com";
        LionUser lionUser2 = new LionUser(testUser2);
        lionUser2.setName(testUser2);
        lionUser2.setEmail(testUser2);
        HashMap<String, String> map2 = lionUser2.getCustom();
        map2.put("Gender", "male");
        map2.put("Age", "32");
        map2.put("Phone", "1234567890");
        map2.put("City", "ShenZheng");
        */

}

    @Test
    public void boolVariation2() {
    }
}