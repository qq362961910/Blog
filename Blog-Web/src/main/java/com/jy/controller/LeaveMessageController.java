package com.jy.controller;

import com.jy.common.constants.ServiceErrorCode;
import com.jy.controller.interceptor.anno.RequiredLogin;
import com.jy.controller.util.AppContextUtil;
import com.jy.dao.LeaveMessageDao;
import com.jy.entity.LeaveMessage;
import com.jy.entity.User;
import com.jy.response.entity.LeaveMessageWrapper;
import com.jy.response.service.LeaveMessageWrapperService;
import com.jy.service.LeaveMessageService;
import com.jy.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("leave_message")
public class LeaveMessageController extends BaseController {

    @Autowired
    private LeaveMessageService leaveMessageService;
    @Autowired
    private LeaveMessageWrapperService leaveMessageWrapperService;

    @RequiredLogin
    @RequestMapping(method = RequestMethod.POST)
    public Object saveLeaveMessage(@RequestBody Map<String, Object> param) {
        Number userId = (Number) param.get("userId");
        String content = (String) param.get("content");
        User currentUser = AppContextUtil.getCurrentUser();
        leaveMessageService.save(currentUser.getId(), userId.longValue(), null, content);
        return success();
    }

    @RequestMapping(value = "query_user_leave_message", method = RequestMethod.GET)
    public Object queryUserLeaveMessage(@RequestParam Map<String, Object> requestBody) {
        String username = (String) requestBody.get("username");
        if (StringUtil.isEmpty(username)) {
            return fail(ServiceErrorCode.PARAM_MISSING);
        }
        Integer pageSize = (Integer) requestBody.get(pageSizeKey);
        Integer currentPage = (Integer) requestBody.get(currentPageKey);
        if (pageSize == null) {
            pageSize = pageSizeDefault;
        } else {
            if (pageSize > 30) {
                pageSize = pageSizeDefault;
            }
        }
        if (currentPage == null) {
            currentPage = currentPageDefault;
        }
        LeaveMessageDao.LeaveMessageParam param = new LeaveMessageDao.LeaveMessageParam();
        param.setCurrentPage(currentPage);
        param.setPageSize(pageSize);
        List<LeaveMessage> leaveMessageList = leaveMessageService.queryListByParam(param);
        List<LeaveMessageWrapper> leaveMessageWrapperList = new ArrayList<>(leaveMessageList.size());
        Map<String, Object> data = new HashMap<>();
        leaveMessageList.forEach(leaveMessage -> {
            leaveMessageWrapperList.add(leaveMessageWrapperService.buildLeaveMessageWrapper(leaveMessage));
        });
        data.put("leaveMessages", leaveMessageWrapperList);
        return success(data);
    }


}
