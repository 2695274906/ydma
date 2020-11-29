package cn.itsqh.ydma.util;

import java.io.IOException;
 
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class GetIp {
	public static String getIpAndAdress() throws IOException {
		Document document = Jsoup.connect("http://2018.ip138.com/ic.asp").get();
		Elements links = document.getElementsByTag("center");
		String info = links.text();
		String ip = info.substring(info.indexOf("[")+1, info.indexOf("]"));
		String adress = info.substring(info.lastIndexOf("：")+1);
		return ip+"==="+adress;
	}
}
