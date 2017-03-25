package xyz.kots.remindme.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/reminder") //указываем, как попадать на наш контроллер
public class ReminderController {

    @RequestMapping (value = "/get", method = RequestMethod.GET) //говорим, как обратится к нашему методу(по какому URL)
    @ResponseBody // указываем, что хотим вернуть именно строку в виде тела ответа
    public String getReminder(ModelMap model){
        return "HELLO";
    }
}
