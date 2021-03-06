package com.jy.blog.controller;

import com.jy.blog.blog.common.constants.ServiceErrorCode;
import com.jy.blog.controller.interceptor.anno.RequiredLogin;
import com.jy.blog.controller.util.AppContextUtil;
import com.jy.blog.entity.LeaveMessage;
import com.jy.blog.response.entity.PageableWrapper;
import com.jy.blog.response.service.LeaveMessageWrapperService;
import com.jy.blog.service.BaseService;
import com.jy.blog.service.LeaveMessageService;
import com.jy.blog.service.UserService;
import com.jy.blog.blog.dao.LeaveMessageDao;
import com.jy.blog.entity.User;
import com.jy.blog.response.entity.LeaveMessageWrapper;
import org.hibernate.criterion.Order;
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
    private UserService userService;
    @Autowired
    private LeaveMessageWrapperService leaveMessageWrapperService;

    @RequiredLogin
    @RequestMapping(method = RequestMethod.POST)
    public Object saveLeaveMessage(@RequestBody Map<String, Object> param) {
        String username = (String) param.get("username");
        String content = (String) param.get("content");
        User currentUser = AppContextUtil.getCurrentUser();
        User toUser = userService.findUserByUsername(username);
        if (toUser == null) {
            return fail(ServiceErrorCode.USER_NOT_FOUND);
        }
        leaveMessageService.save(currentUser.getId(), toUser.getId(), null, content);
        return success();
    }


    /**
     * 被顶起来的留言
     * */
    @RequestMapping(value = "query_user_most_like_count_leave_message", method = RequestMethod.GET)
    public Object queryUserMostLikeCountLeaveMessage(@RequestParam(value = "username", required = false) String username) {
        if (username == null) {
            return fail(ServiceErrorCode.PARAM_MISSING);
        }
        User user = userService.findUserByUsername(username);
        if (user == null) {
            return fail(ServiceErrorCode.USER_NOT_FOUND);
        }
        LeaveMessageDao.LeaveMessageParam param = new LeaveMessageDao.LeaveMessageParam();
        param.setOwnerId(user.getId());
        param.getOrderList().add(Order.desc("likeCount"));
        param.setCurrentPage(1);
        param.setPageSize(3);
        List<LeaveMessage> leaveMessageList = leaveMessageService.queryListByParam(param);
        List<LeaveMessageWrapper> leaveMessageWrapperList = new ArrayList<>(leaveMessageList.size());
        Map<String, Object> data = new HashMap<>();
        leaveMessageList.forEach(leaveMessage -> {
            leaveMessageWrapperList.add(leaveMessageWrapperService.buildLeaveMessageWrapper(leaveMessage));
        });
        data.put("leaveMessages", leaveMessageWrapperList);
        return success(data);
    }

    /**
     * 被顶起来的留言
     * */
    @RequestMapping(value = "rest/query_user_most_like_count_leave_message", method = RequestMethod.POST)
    public Object restQueryUserMostLikeCountLeaveMessage(@RequestBody Map<String, Object> requestBody) {
        return queryUserMostLikeCountLeaveMessage((String) requestBody.get("username"));
    }

    @RequestMapping(value = "rest/query_user_leave_message", method = RequestMethod.POST)
    public Object restQueryUserLeaveMessage(@RequestBody Map<String, Object> requestBody) {
        return queryUserLeaveMessage(requestBody);
    }

    @RequestMapping(value = "query_user_leave_message", method = RequestMethod.GET)
    public Object queryUserLeaveMessage(@RequestParam Map<String, Object> requestBody) {
        String username = (String) requestBody.get("username");
        String orderType = (String) requestBody.get("orderType");
        if (username == null) {
            return fail(ServiceErrorCode.PARAM_MISSING);
        }
        User user = userService.findUserByUsername(username);
        if (user == null) {
            return fail(ServiceErrorCode.USER_NOT_FOUND);
        }
        Integer pageSize = (Integer) requestBody.get(pageSizeKey);
        Integer currentPage = (Integer) requestBody.get(currentPageKey);
        if (pageSize == null) {
            pageSize = pageSizeDefault;
        } else {
            if (pageSize > pageSizeDefault) {
                pageSize = pageSizeDefault;
            }
        }
        if (currentPage == null) {
            currentPage = currentPageDefault;
        }
        LeaveMessageDao.LeaveMessageParam param = new LeaveMessageDao.LeaveMessageParam();
        param.setOwnerId(user.getId());
        param.setCurrentPage(currentPage);
        param.setPageSize(pageSize);
        if (orderType == "2") {
            param.getOrderList().add(Order.asc("createTime"));
        }
        else if (orderType == "3") {
            param.getOrderList().add(Order.desc("likeCount"));
        }
        else {
            param.getOrderList().add(Order.desc("createTime"));
        }
        BaseService.Pageable<LeaveMessage> leaveMessagePageable = leaveMessageService.queryPageableListByParam(param);
        return success(createPageableMap(leaveMessageWrapperService.buildPageableWrapper(leaveMessagePageable)));
    }


}
