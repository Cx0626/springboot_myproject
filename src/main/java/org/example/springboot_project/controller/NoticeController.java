package org.example.springboot_project.controller;

import org.example.springboot_project.entity.Notice;
import org.example.springboot_project.service.NoticeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;


@RestController  // 返回json格式
@CrossOrigin("*") // 支持跨域请求
@RequestMapping("/notice") // 父路径配置
public class NoticeController {

    @Autowired
    private NoticeService noticeservice;



    // 新增
    @RequestMapping("/save")
    public NoticeResult<Boolean> save(@RequestBody Notice entity) {
        try {
            boolean success = noticeservice.save(entity);
            return NoticeResult.success("新增成功", success);
        } catch (Exception e) {
            return NoticeResult.error("新增异常：" + e.getMessage());
        }
    }

    // 修改
    @RequestMapping("/update")
    public NoticeResult<Boolean> update(@RequestBody Notice entity) {
        try {
            boolean success = noticeservice.updateById(entity);
            return NoticeResult.success("修改成功", success);
        } catch (Exception e) {
            return NoticeResult.error("修改异常：" + e.getMessage());
        }
    }

    // 删除
    @RequestMapping("/delete/{id}")
    public NoticeResult<Boolean> delete(@PathVariable Integer id) {
        try {
            boolean success = noticeservice.removeById(id);
            return NoticeResult.success("删除成功", success);
        } catch (Exception e) {
            return NoticeResult.error("删除异常：" + e.getMessage());
        }
    }

    // 查单个
    @RequestMapping("/get/{id}")
    public NoticeResult<Notice> getById(@PathVariable Integer id) {
        try {
            Notice data = noticeservice.getById(id);
            return data != null ? NoticeResult.success("查询成功", data) : NoticeResult.error("数据不存在");
        } catch (Exception e) {
            return NoticeResult.error("查询异常：" + e.getMessage());
        }
    }

    // 查全部
    @RequestMapping("/list")
    public NoticeResult<List<Notice>> getAll() {
        try {
            List<Notice> list = noticeservice.list();
            return NoticeResult.success("查询成功", list);
        } catch (Exception e) {
            return NoticeResult.error("查询异常：" + e.getMessage());
        }
    }

    // 分页查询
    @RequestMapping("/page")
    public NoticeResult<IPage<Notice>> getPage(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            // 搜索参数：keyword（非必传）
            @RequestParam(required = false) String keyword
    ) {
        try {
            Page<Notice> page = new Page<>(pageNum, pageSize);
            QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();

                // 搜索字段 默认表中第二个字段的列名
                String keywordField = "title";
                if (org.springframework.util.StringUtils.hasText(keyword)) {
                    queryWrapper.like(keywordField, keyword);
                }

            IPage<Notice> resultPage = noticeservice.page(page, queryWrapper);
            return NoticeResult.success("分页查询成功", resultPage);
        } catch (Exception e) {
            return NoticeResult.error("分页查询异常：" + e.getMessage());
        }
    }


     // ======================== 专属返回结果类（NoticeResult） ========================
    // 命名规则：实体类名+Result，避免与其他控制器返回类冲突
    public static class NoticeResult<T> {
        private String msg;  // 消息提示
        private int code;    // 状态码：200成功，500失败
        private T data;      // 返回数据

        // 私有构造器（通过静态方法创建实例）
        private NoticeResult(String msg, int code, T data) {
            this.msg = msg;
            this.code = code;
            this.data = data;
        }

        // 成功：带消息和数据
        public static <T> NoticeResult<T> success(String msg, T data) {
            return new NoticeResult<>(msg, 200, data);
        }

        // 失败：带自定义消息
        public static <T> NoticeResult<T> error(String msg) {
            return new NoticeResult<>(msg, 500, null);
        }

        // Getter（JSON序列化必须，无Setter：结果不可修改）
        public String getMsg() { return msg; }
        public int getCode() { return code; }
        public T getData() { return data; }
    }
    // =========================================================================
}
