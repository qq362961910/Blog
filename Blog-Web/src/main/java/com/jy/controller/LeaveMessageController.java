package com.jy.controller;

import com.jy.controller.interceptor.anno.RequiredLogin;
import com.jy.controller.util.AppContextUtil;
import com.jy.entity.User;
import com.jy.service.LeaveMessageService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@RequestMapping("leave_message")
public class LeaveMessageController extends BaseController {

    private LeaveMessageService leaveMessageService;

    @RequiredLogin
    @RequestMapping(method = RequestMethod.POST)
    public Object saveLeaveMessage(@RequestBody Map<String, Object> param) {
        Number userId = (Number)param.get("userId");
        String content = (String)param.get("content");
        User currentUser = AppContextUtil.getCurrentUser();
        leaveMessageService.save(currentUser.getId(), userId.longValue(), null, content);
        return success();
    }



}
