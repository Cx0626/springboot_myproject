package org.example.springboot_project.service.impl;

import org.example.springboot_project.entity.Notice;
import org.example.springboot_project.mapper.NoticeMapper;
import org.example.springboot_project.service.NoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 公告信息 服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2026-01-02
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

}
