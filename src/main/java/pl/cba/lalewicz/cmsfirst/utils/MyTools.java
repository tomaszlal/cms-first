package pl.cba.lalewicz.cmsfirst.utils;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;

public class MyTools {

    public static String clearHtmlTags(String textWithHtml) {


        CleanerProperties props = new CleanerProperties();
        props.setPruneTags("script");
        String result = null;
        try {
            result = new HtmlCleaner(props).clean(textWithHtml).getText().toString();
        }catch (Exception e){}

        return result;


    }
}
