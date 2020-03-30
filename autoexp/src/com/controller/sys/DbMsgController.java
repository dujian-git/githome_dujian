package com.controller.sys;

import com.dao.AllSourceDao;
import com.dao.TableInfoDao;
import com.model.AllSource;
import com.model.DbMsg;
import com.model.TableInfo;
import com.model.base.Json;
import com.service.IDbMsgService;
import com.utils.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据库配置页面跳转控制层
 *
 * @author dujian
 * @date 20200324
 */
@Controller
@RequestMapping("/dbcl")
public class DbMsgController {

    @Autowired
    private IDbMsgService dbMsgService;

    /**
     * 页面加载就执行的方法
     **/
    @RequestMapping("/list")
    public String list(Model model) {
        Map root = new HashMap();
        try {
            List<DbMsg> dbList = dbMsgService.getDbMsgList(root);
            if (dbList.size() == 0) {
                root.put("success", false);
                model.addAttribute("root", root);
                return "db/list";
            }
            root.put("dbList", dbList);
            root.put("success", true);
        } catch (Exception e) {
            model.addAttribute("success", false);
            model.addAttribute("msg", e.getMessage());
            e.printStackTrace();
        }
        model.addAttribute("root", root);

        return "db/list";
    }


    /**
     * 查询按钮
     **/
    @RequestMapping("/qryDbList")
    public String qryDbList(Model model, HttpServletRequest request) {
        String sSys = request.getParameter("sSys");
        String sIp = request.getParameter("sIp");
        Map root = new HashMap();
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("sys", sSys);
            map.put("ip", sIp);
            List<DbMsg> dbMsgList = dbMsgService.getDbMsgList(map);
            if (dbMsgList.size() == 0) {
                root.put("success", false);
                model.addAttribute("root", root);
                return "db/list";
            }
            root.put("dbList", dbMsgList);
            root.put("success", true);
        } catch (Exception e) {
            root.put("success", false);
            model.addAttribute("msg", e.getMessage());
            e.printStackTrace();
        }
        model.addAttribute("root", root);
        return "db/list";
    }


    /**
     * 新增按钮
     **/
    @RequestMapping("/preSave")
    public String preSave(Model model) { //添加或修改
        Map root = new HashMap();
        DbMsg dbMsg = new DbMsg();
        root.put("dbMsg", dbMsg);
        model.addAttribute("root", root);
        return "db/save";
    }

    /**
     * 编辑按钮
     **/
    @RequestMapping("/preEdit")
    public String preEdit(HttpServletRequest request, Model model) {
        String id=request.getParameter("id");
        String sys=request.getParameter("sys");

        System.out.println(id +"...."+sys);
        Map root = new HashMap();
        DbMsg dbMsg = new DbMsg();
        try {
            if (id != null && !"".equals(id.trim())) {
                dbMsg = dbMsgService.findDbMsgById(id);
                root.put("dbMsg", dbMsg);
            } else {
                root.put("dbMsg", dbMsg);
                model.addAttribute("root", root);
                return "db/edit";
            }
        } catch (Exception e) {
            e.printStackTrace();
            root.put("msg", e.getMessage());
            return "db/edit";
        }
        model.addAttribute("root", root);
        return "db/edit";
    }


    /**
     * 新增保存按钮
     **/
    @ResponseBody
    @RequestMapping("/save")
    public Json save(DbMsg dbMsg) {
        Json j = new Json();
        try {
            Long timestamp = System.currentTimeMillis();
            dbMsg.setId(timestamp.toString());
            dbMsgService.addDbMsg(dbMsg);
            j.setSuccess(true);
        } catch (Exception e) {
            j.setSuccess(false);
            j.setMsg("保存失败！");
            e.printStackTrace();
        }
        return j;
    }

    /**
     * 编辑保存按钮
     **/
    @ResponseBody
    @RequestMapping("/edit")
    public Json edit(DbMsg dbMsg) {
        Json j = new Json();
        try {
            dbMsgService.updateDbMsg(dbMsg);
            j.setSuccess(true);
        } catch (Exception e) {
            j.setSuccess(false);
            j.setMsg("保存失败！");
            e.printStackTrace();
        }
        return j;
    }

    /**
     * 删除按钮
     **/
    @RequestMapping("/deleteDbMsg")
    @ResponseBody
    public Json deleteDbMsg(@RequestParam(value = "ids", required = true) String ids) {
        Json j = new Json();
        try {
            if (ids != null && !"".equals(ids.trim())) {
                String[] idsArray = ids.split(",");
                for (String id : idsArray) {
                    dbMsgService.deleteDbMsg(id);
                }
            }
            j.setSuccess(true);
        } catch (Exception e) {
            j.setSuccess(false);
            j.setMsg("删除失败！");
            e.printStackTrace();
        }
        return j;
    }

    /**
     * 测试按钮
     **/
    @RequestMapping("/test")
    public String test(Model model) {
        System.out.println("test");
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("username", "11");
        model.addAllAttributes(root);

        return "template/list";
    }

    /**
     * 导出按钮
     **/
    @RequestMapping("/exp")
    @ResponseBody
    public Json exp(String ids) {
        Json j = new Json();
        if (ids.equals("")) {
            j.setSuccess(false);
            return j;
        }
        DbMsg dbMsg = dbMsgService.findDbMsgById(ids);
        //获得配置文件中要保存的文件的根目录
        String dir = PropertiesUtil.getValue("dir");
        String owner = dbMsg.getUser();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("hostName", dbMsg.getIp());
        map.put("dbName", dbMsg.getSernm());
        map.put("userName", dbMsg.getUser());
        map.put("password", dbMsg.getPwd());
        map.put("projectName", dbMsg.getSys());
        map.put("projectEnv", dbMsg.getEnv());
        if (!new File(dir).exists()) {// 指定的根目录文件夹必须存在
            j.setSuccess(false);
        } else {
            try {
                writeAllSource(dir, owner, map);
                writeTableInfo(dir, owner, map);
                j.setSuccess(true);
            } catch (FileNotFoundException e) {
                j.setSuccess(false);
                e.printStackTrace();
            } catch (SQLException e) {
                j.setSuccess(false);
                e.printStackTrace();
            }
        }
        return j;
    }


    /**
     * 将All_source表以分类路径导出为sql文件
     *
     * @param dir   保存位置的根目录文件夹
     * @param owner 数据库用户
     * @param map   数据库访问信息
     * @throws FileNotFoundException,SQLException
     */
    private void writeAllSource(String dir, String owner, Map map) throws FileNotFoundException, SQLException { // 将All_source表分类导出sql
        map.put("owner", owner.toUpperCase());
//        all_source表记录了该用户可访问的所有数据库对象的脚本信息(DDL)
        List<AllSource> all_sourceGroupList = AllSourceDao.getGroupList(map);
        for (AllSource asGroup : all_sourceGroupList) {
            File folder = null;
            String path1 = dir + map.get("projectName") + "/";
            folder = new File(path1);
            if (!folder.exists()) {
                folder.mkdir();
            }
            String path2 = dir + map.get("projectName") + "/" + map.get("projectEnv") + "/";
            folder = new File(path2);
            if (!folder.exists()) {
                folder.mkdir();
            }
            String path3 = dir + map.get("projectName") + "/" + map.get("projectEnv") + "/" + owner.toUpperCase() + "/";
            folder = new File(path3);
            if (!folder.exists()) {
                folder.mkdir();
            }
            String path4 = dir + map.get("projectName") + "/" + map.get("projectEnv") + "/" + owner.toUpperCase() + "/" + asGroup.getType() + "/";
            folder = new File(path4);
            if (!folder.exists()) {
                folder.mkdir();
            }
            //创建文件夹 能访问到表的文件夹Table
            File file = new File(path4 + asGroup.getName() + ".sql");
            PrintWriter pw = new PrintWriter(file);
            pw.print("create or replace ");
            map.put("type", asGroup.getType());
            map.put("name", asGroup.getName());
            List<AllSource> all_sourceList = AllSourceDao.getAllSourceList(map);
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
     * @param dir   保存位置的根目录文件夹
     * @param owner 数据库用户
     * @param map   数据库访问信息
     * @throws FileNotFoundException,SQLException
     */
    private void writeTableInfo(String dir, String owner, Map map) throws FileNotFoundException, SQLException {
        map.put("table_type", "TABLE"); // 不包含视图VIEW
        List<TableInfo> tableInfoWithDdlAndComList = TableInfoDao.getTableInfoWithDdlAndComList(map);
        for (TableInfo td : tableInfoWithDdlAndComList) {
            File folder = null;
            String path1 = dir + map.get("projectName") + "/";
            folder = new File(path1);
            if (!folder.exists()) {
                folder.mkdir();
            }
            String path2 = dir + map.get("projectName") + "/" + map.get("projectEnv") + "/";
            folder = new File(path2);
            if (!folder.exists()) {
                folder.mkdir();
            }


            String path3 = dir + map.get("projectName") + "/" + map.get("projectEnv") + "/" + owner.toUpperCase() + "/";
            folder = new File(path3);
            if (!folder.exists()) {
                folder.mkdir();
            }
            String path4 = dir + map.get("projectName") + "/" + map.get("projectEnv") + "/" + owner.toUpperCase() + "/" + td.getTable_type() + "/";
            folder = new File(path4);
            if (!folder.exists()) {
                folder.mkdir();
            }
            File file = new File(path4 + td.getTable_name() + ".sql");
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
            List<TableInfo> tableInfoWithIndexList = TableInfoDao.getTableInfoWithIndexList(map);
            if (tableInfoWithIndexList.size() == 0) {
                pw.println("\t\t无索引");
            } else {
                pw.printf("%-25s%-25s%-25s%-30s", "INDEX_NAME(索引名称)", "INDEX_TYPE(索引类型)", "UNIQUENESS(唯一性约束)", "COLUMNS(索引包含的字段)");
                pw.println();
                for (TableInfo ti : tableInfoWithIndexList) {
                    pw.printf("%-29s%-29s%-30s%-30s", ti.getIndex_name(), ti.getIndex_type(), ti.getUniqueness(), ti.getColumns());
                    pw.println();
                }
            }
            pw.flush();
            pw.close();
        }
    }

}
