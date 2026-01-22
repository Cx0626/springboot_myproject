package org.example.springboot_project.service.impl;

import org.example.springboot_project.entity.Userinfo;
import org.example.springboot_project.mapper.UserinfoMapper;
import org.example.springboot_project.service.UserinfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息 服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2025-12-17
 */
@Service
public class UserinfoServiceImpl extends ServiceImpl<UserinfoMapper, Userinfo> implements UserinfoService {

}
