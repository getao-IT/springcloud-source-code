package com.king.springcloud.contrller;

import com.king.springcloud.entities.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@Slf4j
@Controller
@ResponseBody
@RequestMapping("/payment")
public class PaymentController {

    @GetMapping("/getUuid")
    public String getUuid() {
        return "payment8004:" + UUID.randomUUID().toString();
    }
}
