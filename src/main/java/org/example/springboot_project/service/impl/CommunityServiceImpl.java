package org.example.springboot_project.service.impl;

import org.example.springboot_project.entity.Community;
import org.example.springboot_project.mapper.CommunityMapper;
import org.example.springboot_project.service.CommunityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 社区信息 服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2026-01-02
 */
@Service
public class CommunityServiceImpl extends ServiceImpl<CommunityMapper, Community> implements CommunityService {

}
