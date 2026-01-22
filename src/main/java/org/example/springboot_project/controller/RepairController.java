package org.example.springboot_project.controller;

import org.example.springboot_project.entity.Repair;
import org.example.springboot_project.service.RepairService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;


@RestController  // 返回json格式
@CrossOrigin("*") // 支持跨域请求
@RequestMapping("/repair") // 父路径配置
public class RepairController {

    @Autowired
    private RepairService repairservice;



    // 【用户端】查询我的报修（按userId分页）
    @RequestMapping("/my")
    public RepairResult<IPage<Repair>> getMyRepair(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam Long userId, // 必传：当前登录用户ID
            @RequestParam(required = false) String keyword, // 可选：搜索报修类型/标题等
            @RequestParam(required = false) String status // 可选：筛选报修状态
    ) {
        try {
            Page<Repair> page = new Page<>(pageNum, pageSize);
            QueryWrapper<Repair> queryWrapper = new QueryWrapper<>();

            // 核心：只查当前用户的报修（userId匹配）
            queryWrapper.eq("user_id", userId);

            // 可选：按状态筛选（待处理/处理中/已完成）
            if (org.springframework.util.StringUtils.hasText(status)) {
                queryWrapper.eq("status", status);
            }

            // 可选：按关键词搜索（报修类型/标题/房间号等）
            if (org.springframework.util.StringUtils.hasText(keyword)) {
                queryWrapper.and(wrapper -> wrapper
                        .like("type", keyword)
                        .or()
                        .like("title", keyword)
                        .or()
                        .like("room_id", keyword)
                );
            }

            // 按报修时间倒序（最新的在前）
            queryWrapper.orderByDesc("repair_time");

            IPage<Repair> resultPage = repairservice.page(page, queryWrapper);
            return RepairResult.success("我的报修查询成功", resultPage);
        } catch (Exception e) {
            return RepairResult.error("我的报修查询异常：" + e.getMessage());
        }
    }


    // 新增
    @RequestMapping("/save")
    public RepairResult<Boolean> save(@RequestBody Repair entity) {
        try {
            boolean success = repairservice.save(entity);
            return RepairResult.success("新增成功", success);
        } catch (Exception e) {
            return RepairResult.error("新增异常：" + e.getMessage());
        }
    }

    // 修改
    @RequestMapping("/update")
    public RepairResult<Boolean> update(@RequestBody Repair entity) {
        try {
            boolean success = repairservice.updateById(entity);
            return RepairResult.success("修改成功", success);
        } catch (Exception e) {
            return RepairResult.error("修改异常：" + e.getMessage());
        }
    }

    // 删除
    @RequestMapping("/delete/{id}")
    public RepairResult<Boolean> delete(@PathVariable Integer id) {
        try {
            boolean success = repairservice.removeById(id);
            return RepairResult.success("删除成功", success);
        } catch (Exception e) {
            return RepairResult.error("删除异常：" + e.getMessage());
        }
    }

    // 查单个
    @RequestMapping("/get/{id}")
    public RepairResult<Repair> getById(@PathVariable Integer id) {
        try {
            Repair data = repairservice.getById(id);
            return data != null ? RepairResult.success("查询成功", data) : RepairResult.error("数据不存在");
        } catch (Exception e) {
            return RepairResult.error("查询异常：" + e.getMessage());
        }
    }

    // 查全部
    @RequestMapping("/list")
    public RepairResult<List<Repair>> getAll() {
        try {
            List<Repair> list = repairservice.list();
            return RepairResult.success("查询成功", list);
        } catch (Exception e) {
            return RepairResult.error("查询异常：" + e.getMessage());
        }
    }

    // 分页查询
    @RequestMapping("/page")
    public RepairResult<IPage<Repair>> getPage(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            // 搜索参数：keyword（非必传）
            @RequestParam(required = false) String keyword
    ) {
        try {
            Page<Repair> page = new Page<>(pageNum, pageSize);
            QueryWrapper<Repair> queryWrapper = new QueryWrapper<>();

                // 搜索字段 默认表中第二个字段的列名
                String keywordField = "user_id";
                if (org.springframework.util.StringUtils.hasText(keyword)) {
                    queryWrapper.like(keywordField, keyword);
                }

            IPage<Repair> resultPage = repairservice.page(page, queryWrapper);
            return RepairResult.success("分页查询成功", resultPage);
        } catch (Exception e) {
            return RepairResult.error("分页查询异常：" + e.getMessage());
        }
    }


     // ======================== 专属返回结果类（RepairResult） ========================
    // 命名规则：实体类名+Result，避免与其他控制器返回类冲突
    public static class RepairResult<T> {
        private String msg;  // 消息提示
        private int code;    // 状态码：200成功，500失败
        private T data;      // 返回数据

        // 私有构造器（通过静态方法创建实例）
        private RepairResult(String msg, int code, T data) {
            this.msg = msg;
            this.code = code;
            this.data = data;
        }

        // 成功：带消息和数据
        public static <T> RepairResult<T> success(String msg, T data) {
            return new RepairResult<>(msg, 200, data);
        }

        // 失败：带自定义消息
        public static <T> RepairResult<T> error(String msg) {
            return new RepairResult<>(msg, 500, null);
        }

        // Getter（JSON序列化必须，无Setter：结果不可修改）
        public String getMsg() { return msg; }
        public int getCode() { return code; }
        public T getData() { return data; }
    }
    // =========================================================================
}
