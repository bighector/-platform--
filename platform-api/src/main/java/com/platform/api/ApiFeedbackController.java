package com.platform.api;

import com.platform.annotation.LoginUser;
import com.platform.entity.FeedbackVo;
import com.platform.entity.UserVo;
import com.platform.service.ApiFeedbackService;
import com.platform.util.ApiBaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 作者: @author Harmon <br>
 * 时间: 2017-08-11 08:32<br>
 * 描述: ApiFeedbackController <br>
 */
@RestController
@RequestMapping("/api/feedback")
public class ApiFeedbackController extends ApiBaseAction {
    @Autowired
    private ApiFeedbackService feedbackService;

    /**
     * 添加反馈
     */
    @RequestMapping("save")
    public Object save(@LoginUser UserVo loginUser, @RequestBody FeedbackVo feedbackVo) {
        feedbackVo.setUser_id(loginUser.getUserId());
        feedbackVo.setUser_name(loginUser.getNickname());
        feedbackVo.setMsg_time(System.currentTimeMillis() / 1000);
        feedbackService.save(feedbackVo);
        return toResponsSuccess("感谢你的反馈");
    }
}