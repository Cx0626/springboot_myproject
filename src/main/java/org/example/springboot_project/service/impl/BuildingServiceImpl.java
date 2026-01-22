package org.example.springboot_project.service.impl;

import org.example.springboot_project.entity.Building;
import org.example.springboot_project.mapper.BuildingMapper;
import org.example.springboot_project.service.BuildingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 楼栋信息 服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2026-01-02
 */
@Service
public class BuildingServiceImpl extends ServiceImpl<BuildingMapper, Building> implements BuildingService {

}
