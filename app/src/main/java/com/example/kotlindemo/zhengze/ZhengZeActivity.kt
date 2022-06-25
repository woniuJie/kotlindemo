package com.example.kotlindemo.zhengze

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlindemo.R
import com.example.kotlindemo.dp2px
import com.example.kotlindemo.loge
import kotlinx.android.synthetic.main.activity_zheng_ze.*
import java.lang.Exception
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.HashMap
import java.util.regex.Matcher
import java.util.regex.Pattern

class ZhengZeActivity : AppCompatActivity() {

    private val XHS_RANGE =
        Pattern.compile("\\[[^\\]]+\\]", Pattern.CASE_INSENSITIVE)
    private val XHS_RANGE1 =
        Pattern.compile("张士杰.", Pattern.CASE_INSENSITIVE)

    private fun getMatcher(matchStr: CharSequence): Matcher {
        return XHS_RANGE.matcher(matchStr)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zheng_ze)

        var str = "第二次皮秒[:163]阿斯顿发生张士杰是.xxx我的名字觉张士杰啊.xxx张士杰我.xxx"
//        var regex = "\b[Cc][Aa][Rr]\b"
//        loge("正则表达式--" + regexChange(str, regex))

        var str1 =
            "sales1.xlsorders3.xlssales2.xlsapac1.xlseurope2.xlsna1.xlsna2.xlssa1.xlssam.xlsusa1.xls"
        var str2 = "myArray[0]==0adfljmyArray0adsfasdmyArray[1]hoem\\ddddaf\\adsf 打发点啥"
        var str3 = "hello .baaf.adsf@asdflj.cadof adfasd"
        var str4 = "http://www.safk.asco/alsdfjla aldsfjaldsjf https://sadf.sdlfa.adskflj/"
        var str5 = "adflkjkl #adf123 #adfjk1212 #111222 #oooooo #ffffff"
        var str6 = "2021/1/3 af/s/s 1/2/2 22222/2/222 2021/111/22"
        var str7 = "1001:100.1 1002:21.10 1003:300.901"
        var str8 = "<B>Ak</B>adsfa<b>asdf</b>"
        var str9 = "cat ataadfadscatt"
        var str10 = "adff my name is zhang&nbsp;&nbsp;shi&nbsp;jie adsfkjahdf dakjfh"
        var str11 = "adsflj[12.212.12.121]"
        var str12 = "2021-ddadfkjkj"
        var str13 = "<h1>afajlj</h1><asfadsf><h2>adfadf</h2><h2>asdf</h3>"
        var str14 = "mymy nameisiszhang zhang shi jie"
        var str15 = "hello zhangshijiezhang"
        var str16 = "sales.xml  salex\\.xml  community.xml"
        var str17 = "sales.xml usales.xml nales.xml  nale1.xml bales.xml  malex.xml "
        var str18 = "\\home \\aasd"
        var str19 = "h ame asdflj"
        var str20 = "home\n" +
                "lll\n" +
                "\n" +
                "adsf"

        var str21 = ".zhang@soyoung.com.xxx .liu.zha@soyoung.com  zliu.zha@soyoung.com"
        var str22 = "4/8/02 10-6-2004"
        var str23 = "<title>张士杰</title>"
        var str24 = "¥11.33"
        var str25 = "¥11.33 12.3 100"
        var str26 = "¥1133 123 100"
        var str27 =
            "https://img2.soyoung.com/tieba/ios/post/20200629/2/108d391524db8f42839644be34de95ac_540_718.jpg"
        var str28 =
            "<img src=\"https://img2.soyoung.com/tieba/ios/diary/20191014/4/279923b3bcbaea2c52dd6604632912b3_570.jpg\"style=\"width:570;height:760;\"/>"
        var str29 = "2017-1-1"
        var str30 =
            "<img src=\"https://img2.soyoung.com/tieba/ios/diary/20191014/4/279923b3bcbaea2c52dd6604632912b3_570.jpg\"/>"
        var str31 = "sale usale nale"
        var str32 = "1010"
        var str33 = "zhangshijie"
        var str34 = "abbc"
        var str35 = "10101010110101001100101010101010101010101010101010000"
        var str36 = "abaabaaaaba"
        var str37 = "@color/col_777777"

