package ${package.Controller};

import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;


@RestController  // 返回json格式
@CrossOrigin("*") // 支持跨域请求
@RequestMapping("/${table.entityPath}") // 父路径配置
public class ${table.controllerName} {

    @Autowired
    private ${table.serviceName} ${table.serviceName?lower_case};



    // 新增
    @RequestMapping("/save")
    public ${entity}Result<Boolean> save(@RequestBody ${entity} entity) {
        try {
            boolean success = ${table.serviceName?lower_case}.save(entity);
            return ${entity}Result.success("新增成功", success);
        } catch (Exception e) {
            return ${entity}Result.error("新增异常：" + e.getMessage());
        }
    }

    // 修改
    @RequestMapping("/update")
    public ${entity}Result<Boolean> update(@RequestBody ${entity} entity) {
        try {
            boolean success = ${table.serviceName?lower_case}.updateById(entity);
            return ${entity}Result.success("修改成功", success);
        } catch (Exception e) {
            return ${entity}Result.error("修改异常：" + e.getMessage());
        }
    }

    // 删除
    @RequestMapping("/delete/{id}")
    public ${entity}Result<Boolean> delete(@PathVariable Integer id) {
        try {
            boolean success = ${table.serviceName?lower_case}.removeById(id);
            return ${entity}Result.success("删除成功", success);
        } catch (Exception e) {
            return ${entity}Result.error("删除异常：" + e.getMessage());
        }
    }

    // 查单个
    @RequestMapping("/get/{id}")
    public ${entity}Result<${entity}> getById(@PathVariable Integer id) {
        try {
            ${entity} data = ${table.serviceName?lower_case}.getById(id);
            return data != null ? ${entity}Result.success("查询成功", data) : ${entity}Result.error("数据不存在");
        } catch (Exception e) {
            return ${entity}Result.error("查询异常：" + e.getMessage());
        }
    }

    // 查全部
    @RequestMapping("/list")
    public ${entity}Result<List<${entity}>> getAll() {
        try {
            List<${entity}> list = ${table.serviceName?lower_case}.list();
            return ${entity}Result.success("查询成功", list);
        } catch (Exception e) {
            return ${entity}Result.error("查询异常：" + e.getMessage());
        }
    }

    // 分页查询
    @RequestMapping("/page")
    public ${entity}Result<IPage<${entity}>> getPage(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            // 搜索参数：keyword（非必传）
            @RequestParam(required = false) String keyword
    ) {
        try {
            Page<${entity}> page = new Page<>(pageNum, pageSize);
            QueryWrapper<${entity}> queryWrapper = new QueryWrapper<>();

            <#if (table.fields?has_content) && (table.fields?size >= 2)>
                // 搜索字段 默认表中第二个字段的列名
                String keywordField = "${table.fields[1].name}";
                if (org.springframework.util.StringUtils.hasText(keyword)) {
                    queryWrapper.like(keywordField, keyword);
                }
            </#if>

            IPage<${entity}> resultPage = ${table.serviceName?lower_case}.page(page, queryWrapper);
            return ${entity}Result.success("分页查询成功", resultPage);
        } catch (Exception e) {
            return ${entity}Result.error("分页查询异常：" + e.getMessage());
        }
    }


     // ======================== 专属返回结果类（${entity}Result） ========================
    // 命名规则：实体类名+Result，避免与其他控制器返回类冲突
    public static class ${entity}Result<T> {
        private String msg;  // 消息提示
        private int code;    // 状态码：200成功，500失败
        private T data;      // 返回数据

        // 私有构造器（通过静态方法创建实例）
        private ${entity}Result(String msg, int code, T data) {
            this.msg = msg;
            this.code = code;
            this.data = data;
        }

        // 成功：带消息和数据
        public static <T> ${entity}Result<T> success(String msg, T data) {
            return new ${entity}Result<>(msg, 200, data);
        }

        // 失败：带自定义消息
        public static <T> ${entity}Result<T> error(String msg) {
            return new ${entity}Result<>(msg, 500, null);
        }

        // Getter（JSON序列化必须，无Setter：结果不可修改）
        public String getMsg() { return msg; }
        public int getCode() { return code; }
        public T getData() { return data; }
    }
    // =========================================================================
}
