package org.example.springboot_project.service.impl;

import org.example.springboot_project.entity.Payment;
import org.example.springboot_project.mapper.PaymentMapper;
import org.example.springboot_project.service.PaymentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 缴费信息 服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2026-01-02
 */
@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment> implements PaymentService {

}