        val m: Matcher = getMatcher(str)
        while (m.find()) {
            val key = m.group()
            loge("zhengze--key--${key}")
        }

//        regexChange(str3,"\\w+[\\w.]*@[\\w.]+\\.\\w+")
//        regexChange(str4,"https?://[\\w./]+")
//        regexChange(str5,"#[a-fA-F0-9]{6}")
//        regexChange(str6,"\\d{3,4}\\/\\d{1,2}\\/\\d{1,2}")
//        regexChange(str7,"\\d+:\\d{3,}\\.\\d{1,2}")
//        regexChange(str8,"<[Bb]>.*?</[Bb]>")
//        regexChange(str9,"\\bcat\\b")
//        regexChange(str10,"(&nbsp;){2,}")
//        regexChange(str11,"(\\d{1,3}\\.){3}\\d{1,3}")
//        regexChange(str12,"(19|20)\\d{2}")
//        regexChange(str11,"(((\\d{1,2})|(1\\d{2})|(2[0-4]\\d)|(25[0-5]))\\.){3}((\\d{1,2})|(1\\d{2})|(2[0-4]\\d)|(25[0-5]))\\b")
//        regexChange(str13,"<Hh[1-6]>.*?</Hh[1,6]>")
//        regexChange(str13, "<[Hh]([1-6])>.*?</[Hh]\\1>")
//        regexChange(str13, "<[Hh][1-6]>.*?</[Hh][1-6]>")
//        regexChange(str15, "zhang")
//        regexChange(str16, "sale.\\\\.xml")
//        regexChange(str14,"[ ]*(\\w+)[ ]*\\1")
//        regexChange(str17, "[sn]ale[0-9]\\.xml")
//        regexChange(str17, "\\b[sn]ales\\.xml")
//        regexChange(str18, "\\\\home")
//        regexChange(str19, "h\\same")
//        regexChange(str20, "lll\\sadsf")
//        regexChange(str21, "\\w[\\w.]*@[\\w]+\\.[\\w]+")
//        regexChange(str22, "\\d{1,2}[-\\/]\\d{1,2}[-/\\]\\d{2,4}")
//        regexChange(str23, "(?<=<title>).*?(?=</title>)")
//        regexChange(str24, "(?<=¥)[0-9.]+")
//        regexChange(str25, "(?<!¥)[0-9]+[0-9.]*[0-9]\\b")
//        regexChange(str26, "\\b(?<!¥)\\d+\\b")
//        regexChange(str27, "_\\d+_\\d+")
//        regexChange(str28, "<\\s*img[^>]*?src=\"([^>\"]+)\"[^>]*?style=\"width:([^;]+); height:([^;]+);\"[^>]*>")
//        regexChange(str28, "width:([^;]+);")
//        regexChange(str29, "(\\d{4})-(\\d{1,2})-(\\d{1,2})")
//        regexChange(str28, "<img[\\s]*src=\"(\\w+)\"[\\s]*style=\"width:(\\d+);[\\s]*height:(\\d+);\"/>")
//        regexChange(str28, "<img[\\s]*src=\"([^>]+)\"style=\"width:([\\d]+);height:([\\d]+);\"/>")
//        regexChange(str21, "\\b\\w[\\w.]+@[\\w.]+\\w\\b")
//        regexChange(str31, "[ns]ale\$")
//        regexChange(str32, "^(0\\.\\d{0,1}[1-9]|(?!0)\\d{1,6}(\\.\\d{0,1}[1-9])?)\$")
//        regexChange(str33, "^(zhangshi(?!jie|hh))\$")
//        regexChange(str34, "ab{1,3}+c")
//        regexChange(str35, "(([01]+)+b)")
//        regexChange(str36, "a.*?b")
//        regexChange(str37, "(?<=(@color/(color|col)_))([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3}|[A-Fa-f0-9]{8})")
        var str38 = "com.soyoung.x.library:utils:0.0.1"
//        regexChange(str38, "com.soyoung.x.(view|library):(\\w+):([\\d.]+)")
//        regexChange(str38, "com.soyoung[\\w.]*:([\\w-_]+):([\\d.]+(-SNAPSHOT)?)")
//        regexChange(str38, "(com.soyoung[.A-Za-z0-9_\\-]*):([A-Za-z0-9_]+):([.A-Za-z0-9_\\+\\-]+)")

        var str281 =
            "<img src=\"https://img2.soyoung.com/tieba/ios/diary/20191014/4/279923b3bcbaea2c52dd6604632912b3_570.jpg\"style=\"width:570;height:760;\"/>"

        var str40 = "{,}";
//        loge("zsj-"+getMatcherResultUrl(str27))
//        matchHtmlToMap(str28)

        loge(xxx(str40))

        val x = str40.replace("{", "\\{")
            .replace(",", "\\,")
            .replace("}", "\\}")
        loge(x)
    }

    private fun xxx(url: String): String {
        var matchUrl = ""
        try {
            val pattern = Pattern.compile("\\{")
            val matcher = pattern.matcher(url)
            matchUrl = matcher.replaceAll("\\\\{").trim { it <= ' ' }
        } catch (e: Exception) {
        }
        return matchUrl
    }

    private fun getMatcherResultUrl(url: String): String? {
        var matchUrl = ""
        try {
            val pattern = Pattern.compile("_\\d+_\\d+")
            val matcher = pattern.matcher(url)
            matchUrl = matcher.replaceAll("").trim { it <= ' ' }
        } catch (e: Exception) {
        }
        return matchUrl
    }

    fun matchHtmlToMap(htmlSource: String) {
        var htmlSource = htmlSource
        val htmlMap = HashMap<String, String>()
        htmlSource = htmlSource.replace("'".toRegex(), "\"")
        val reg =
            "<\\s*img[^>]*?src=\"([^>\"]+)\"[^>]*?style=\"width:([^;]+); height:([^;]+);\"[^>]*>"
        try {
            val htmlMatcher = Pattern.compile(reg).matcher(htmlSource)
            while (htmlMatcher.find()) {
                val url = htmlMatcher.group(1)
                val widStr = htmlMatcher.group(2)
                val heiStr = htmlMatcher.group(3)
                loge("url-${url} width${widStr} hright${heiStr} ${htmlMatcher.group()}")
                htmlMap["src"] = url
                htmlMap["width"] = widStr
                htmlMap["height"] = heiStr
            }
        } catch (e: Exception) {
            e.printStackTrace()
            loge("e${e.localizedMessage}")
        }
    }


    fun regexChange(str: String?, regex: String) {
        val m: Matcher = Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(str)
        while (m.find()) {
            val key = m.group()
            loge("zhengze--key--${key}")
            loge("zhengze--key--${m.group(1)} -- ${m.group(2)}---${m.group(3)}")
        }
    }
}