package controller;

import dao.MsgBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.BusinessService;

@RestController
@RequestMapping("/test")
public class Controller {

    @Autowired
    private BusinessService businessService;

    @PostMapping("/sendCommonMqMsg")
    public void sendCommonMqMsg(@RequestBody MsgBody msgBody){
        businessService.sendCommonMqMsg(msgBody);
    }

    @PostMapping("/sendDelayMqMsg")
    public void sendDelayMqMsg(@RequestBody MsgBody msgBody){
        businessService.sendDelayMqMsg(msgBody);
    }


}
