package org.example.springboot_project;

import org.example.springboot_project.entity.Userinfo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.springboot_project.service.UserinfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Transactional
@Rollback
class UserinfoServiceTest {

    @Autowired
    private UserinfoService userinfoService;

    /**
     * 1. 测试新增单个用户
     */
    @Test
    void testInsertSingleUser() {
        System.out.println("=== 测试新增单个用户 ===");
        Userinfo user = new Userinfo();
        user.setName("测试用户A");
        user.setPhone("13800138000");
        user.setEmail("test_a@example.com");
        user.setRole("USER");
        user.setAge(25);
        user.setSex("男");
        user.setPassword("123456");
        user.setIntro("这是一个测试用户A");
        
        boolean result = userinfoService.save(user);
        System.out.println("新增结果: " + result);
        System.out.println("生成ID: " + user.getId());
    }

    /**
     * 2. 测试根据ID查询用户
     */
    @Test
    void testSelectUserById() {
        System.out.println("=== 测试根据ID查询用户 ===");
        
        // 先插入一条测试数据
        Userinfo newUser = new Userinfo();
        newUser.setName("查询测试用户");
        newUser.setEmail("select_test@example.com");
        newUser.setPhone("13800138001");
        newUser.setRole("ADMIN");
        newUser.setAge(30);
        newUser.setSex("女");
        newUser.setPassword("654321");
        newUser.setIntro("用于查询测试的用户");
        userinfoService.save(newUser);
        
        // 执行查询
        Userinfo result = userinfoService.getById(newUser.getId());
        System.out.println("查询到的用户: " + result);
    }

    /**
     * 3. 测试更新用户信息
     */
    @Test
    void testUpdateUserInfo() {
        System.out.println("=== 测试更新用户信息 ===");
        
        // 先插入一条测试数据
        Userinfo user = new Userinfo();
        user.setName("更新前用户");
        user.setEmail("before_update@example.com");
        user.setPhone("13800138002");
        user.setRole("USER");
        user.setAge(20);
        user.setSex("男");
        user.setPassword("111111");
        userinfoService.save(user);
        
        // 执行更新
        user.setName("更新后用户");
        user.setEmail("after_update@example.com");
        user.setAge(30);
        
        boolean updateResult = userinfoService.updateById(user);
        System.out.println("更新结果: " + updateResult);
        
        // 验证更新结果
        Userinfo updatedUser = userinfoService.getById(user.getId());
        System.out.println("更新后的用户: " + updatedUser);
    }

    /**
     * 4. 测试根据ID删除用户
     */
    @Test
    void testDeleteUserById() {
        System.out.println("=== 测试根据ID删除用户 ===");
        
        // 先插入一条测试数据
        Userinfo user = new Userinfo();
        user.setName("待删除用户");
        user.setEmail("to_delete@example.com");
        user.setPhone("13800138003");
        user.setRole("USER");
        user.setAge(35);
        user.setSex("女");
        userinfoService.save(user);
        
        // 执行删除
        boolean deleteResult = userinfoService.removeById(user.getId());
        System.out.println("删除结果: " + deleteResult);
        
        // 尝试查询已删除的用户
        Userinfo deletedUser = userinfoService.getById(user.getId());
        System.out.println("删除后查询结果: " + deletedUser);
    }

    /**
     * 5. 测试查询所有用户
     */
    @Test
    void testSelectAllUsers() {
        System.out.println("=== 测试查询所有用户 ===");
        
        // 插入测试数据
        Userinfo user1 = new Userinfo();
        user1.setName("全查询用户1");
        user1.setEmail("all_query_1@example.com");
        user1.setPhone("13800138004");
        user1.setRole("ADMIN");
        
        Userinfo user2 = new Userinfo();
        user2.setName("全查询用户2");
        user2.setEmail("all_query_2@example.com");
        user2.setPhone("13800138005");
        user2.setRole("USER");
        
        userinfoService.save(user1);
        userinfoService.save(user2);
        
        // 执行查询
        List<Userinfo> allUsers = userinfoService.list();
        System.out.println("总用户数: " + allUsers.size());
        System.out.println("用户列表: ");
        allUsers.forEach(u -> System.out.println("  - " + u.getName() + " (" + u.getEmail() + ")"));
    }

