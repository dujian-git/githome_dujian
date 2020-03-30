package com.framework.constant;

import java.util.HashMap;
import java.util.Map;

public class GlobalConstant {

	public static final String SESSION_INFO = "sessionInfo";

	public static final Integer ENABLE = 0; // 启用
	public static final Integer DISABLE = 1; // 禁用
	
	public static final Integer LOG_ID_0 = 0; // 待审批
	public static final Integer LOG_ID_1 = 1; // 通过
	
	public static final Integer DEFAULT = 0; // 默认
	public static final Integer NOT_DEFAULT = 1; // 非默认
	
	public static final Map sexlist = new HashMap(){{ put("0", "男");  put("1", "女");} };
	public static final Map statelist = new HashMap(){{ put("0", "启用");  put("1", "停用");} };
	
	//普通会员
	public static final Integer ROLE_ADMIN = new Integer(1);
	public static final Integer ROLE_NORMOAL = new Integer(2);
	
	public static final Integer UN_REAL_NAME = 0; //非实名
	public static final Integer REAL_NAME = 1;//已实名
	public static final Integer WAIT_REAL_NAME = 2;//待审核实名
	
	public static final String CHUSHIHUA_PWD = "111111";

	public static final Long DEFAULT_ORG = 1L;
	
	public static final Long ROLE_HY_ID = new Long(3);
	
	//实验室状态：0-关闭 1-开放
	public static final String LAB_STS_0 = "0";
	public static final String LAB_STS_1 = "1";
	  
	//课程状态：0-初始化,1-待审核,2-审批通过,11拒绝 3-完结
	public static final String EXP_CLASS_STS_0 = "0";//退单
	public static final String EXP_CLASS_STS_1 = "1";
	public static final String EXP_CLASS_STS_2 = "2";
	public static final String EXP_CLASS_STS_3 = "3";//完结
	public static final String EXP_CLASS_STS_11 = "11";//拒绝 
	public static final String EXP_CLASS_STS_12 = "12";//作废
	
	
	public static final String TRADE_LOG_STATE_0 = "0"; //负值
	public static final String TRADE_LOG_STATE_1 = "1"; //正值
	
	public static final String PT_ACCOUNT_NAME = "admin";
	public static final Integer PT_ACCOUNT_ID = 0;
	
	public static final Double KJ_BL_1 = new Double(0.06);
	public static final Double KJ_BL_2 = new Double(0.1);
	
	//竞拍日期范围
	public static final Integer BETWEEN_MIN = 1; //  
	public static final Integer BETWEEN_MAX = 3; // 
	
	public static final String TB_TYPE_CODE = "tabletype";
	
	public static final Integer ROWS = 20; // 默认每页页码
	
	
	public static final String LOGIN_TYPE_1 = new String("1"); //系统管理员
	public static final String LOGIN_TYPE_2 = new String("2"); //教务审核教师
	public static final String LOGIN_TYPE_3 = new String("3"); //教师
	public static final String LOGIN_TYPE_4 = new String("4"); //学生
	
	public static final String CLASS_TIME_1 = "1";//上午第一节(8:00-10:00)
	public static final String CLASS_TIME_2 = "2";//上午第二节(10:00-12:00)
	public static final String CLASS_TIME_3 = "3";//下午第一节(13:00-15:00)
	public static final String CLASS_TIME_4 = "4";//下午第二节(15:00-17:00)
	public static final String CLASS_TIME_5 = "5";//晚上第一节(19:00-21:00)
	
	public static final String PAGECNT = "20";//后台每页默认展示5条数据
	
	 
}
