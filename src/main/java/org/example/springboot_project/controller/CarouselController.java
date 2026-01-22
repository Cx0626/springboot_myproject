package org.example.springboot_project.controller;

import org.example.springboot_project.entity.Carousel;
import org.example.springboot_project.service.CarouselService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;


@RestController  // 返回json格式
@CrossOrigin("*") // 支持跨域请求
@RequestMapping("/carousel") // 父路径配置
public class CarouselController {

    @Autowired
    private CarouselService carouselservice;



    // 新增
    @RequestMapping("/save")
    public CarouselResult<Boolean> save(@RequestBody Carousel entity) {
        try {
            boolean success = carouselservice.save(entity);
            return CarouselResult.success("新增成功", success);
        } catch (Exception e) {
            return CarouselResult.error("新增异常：" + e.getMessage());
        }
    }

    // 修改
    @RequestMapping("/update")
    public CarouselResult<Boolean> update(@RequestBody Carousel entity) {
        try {
            boolean success = carouselservice.updateById(entity);
            return CarouselResult.success("修改成功", success);
        } catch (Exception e) {
            return CarouselResult.error("修改异常：" + e.getMessage());
        }
    }

    // 删除
    @RequestMapping("/delete/{id}")
    public CarouselResult<Boolean> delete(@PathVariable Integer id) {
        try {
            boolean success = carouselservice.removeById(id);
            return CarouselResult.success("删除成功", success);
        } catch (Exception e) {
            return CarouselResult.error("删除异常：" + e.getMessage());
        }
    }

    // 查单个
    @RequestMapping("/get/{id}")
    public CarouselResult<Carousel> getById(@PathVariable Integer id) {
        try {
            Carousel data = carouselservice.getById(id);
            return data != null ? CarouselResult.success("查询成功", data) : CarouselResult.error("数据不存在");
        } catch (Exception e) {
            return CarouselResult.error("查询异常：" + e.getMessage());
        }
    }

    // 查全部
    @RequestMapping("/list")
    public CarouselResult<List<Carousel>> getAll() {
        try {
            List<Carousel> list = carouselservice.list();
            return CarouselResult.success("查询成功", list);
        } catch (Exception e) {
            return CarouselResult.error("查询异常：" + e.getMessage());
        }
    }

    // 分页查询
    @RequestMapping("/page")
    public CarouselResult<IPage<Carousel>> getPage(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            // 搜索参数：keyword（非必传）
            @RequestParam(required = false) String keyword
    ) {
        try {
            Page<Carousel> page = new Page<>(pageNum, pageSize);
            QueryWrapper<Carousel> queryWrapper = new QueryWrapper<>();

                // 搜索字段 默认表中第二个字段的列名
                String keywordField = "name";
                if (org.springframework.util.StringUtils.hasText(keyword)) {
                    queryWrapper.like(keywordField, keyword);
                }

            IPage<Carousel> resultPage = carouselservice.page(page, queryWrapper);
            return CarouselResult.success("分页查询成功", resultPage);
        } catch (Exception e) {
            return CarouselResult.error("分页查询异常：" + e.getMessage());
        }
    }


     // ======================== 专属返回结果类（CarouselResult） ========================
    // 命名规则：实体类名+Result，避免与其他控制器返回类冲突
    public static class CarouselResult<T> {
        private String msg;  // 消息提示
        private int code;    // 状态码：200成功，500失败
        private T data;      // 返回数据

        // 私有构造器（通过静态方法创建实例）
        private CarouselResult(String msg, int code, T data) {
            this.msg = msg;
            this.code = code;
            this.data = data;
        }

        // 成功：带消息和数据
        public static <T> CarouselResult<T> success(String msg, T data) {
            return new CarouselResult<>(msg, 200, data);
        }

        // 失败：带自定义消息
        public static <T> CarouselResult<T> error(String msg) {
            return new CarouselResult<>(msg, 500, null);
        }

        // Getter（JSON序列化必须，无Setter：结果不可修改）
        public String getMsg() { return msg; }
        public int getCode() { return code; }
        public T getData() { return data; }
    }
    // =========================================================================
}