    /**
     * 6. 测试条件查询
     */
    @Test
    void testSelectByCondition() {
        System.out.println("=== 测试条件查询 ===");
        
        // 插入测试数据
        Userinfo adminUser = new Userinfo();
        adminUser.setName("管理员用户");
        adminUser.setEmail("admin_user@example.com");
        adminUser.setPhone("13800138006");
        adminUser.setRole("ADMIN");
        adminUser.setAge(40);
        
        Userinfo normalUser = new Userinfo();
        normalUser.setName("普通用户");
        normalUser.setEmail("normal_user@example.com");
        normalUser.setPhone("13800138007");
        normalUser.setRole("USER");
        normalUser.setAge(25);
        
        userinfoService.save(adminUser);
        userinfoService.save(normalUser);
        
        // 创建查询条件
        QueryWrapper<Userinfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role", "ADMIN");
        
        // 执行条件查询
        List<Userinfo> adminUsers = userinfoService.list(queryWrapper);
        System.out.println("管理员用户数量: " + adminUsers.size());
        System.out.println("管理员用户列表: ");
        adminUsers.forEach(u -> System.out.println("  - " + u.getName() + " (" + u.getRole() + ")"));
    }

    /**
     * 7. 测试模糊查询
     */
    @Test
    void testFuzzyQuery() {
        System.out.println("=== 测试模糊查询 ===");
        
        // 插入测试数据
        Userinfo user1 = new Userinfo();
        user1.setName("张三丰");
        user1.setEmail("zhangsanfeng@example.com");
        user1.setPhone("13800138008");
        
        Userinfo user2 = new Userinfo();
        user2.setName("张无忌");
        user2.setEmail("zhangwuji@example.com");
        user2.setPhone("13800138009");
        
        Userinfo user3 = new Userinfo();
        user3.setName("李小龙");
        user3.setEmail("lixiaolong@example.com");
        user3.setPhone("13800138010");
        
        userinfoService.save(user1);
        userinfoService.save(user2);
        userinfoService.save(user3);
        
        // 创建模糊查询条件
        QueryWrapper<Userinfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "张");
        
