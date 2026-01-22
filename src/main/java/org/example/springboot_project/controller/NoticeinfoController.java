package org.example.springboot_project.controller;

import org.example.springboot_project.entity.Noticeinfo;
import org.example.springboot_project.service.NoticeinfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;


@RestController  // 返回json格式
@CrossOrigin("*") // 支持跨域请求
@RequestMapping("/noticeinfo") // 父路径配置
public class NoticeinfoController {

    @Autowired
    private NoticeinfoService noticeinfoservice;



    // 新增
    @RequestMapping("/save")
    public NoticeinfoResult<Boolean> save(@RequestBody Noticeinfo entity) {
        try {
            boolean success = noticeinfoservice.save(entity);
            return NoticeinfoResult.success("新增成功", success);
        } catch (Exception e) {
            return NoticeinfoResult.error("新增异常：" + e.getMessage());
        }
    }

    // 修改
    @RequestMapping("/update")
    public NoticeinfoResult<Boolean> update(@RequestBody Noticeinfo entity) {
        try {
            boolean success = noticeinfoservice.updateById(entity);
            return NoticeinfoResult.success("修改成功", success);
        } catch (Exception e) {
            return NoticeinfoResult.error("修改异常：" + e.getMessage());
        }
    }

    // 删除
    @RequestMapping("/delete/{id}")
    public NoticeinfoResult<Boolean> delete(@PathVariable Integer id) {
        try {
            boolean success = noticeinfoservice.removeById(id);
            return NoticeinfoResult.success("删除成功", success);
        } catch (Exception e) {
            return NoticeinfoResult.error("删除异常：" + e.getMessage());
        }
    }

    // 查单个
    @RequestMapping("/get/{id}")
    public NoticeinfoResult<Noticeinfo> getById(@PathVariable Integer id) {
        try {
            Noticeinfo data = noticeinfoservice.getById(id);
            return data != null ? NoticeinfoResult.success("查询成功", data) : NoticeinfoResult.error("数据不存在");
        } catch (Exception e) {
            return NoticeinfoResult.error("查询异常：" + e.getMessage());
        }
    }

    // 查全部
    @RequestMapping("/list")
    public NoticeinfoResult<List<Noticeinfo>> getAll() {
        try {
            List<Noticeinfo> list = noticeinfoservice.list();
            return NoticeinfoResult.success("查询成功", list);
        } catch (Exception e) {
            return NoticeinfoResult.error("查询异常：" + e.getMessage());
        }
    }

    // 分页查询
    @RequestMapping("/page")
    public NoticeinfoResult<IPage<Noticeinfo>> getPage(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            // 搜索参数：keyword（非必传）
            @RequestParam(required = false) String keyword
    ) {
        try {
            Page<Noticeinfo> page = new Page<>(pageNum, pageSize);
            QueryWrapper<Noticeinfo> queryWrapper = new QueryWrapper<>();

                // 搜索字段 默认表中第二个字段的列名
                String keywordField = "title";
                if (org.springframework.util.StringUtils.hasText(keyword)) {
                    queryWrapper.like(keywordField, keyword);
                }

            IPage<Noticeinfo> resultPage = noticeinfoservice.page(page, queryWrapper);
            return NoticeinfoResult.success("分页查询成功", resultPage);
        } catch (Exception e) {
            return NoticeinfoResult.error("分页查询异常：" + e.getMessage());
        }
    }


     // ======================== 专属返回结果类（NoticeinfoResult） ========================
    // 命名规则：实体类名+Result，避免与其他控制器返回类冲突
    public static class NoticeinfoResult<T> {
        private String msg;  // 消息提示
        private int code;    // 状态码：200成功，500失败
        private T data;      // 返回数据

        // 私有构造器（通过静态方法创建实例）
        private NoticeinfoResult(String msg, int code, T data) {
            this.msg = msg;
            this.code = code;
            this.data = data;
        }

        // 成功：带消息和数据
        public static <T> NoticeinfoResult<T> success(String msg, T data) {
            return new NoticeinfoResult<>(msg, 200, data);
        }

        // 失败：带自定义消息
        public static <T> NoticeinfoResult<T> error(String msg) {
            return new NoticeinfoResult<>(msg, 500, null);
        }

        // Getter（JSON序列化必须，无Setter：结果不可修改）
        public String getMsg() { return msg; }
        public int getCode() { return code; }
        public T getData() { return data; }
    }
    // =========================================================================
}
