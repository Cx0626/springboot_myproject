package org.example.springboot_project.service.impl;

import org.example.springboot_project.entity.Noticeinfo;
import org.example.springboot_project.mapper.NoticeinfoMapper;
import org.example.springboot_project.service.NoticeinfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 公告信息 服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2025-12-17
 */
@Service
public class NoticeinfoServiceImpl extends ServiceImpl<NoticeinfoMapper, Noticeinfo> implements NoticeinfoService {

}
