package com.atobs.qa;



import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Node;

import java.io.IOException;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Example program to list links from a URL.
 * 
 * 1. 	Identify the element
 * 2.	identify the node
 * 3.	extract the value for the attribute
 * 4. 	extract the number
 */
public class ListLinks {
    public static void main(String[] args) throws IOException {
        
        String url = null;
        url = "https://gm.taleo.net/careersection/10001/joblist.ftl";
        print("Fetching %s...", url);

        Document doc = Jsoup.connect(url).get();
       Connection con = Jsoup.connect(url);
        Response res = con.execute();
        System.out.println(res.statusCode());
        
        //org.jsoup.helper.HttpConnection.Response response = null ;
       
       // System.out.println(response.statusCode());
      //  Elements links = doc.select("a[href]");
       // Elements media = doc.select("[src]");
      //  Elements imports = doc.select("link[href]");	
       // Elements allElems = doc.getAllElements();
      //  Attributes joblist = doc.getElementById("initialHistory").attributes();
        String value = doc.getElementById("initialHistory").attr("value");
        
        Pattern p = Pattern.compile("Job\\sOpenings\\s\\([0-9]*\\sjobs\\sfound\\)");
        Matcher m = null;
        String[] splited = value.split("!");
        for (int i = 0; i < splited.length; i++) {
        	//System.out.println(splited[i]);
        	 m = p.matcher(splited[i]);
        	if (m.matches()) {
        		String str = splited[i];
				String result = str.substring(str.indexOf("(") + 1, str.indexOf("j"));
				Integer jobNum = Integer.parseInt(result.trim());	
				System.out.println("Number of jobs is : " + jobNum);
			}
		}
       
    }

    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

   
}