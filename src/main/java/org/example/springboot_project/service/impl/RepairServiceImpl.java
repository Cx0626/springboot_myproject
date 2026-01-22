package org.example.springboot_project.service.impl;

import org.example.springboot_project.entity.Repair;
import org.example.springboot_project.mapper.RepairMapper;
import org.example.springboot_project.service.RepairService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 报修信息 服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2026-01-02
 */
@Service
public class RepairServiceImpl extends ServiceImpl<RepairMapper, Repair> implements RepairService {

}
