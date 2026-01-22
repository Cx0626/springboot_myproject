package org.example.springboot_project.controller;

import org.example.springboot_project.entity.Payment;
import org.example.springboot_project.service.PaymentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;


@RestController  // 返回json格式
@CrossOrigin("*") // 支持跨域请求
@RequestMapping("/payment") // 父路径配置
public class PaymentController {

    @Autowired
    private PaymentService paymentservice;



    // 新增
    @RequestMapping("/save")
    public PaymentResult<Boolean> save(@RequestBody Payment entity) {
        try {
            boolean success = paymentservice.save(entity);
            return PaymentResult.success("新增成功", success);
        } catch (Exception e) {
            return PaymentResult.error("新增异常：" + e.getMessage());
        }
    }

    // 修改
    @RequestMapping("/update")
    public PaymentResult<Boolean> update(@RequestBody Payment entity) {
        try {
            boolean success = paymentservice.updateById(entity);
            return PaymentResult.success("修改成功", success);
        } catch (Exception e) {
            return PaymentResult.error("修改异常：" + e.getMessage());
        }
    }

    // 删除
    @RequestMapping("/delete/{id}")
    public PaymentResult<Boolean> delete(@PathVariable Integer id) {
        try {
            boolean success = paymentservice.removeById(id);
            return PaymentResult.success("删除成功", success);
        } catch (Exception e) {
            return PaymentResult.error("删除异常：" + e.getMessage());
        }
    }

    // 查单个
    @RequestMapping("/get/{id}")
    public PaymentResult<Payment> getById(@PathVariable Integer id) {
        try {
            Payment data = paymentservice.getById(id);
            return data != null ? PaymentResult.success("查询成功", data) : PaymentResult.error("数据不存在");
        } catch (Exception e) {
            return PaymentResult.error("查询异常：" + e.getMessage());
        }
    }

    // 查全部
    @RequestMapping("/list")
    public PaymentResult<List<Payment>> getAll() {
        try {
            List<Payment> list = paymentservice.list();
            return PaymentResult.success("查询成功", list);
        } catch (Exception e) {
            return PaymentResult.error("查询异常：" + e.getMessage());
        }
    }

    // 分页查询
    @RequestMapping("/page")
    public PaymentResult<IPage<Payment>> getPage(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            // 搜索参数：keyword（非必传）
            @RequestParam(required = false) String keyword
    ) {
        try {
            Page<Payment> page = new Page<>(pageNum, pageSize);
            QueryWrapper<Payment> queryWrapper = new QueryWrapper<>();

                // 搜索字段 默认表中第二个字段的列名
                String keywordField = "user_id";
                if (org.springframework.util.StringUtils.hasText(keyword)) {
                    queryWrapper.like(keywordField, keyword);
                }

            IPage<Payment> resultPage = paymentservice.page(page, queryWrapper);
            return PaymentResult.success("分页查询成功", resultPage);
        } catch (Exception e) {
            return PaymentResult.error("分页查询异常：" + e.getMessage());
        }
    }


     // ======================== 专属返回结果类（PaymentResult） ========================
    // 命名规则：实体类名+Result，避免与其他控制器返回类冲突
    public static class PaymentResult<T> {
        private String msg;  // 消息提示
        private int code;    // 状态码：200成功，500失败
        private T data;      // 返回数据

        // 私有构造器（通过静态方法创建实例）
        private PaymentResult(String msg, int code, T data) {
            this.msg = msg;
            this.code = code;
            this.data = data;
        }

        // 成功：带消息和数据
        public static <T> PaymentResult<T> success(String msg, T data) {
            return new PaymentResult<>(msg, 200, data);
        }

        // 失败：带自定义消息
        public static <T> PaymentResult<T> error(String msg) {
            return new PaymentResult<>(msg, 500, null);
        }

        // Getter（JSON序列化必须，无Setter：结果不可修改）
        public String getMsg() { return msg; }
        public int getCode() { return code; }
        public T getData() { return data; }
    }
    // =========================================================================
}
