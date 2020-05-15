package com.example.demo.controller;


import com.example.demo.entity.Items;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@ComponentScan
@RestController
@RequestMapping("/demo")
//@RequestMapping("/User")
public class ItemsController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * @return
     * 查询全部信息
     */
    @RequestMapping("/list")
    public List<Map<String, Object>> itemsList() {
        String sql = "select * from items";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }
    /**
     * @return
     * 查询全部信息页面
     */
    @RequestMapping("/login")
    public ModelAndView itemsListPage() {
        String sql = "select * from items";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        ModelAndView mav = new ModelAndView("items");
        mav.addObject("list", list);
        return mav;
    }

    /**
     * @return
     * 跳转到新增接界面
     */
    @RequestMapping("/toAdd")
    public ModelAndView  toAdd() {
        ModelAndView mav = new ModelAndView("add");
        return mav;
    }
    /**
     * 新增数据
     * @param items
     * @return
     */
    @RequestMapping("/add")
    public @ResponseBody boolean  addItems(Items items) {
        String sql = "insert into items (title,name,detail) value (?,?,?)";
        Object args[] = {items.getTitle(),items.getName(),items.getDetail()};
        int temp = jdbcTemplate.update(sql, args);
        if(temp > 0) {
            return true;
        }
        return false;
    }

    /**
     * @param id
     * @return
     * 根据ID查询单条信息
     */
    @RequestMapping("/detail/{id}")
    public Map<String, Object> detail(@PathVariable int id) {
        Map<String, Object> map = null;
        List<Map<String, Object>> list = itemsList();
        map = list.get(id);
        return map;
    }

//    /**
//     * 新增数据
//     * @param items
//     * @return
//     */
//    @RequestMapping("/add")
//    public @ResponseBody
//    String  addItems(Items items) {
//        String sql = "insert into items (id,title,name,detail) value (?,?,?,?)";
//        Object args[] = {items.getId(),items.getTitle(),items.getName(),items.getDetail()};
//        int temp = jdbcTemplate.update(sql, args);
//        if(temp > 0) {
//            return "新增成功";
//        }
//        return "新增出现错误";
//    }

    /**
     * @param items
     * @return
     * 删除数据
     */
    @RequestMapping("/del")
    public @ResponseBody String  delItems(Items items) {
        String sql = "delete from items where id = ?";
        Object args[] = {items.getId()};
        int temp = jdbcTemplate.update(sql, args);
        if(temp > 0) {
            return "删除成功";
        }
        return "删除出现错误";
    }

    /**
     * @param items
     * @return
     * 更新操作
     */
    @RequestMapping("/upd")
    public @ResponseBody String  updItems(Items items) {
        String sql = "update items set title = ?,detail = ? where id = ?";
        Object args[] = {items.getTitle(),items.getDetail(),items.getId()};
        int temp = jdbcTemplate.update(sql, args);
        if(temp > 0) {
            return "修改成功";
        }
        return "修改出现错误";
    }
}
