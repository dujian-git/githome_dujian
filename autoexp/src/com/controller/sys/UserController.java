package com.controller.sys;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.model.AllSource;
import com.model.TableInfo;
import com.model.base.Json;
import com.service.IAllSourceService;
import com.service.ITableInfoService;
import com.utils.PropertiesUtil;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IAllSourceService allSourceService;

	@Autowired
	private ITableInfoService tableInfoService;
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
//	    System.out.println("user/index");
		return "db/list";
	}

	//导出按钮
	@ResponseBody
	@RequestMapping("/exp")
	public Json exp(HttpServletRequest request) {
		Json j = new Json();
		//获得配置文件中要保存的文件的根目录
		String dir = PropertiesUtil.getValue("dir");
		//获取本数据库连接的账号 这边是写死的  想要写活  在外面传入
		String owner = PropertiesUtil.getValue("username");
		System.out.println(dir+"...."+owner);
		if (!new File(dir).exists()) {// 指定的根目录文件夹必须存在
			j.setSuccess(false);
		} else {
			try {
				writeAll_source(dir, owner);
				writeTableInfo(dir, owner);
				j.setSuccess(true);
			} catch (FileNotFoundException e) {
				j.setSuccess(false);
				e.printStackTrace();
			}
		}
		return j;
	}

	/**
	 * 将All_source表以分类路径导出为sql文件
	 * 
	 * @param dir
	 *            保存位置的根目录文件夹
	 * @param owner
	 *            数据库用户
	 * @throws FileNotFoundException
	 */
	private void writeAll_source(String dir, String owner)
			throws FileNotFoundException { // 将All_source表分类导出sql
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("owner", owner.toUpperCase());
//        all_source表记录了该用户可访问的所有数据库对象的脚本信息(DDL)
		List<AllSource> all_sourceGroupList = allSourceService
				.getGroupList(map);
		for (AllSource asGroup : all_sourceGroupList) {
			File folder = null;
			String path1 = dir + owner.toUpperCase() + "/";
			folder = new File(path1);
			if (!folder.exists()) {
				folder.mkdir();
			}
			String path2 = dir + owner.toUpperCase() + "/" + asGroup.getType()
					+ "/";
			folder = new File(path2);
			if (!folder.exists()) {
				folder.mkdir();
			}
			//创建文件夹 能访问到表的文件夹Table
			File file = new File(path2 + asGroup.getName() + ".sql");
			PrintWriter pw = new PrintWriter(file);
			pw.print("create or replace ");
			map.put("type", asGroup.getType());
			map.put("name", asGroup.getName());
			List<AllSource> all_sourceList = allSourceService.getAllSourceList(map);
			String asTem = null;
			for (AllSource as : all_sourceList) {
				asTem = as.getText();
				if (asTem != null && !"".equals(asTem.trim())) {
					pw.println(asTem.substring(0, asTem.length() - 1)); // 去掉"\n"符，window和linux换行不同
				}
			}
			pw.flush();
			pw.close();
		}
	}

	/**
	 * 将用户下的表的结构、索引、注释等导出为sql文件
	 * 
	 * @param dir
	 *            保存位置的根目录文件夹
	 * @param owner
	 *            数据库用户
	 * @throws FileNotFoundException
	 */
	private void writeTableInfo(String dir, String owner)
			throws FileNotFoundException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("table_type", "TABLE"); // 不包含视图VIEW
		List<TableInfo> tableInfoWithDdlAndComList = tableInfoService
				.getTableInfoWithDdlAndComList(map);
		for (TableInfo td : tableInfoWithDdlAndComList) {
			File folder = null;
			String path1 = dir + owner.toUpperCase() + "/";
			folder = new File(path1);
			if (!folder.exists()) {
				folder.mkdir();
			}
			String path2 = dir + owner.toUpperCase() + "/" + td.getTable_type()
					+ "/";
			folder = new File(path2);
			if (!folder.exists()) {
				folder.mkdir();
			}
			File file = new File(path2 + td.getTable_name() + ".sql");
			PrintWriter pw = new PrintWriter(file);
			pw.println();
			pw.println("/****************表结构****************/");
			pw.println();
			String[] strArr = td.getDdl().split("\n");
			for (String str : strArr) {
				if (!"".equals(str.trim())) {
					pw.println(str);
				}
			}
			pw.println();
			pw.println();
			pw.println("/**************本表注释**************/");
			pw.println();
			if (td.getComments() == null || "".equals(td.getComments().trim())) {
				pw.println("\t\t无注释");
			} else {
				pw.println(td.getComments());
			}
			pw.println();
			pw.println();
			pw.println("/**************本表索引**************/");
			pw.println();
			map.put("table_name", td.getTable_name());
			List<TableInfo> tableInfoWithIndexList = tableInfoService
					.getTableInfoWithIndexList(map);
			if (tableInfoWithIndexList.size() == 0) {
				pw.println("\t\t无索引");
			} else {
				pw.printf("%-25s%-25s%-25s%-30s", "INDEX_NAME(索引名称)",
						"INDEX_TYPE(索引类型)", "UNIQUENESS(唯一性约束)",
						"COLUMNS(索引包含的字段)");
				pw.println();
				for (TableInfo ti : tableInfoWithIndexList) {
					pw.printf("%-29s%-29s%-30s%-30s", ti.getIndex_name(), ti
							.getIndex_type(), ti.getUniqueness(), ti
							.getColumns());
					pw.println();
				}
			}
			pw.flush();
			pw.close();
		}
	}
}
