package com.example.kotlindemo.dialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author:zhangshijie
 * @Date:2021/12/2
 * @email:zhangshijie@soyoung.com
 * @Description:
 */
public class AAZSJ {

    public static void a(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("female", true);
        map.put("hobbies", Arrays.asList(new String[] { "yoga", "swimming" }));
        map.put("discount", 9.5);
        map.put("age", "26");
        map.put("features", new HashMap<String, Integer>() {
            private static final long serialVersionUID = 1L;
            {
                put("height", 175);
                put("weight", 70);
            }
        });

        JSONObject jsonObject = new JSONObject(map);
        try {
            JSONArray jsonArray = jsonObject.getJSONArray("hobbies");


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