        // 执行模糊查询
        List<Userinfo> result = userinfoService.list(queryWrapper);
        System.out.println("姓张的用户数量: " + result.size());
        result.forEach(u -> System.out.println("  - " + u.getName()));
    }

    /**
     * 8. 测试分页查询
     */
    @Test
    void testPaginationQuery() {
        System.out.println("=== 测试分页查询 ===");
        
        // 插入测试数据
        for (int i = 1; i <= 12; i++) {
            Userinfo user = new Userinfo();
            user.setName("分页用户" + i);
            user.setEmail("page_user_" + i + "@example.com");
            user.setPhone("13800138" + String.format("%04d", 100 + i));
            user.setRole("USER");
            user.setAge(20 + i);
            userinfoService.save(user);
        }
        
        // 创建分页对象（第2页，每页5条）
        Page<Userinfo> page = new Page<>(2, 5);
        
        // 执行分页查询
        IPage<Userinfo> pageResult = userinfoService.page(page);
        
        System.out.println("总记录数: " + pageResult.getTotal());
        System.out.println("总页数: " + pageResult.getPages());
        System.out.println("当前页码: " + pageResult.getCurrent());
        System.out.println("每页大小: " + pageResult.getSize());
        System.out.println("当前页数据: ");
        pageResult.getRecords().forEach(u -> 
            System.out.println("  - " + u.getName() + " (年龄: " + u.getAge() + ")")
        );
    }

    /**
     * 9. 测试批量插入
     */
    @Test
    void testBatchInsert() {
        System.out.println("=== 测试批量插入 ===");
        
        // 创建批量数据
        List<Userinfo> users = Arrays.asList(
            createSimpleUser("批量用户A", "batch_a@example.com", "13800138100"),
            createSimpleUser("批量用户B", "batch_b@example.com", "13800138101"),
            createSimpleUser("批量用户C", "batch_c@example.com", "13800138102"),
            createSimpleUser("批量用户D", "batch_d@example.com", "13800138103")
        );
        
        // 执行批量插入
        boolean batchResult = userinfoService.saveBatch(users);
        System.out.println("批量插入结果: " + batchResult);
        System.out.println("插入的用户ID: ");
        users.forEach(u -> System.out.println("  - " + u.getName() + ": " + u.getId()));
    }

    /**
     * 10. 测试批量删除
     */
    @Test
    void testBatchDelete() {
        System.out.println("=== 测试批量删除 ===");
        
        // 插入测试数据
        Userinfo user1 = new Userinfo();
        user1.setName("待批量删除A");
        user1.setEmail("batch_delete_a@example.com");
        
        Userinfo user2 = new Userinfo();
        user2.setName("待批量删除B");
        user2.setEmail("batch_delete_b@example.com");
        
        Userinfo user3 = new Userinfo();
        user3.setName("待批量删除C");
        user3.setEmail("batch_delete_c@example.com");
        
        userinfoService.save(user1);
        userinfoService.save(user2);
        userinfoService.save(user3);
        
        // 收集要删除的ID
        List<Integer> idsToDelete = Arrays.asList(user1.getId(), user2.getId());
        
        // 执行批量删除
        boolean deleteResult = userinfoService.removeByIds(idsToDelete);
        System.out.println("批量删除结果: " + deleteResult);
        System.out.println("尝试删除的ID: " + idsToDelete);
        
        // 查询剩余用户
        List<Userinfo> remainingUsers = userinfoService.list();
        System.out.println("删除后剩余用户数: " + remainingUsers.size());
    }

    /**
     * 11. 测试多条件组合查询
     */
    @Test
    void testMultiConditionQuery() {
        System.out.println("=== 测试多条件组合查询 ===");
        
        // 插入测试数据
        userinfoService.save(createFullUser("张三", 25, "男", "ADMIN", "zhangsan@example.com"));
        userinfoService.save(createFullUser("李四", 30, "男", "USER", "lisi@example.com"));
        userinfoService.save(createFullUser("王五", 25, "女", "ADMIN", "wangwu@example.com"));
        userinfoService.save(createFullUser("赵六", 25, "男", "USER", "zhaoliu@example.com"));
        
        // 创建多条件查询
        QueryWrapper<Userinfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("age", 25)
                   .eq("sex", "男")
                   .eq("role", "ADMIN");
        
        // 执行查询
        List<Userinfo> result = userinfoService.list(queryWrapper);
        System.out.println("符合条件的用户数量: " + result.size());
        result.forEach(u -> System.out.println("  - " + u.getName() + " (年龄: " + u.getAge() + 
                                               ", 性别: " + u.getSex() + ", 角色: " + u.getRole() + ")"));
    }

    /**
     * 12. 测试排序查询
     */
    @Test
    void testOrderByQuery() {
        System.out.println("=== 测试排序查询 ===");
        
        // 插入测试数据
        userinfoService.save(createFullUser("用户A", 20, "男", "USER", "user_a@example.com"));
        userinfoService.save(createFullUser("用户B", 35, "女", "ADMIN", "user_b@example.com"));
        userinfoService.save(createFullUser("用户C", 28, "男", "USER", "user_c@example.com"));
        userinfoService.save(createFullUser("用户D", 40, "女", "ADMIN", "user_d@example.com"));
        
        // 创建排序查询
        QueryWrapper<Userinfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("age");  // 按年龄降序
        
        // 执行查询
        List<Userinfo> result = userinfoService.list(queryWrapper);
        System.out.println("按年龄降序排列的用户:");
        for (int i = 0; i < result.size(); i++) {
            Userinfo user = result.get(i);
            System.out.println("  " + (i+1) + ". " + user.getName() + " (年龄: " + user.getAge() + ")");
        }
    }

    /**
     * 13. 测试选择特定字段查询
     */
    @Test
    void testSelectSpecificFields() {
        System.out.println("=== 测试选择特定字段查询 ===");
        
        // 插入测试数据
        Userinfo user = new Userinfo();
        user.setName("字段选择测试");
        user.setEmail("select_fields@example.com");
        user.setPhone("13800138110");
        user.setAge(30);
        user.setSex("男");
        user.setRole("USER");
        user.setPassword("password123");
        user.setIntro("这是一个完整的用户信息，但查询时只选择部分字段");
        userinfoService.save(user);
        
        // 创建特定字段查询
        QueryWrapper<Userinfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "name", "email", "age");
        
        // 执行查询
        List<Userinfo> result = userinfoService.list(queryWrapper);
        System.out.println("查询结果（只包含指定字段）:");
        result.forEach(u -> {
            System.out.println("  - ID: " + u.getId() + 
                             ", 姓名: " + u.getName() + 
                             ", 邮箱: " + u.getEmail() + 
                             ", 年龄: " + u.getAge());
        });
    }

    /**
     * 14. 测试分组查询
     */
    @Test
    void testGroupByQuery() {
        System.out.println("=== 测试分组查询 ===");
        
        // 清空并插入测试数据
        userinfoService.remove(null);
        userinfoService.save(createFullUser("员工A", 25, "男", "STAFF", "staff_a@example.com"));
        userinfoService.save(createFullUser("员工B", 30, "女", "STAFF", "staff_b@example.com"));
        userinfoService.save(createFullUser("经理A", 35, "男", "MANAGER", "manager_a@example.com"));
        userinfoService.save(createFullUser("管理员A", 40, "女", "ADMIN", "admin_a@example.com"));
        userinfoService.save(createFullUser("员工C", 28, "男", "STAFF", "staff_c@example.com"));
        
        // 创建分组查询
        QueryWrapper<Userinfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("role", "count(*) as user_count", "avg(age) as avg_age")
                   .groupBy("role");
        
        // 执行分组查询
        List<Map<String, Object>> result = userinfoService.listMaps(queryWrapper);
        System.out.println("按角色分组统计:");
        result.forEach(map -> {
            System.out.println("  角色: " + map.get("role") + 
                             ", 用户数: " + map.get("user_count") + 
                             ", 平均年龄: " + map.get("avg_age"));
        });
    }

    /**
     * 15. 测试存在性检查
     */
    @Test
    void testExistsQuery() {
        System.out.println("=== 测试存在性检查 ===");
        
        // 插入测试数据
        Userinfo user = new Userinfo();
        user.setName("存在性测试");
        user.setEmail("exist_test@example.com");
        user.setPhone("13800138120");
        user.setRole("USER");
        user.setAge(30);
        userinfoService.save(user);
        
        // 检查存在的记录
        QueryWrapper<Userinfo> existsWrapper = new QueryWrapper<>();
        existsWrapper.eq("name", "存在性测试");
        boolean exists = userinfoService.exists(existsWrapper);
        System.out.println("用户'存在性测试'是否存在: " + exists);
        
        // 检查不存在的记录
        QueryWrapper<Userinfo> notExistsWrapper = new QueryWrapper<>();
        notExistsWrapper.eq("name", "不存在的用户");
        boolean notExists = userinfoService.exists(notExistsWrapper);
        System.out.println("用户'不存在的用户'是否存在: " + notExists);
    }

    /**
     * 16. 测试范围查询
     */
    @Test
    void testRangeQuery() {
        System.out.println("=== 测试范围查询 ===");
        
        // 插入测试数据
        userinfoService.save(createFullUser("年轻人A", 18, "男", "USER", "young_a@example.com"));
        userinfoService.save(createFullUser("年轻人B", 22, "女", "USER", "young_b@example.com"));
        userinfoService.save(createFullUser("中年人A", 35, "男", "ADMIN", "middle_a@example.com"));
        userinfoService.save(createFullUser("中年人B", 45, "女", "ADMIN", "middle_b@example.com"));
        userinfoService.save(createFullUser("老年人A", 60, "男", "USER", "old_a@example.com"));
        
        // 创建范围查询（年龄在20-40之间）
        QueryWrapper<Userinfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("age", 20, 40)
                   .orderByAsc("age");
        
        // 执行范围查询
        List<Userinfo> result = userinfoService.list(queryWrapper);
        System.out.println("年龄在20-40岁之间的用户:");
        result.forEach(u -> System.out.println("  - " + u.getName() + " (年龄: " + u.getAge() + ")"));
    }

    /**
     * 辅助方法：创建简单用户
     */
    private Userinfo createSimpleUser(String name, String email, String phone) {
        Userinfo user = new Userinfo();
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
        user.setRole("USER");
        user.setAge(25);
        user.setSex("男");
        user.setPassword("123456");
        return user;
    }
    
    /**
     * 辅助方法：创建完整用户
     */
    private Userinfo createFullUser(String name, Integer age, String sex, String role, String email) {
        Userinfo user = new Userinfo();
        user.setName(name);
        user.setAge(age);
        user.setSex(sex);
        user.setRole(role);
        user.setEmail(email);
        user.setPhone("138" + String.format("%08d", (int)(Math.random() * 100000000)));
        user.setPassword("password123");
        return user;
    }
}