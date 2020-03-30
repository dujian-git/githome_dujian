package com.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {//properties工具类

	public static String getValue(String key){
			//.properties键值对获取
		Properties prop=new Properties();
		InputStream input=
				new PropertiesUtil().getClass().getResourceAsStream("/jdbc.properties");
		try {
			prop.load(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (String)prop.get(key);
	}
}
