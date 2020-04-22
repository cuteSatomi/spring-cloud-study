package com.zzx.springcloud.controller;

import com.zzx.springcloud.entities.CommonResult;
import com.zzx.springcloud.entities.Payment;
import com.zzx.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("╭(●｀∀´●)╯插入结果:" + result);

        if (result > 0) {
            return new CommonResult(200, "♪（＾∀＾●）插入数据成功,serverPort:" + serverPort, result);
        } else {
            return new CommonResult(444, "(╥╯^╰╥) 插入数据失败,serverPort:" + serverPort, null);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("╭(●｀∀´●)╯查询结果:" + payment);

        if (payment != null) {
            return new CommonResult(200, "♪（＾∀＾●）查询成功,serverPort:"+serverPort, payment);
        } else {
            return new CommonResult(444, "(╥╯^╰╥) 没有ID为" + id + "的记录,serverPort:"+serverPort, null);
        }
    }
}
