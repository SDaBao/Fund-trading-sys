package com.client;

import com.demo.SystemService;
import com.hundsun.jrescloud.rpc.annotation.CloudReference;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
public class SystemClientController {
    @CloudReference
    private SystemService systemService;

    @RequestMapping(value = "/manage", method = RequestMethod.GET)
    public String systemManage(@RequestParam("op_type")int op_type)
    {
        String str = null;
        if(op_type == 1){
            str = systemService.systemClear();
        }else if(op_type == 2){
            str = systemService.systemRollback();
        }
        return str;
    }

    @RequestMapping(value = "/setSystemDate", method = RequestMethod.GET)
    public String setSystemDate(@RequestParam("date")String date)
    {
        String str = systemService.setDate(date);
        return str;
    }

    @RequestMapping(value = "/getSystemDate", method = RequestMethod.GET)
    public String getSystemDate()
    {
        System.out.println("test");
        String str = systemService.getDate();
        return str;
    }
}
