package pl.cba.lalewicz.cmsfirst.utils;

import org.jsoup.Jsoup;

public class MyTools {

    public static String clearHtmlTags(String textWithHtml){
        return Jsoup.parse(textWithHtml).text();
    }
}
