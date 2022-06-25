package com.example.kotlindemo.zhengze;

import android.util.Log;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author:zhangshijie
 * @Date:2021/9/27
 * @email:zhangshijie@soyoung.com
 * @Description:
 */
public class AZSJ {

    public static int firstIndexOf(String str, String pattern) {
        for (int i = 0; i < (str.length() - pattern.length()); i++) {
            int j = 0;
            while (j < pattern.length()) {
                if (str.charAt(i + j) != pattern.charAt(j))
                    break;
                j++;
            }
            if (j == pattern.length())
                return i;
        }
        return -1;
    }

    public void matchHtmlToMap(String htmlSource) {
        htmlSource = htmlSource.replaceAll("'", "\"");
        String reg = "<\\s*img[^>]*?src=\"([^>\"]+)\"[^>]*?style=\"width:([^;]+); height:([^;]+);\"[^>]*>";
        try {
            Matcher htmlMatcher = Pattern.compile(reg).matcher(htmlSource);
            while (htmlMatcher.find()) {

                String url = htmlMatcher.group(1);
                String widStr = htmlMatcher.group(2);
                String heiStr = htmlMatcher.group(3);

                Log.e("zsj", "matchHtmlToMap: " +url+"--"+widStr+"--"+heiStr);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("zsj", "matchHtmlToMap: "+e.getLocalizedMessage() );
        }
    }
}
