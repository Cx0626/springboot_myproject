package org.example.springboot_project.controller;

import org.example.springboot_project.entity.Userinfo;
import org.example.springboot_project.service.UserinfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;


@RestController  // 返回json格式
@CrossOrigin("*") // 支持跨域请求
@RequestMapping("/userinfo") // 父路径配置
public class UserinfoController {

    @Autowired
    private UserinfoService userinfoservice;



    // 新增
    @RequestMapping("/save")
    public UserinfoResult<Boolean> save(@RequestBody Userinfo entity) {
        try {
            boolean success = userinfoservice.save(entity);
            return UserinfoResult.success("新增成功", success);
        } catch (Exception e) {
            return UserinfoResult.error("新增异常：" + e.getMessage());
        }
    }

    // 修改
    @RequestMapping("/update")
    public UserinfoResult<Boolean> update(@RequestBody Userinfo entity) {
        try {
            boolean success = userinfoservice.updateById(entity);
            return UserinfoResult.success("修改成功", success);
        } catch (Exception e) {
            return UserinfoResult.error("修改异常：" + e.getMessage());
        }
    }

    // 删除
    @RequestMapping("/delete/{id}")
    public UserinfoResult<Boolean> delete(@PathVariable Integer id) {
        try {
            boolean success = userinfoservice.removeById(id);
            return UserinfoResult.success("删除成功", success);
        } catch (Exception e) {
            return UserinfoResult.error("删除异常：" + e.getMessage());
        }
    }

    // 查单个
    @RequestMapping("/get/{id}")
    public UserinfoResult<Userinfo> getById(@PathVariable Integer id) {
        try {
            Userinfo data = userinfoservice.getById(id);
            return data != null ? UserinfoResult.success("查询成功", data) : UserinfoResult.error("数据不存在");
        } catch (Exception e) {
            return UserinfoResult.error("查询异常：" + e.getMessage());
        }
    }

    // 查全部
    @RequestMapping("/list")
    public UserinfoResult<List<Userinfo>> getAll() {
        try {
            List<Userinfo> list = userinfoservice.list();
            return UserinfoResult.success("查询成功", list);
        } catch (Exception e) {
            return UserinfoResult.error("查询异常：" + e.getMessage());
        }
    }

    // 分页查询
    @RequestMapping("/page")
    public UserinfoResult<IPage<Userinfo>> getPage(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            // 搜索参数：keyword（非必传）
            @RequestParam(required = false) String keyword
    ) {
        try {
            Page<Userinfo> page = new Page<>(pageNum, pageSize);
            QueryWrapper<Userinfo> queryWrapper = new QueryWrapper<>();

                // 搜索字段 默认表中第二个字段的列名
                String keywordField = "name";
                if (org.springframework.util.StringUtils.hasText(keyword)) {
                    queryWrapper.like(keywordField, keyword);
                }

            IPage<Userinfo> resultPage = userinfoservice.page(page, queryWrapper);
            return UserinfoResult.success("分页查询成功", resultPage);
        } catch (Exception e) {
            return UserinfoResult.error("分页查询异常：" + e.getMessage());
        }
    }


     // ======================== 专属返回结果类（UserinfoResult） ========================
    // 命名规则：实体类名+Result，避免与其他控制器返回类冲突
    public static class UserinfoResult<T> {
        private String msg;  // 消息提示
        private int code;    // 状态码：200成功，500失败
        private T data;      // 返回数据

        // 私有构造器（通过静态方法创建实例）
        private UserinfoResult(String msg, int code, T data) {
            this.msg = msg;
            this.code = code;
            this.data = data;
        }

        // 成功：带消息和数据
        public static <T> UserinfoResult<T> success(String msg, T data) {
            return new UserinfoResult<>(msg, 200, data);
        }

        // 失败：带自定义消息
        public static <T> UserinfoResult<T> error(String msg) {
            return new UserinfoResult<>(msg, 500, null);
        }

        // Getter（JSON序列化必须，无Setter：结果不可修改）
        public String getMsg() { return msg; }
        public int getCode() { return code; }
        public T getData() { return data; }
    }
    // =========================================================================
}
