package com.momoc.multi.chat.room.core.controller;

import com.momoc.multi.chat.room.common.exception.BaseException;
import com.momoc.multi.chat.room.common.model.channel.ChannelHistoryMessagePojo;
import com.momoc.multi.chat.room.common.model.channel.ChannelInfoPojo;
import com.momoc.multi.chat.room.common.utils.Pager;
import com.momoc.multi.chat.room.common.vo.ApiResultVO;
import com.momoc.multi.chat.room.common.vo.AppUserInfo;
import com.momoc.multi.chat.room.common.vo.UserChannelInfoVO;
import com.momoc.multi.chat.room.core.service.IChannelService;
import com.momoc.multi.chat.room.core.session.ChannelManagerContext;
import com.momoc.multi.chat.room.core.session.vo.UserSessionInfo;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author momoc
 * @version 1.0
 * @className ChannelController
 * @description
 * @date 2023/8/1 18:23
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/channel")
public class ChannelController extends BaseController{


    private final IChannelService channelService;


    /**
     * 获取当前频道的所有成员
     * @param channelId
     * @return
     */
    @RequestMapping("/getChannelAllUser")
    public ApiResultVO getChannelAllUser(Long channelId){
        List<UserChannelInfoVO> channelAllUser = channelService.getChannelAllUser(channelId);
        Collection<UserSessionInfo> channelOnlineMember = ChannelManagerContext.getChannelOnlineMember(channelId);
        Map<Long, AppUserInfo> channelOnlineMap = channelOnlineMember.stream().map(UserSessionInfo::getAppUserInfo)
                .collect(Collectors.toMap(AppUserInfo::getId, k -> k));
        for (UserChannelInfoVO userChannelInfoVO : channelAllUser) {
            AppUserInfo appUserInfo = channelOnlineMap.get(userChannelInfoVO.getUserid());
            userChannelInfoVO.setOnline(appUserInfo != null);
        }
        return ApiResultVO.success(channelAllUser);
    }

    @RequestMapping("/getChannelHistoryMessage")
    public ApiResultVO getChannelHistoryMessage(Long channelId, Long messageMaxId) throws BaseException, Exception {
        Pager<ChannelHistoryMessagePojo> myPage = getMyPage();
        myPage.setSearchCount(false);
        myPage = channelService.getCHannelHistoryMessageByPager(channelId,  myPage,messageMaxId);
        return ApiResultVO.success(myPage);
    }

    @RequestMapping("/getChannelInfo")
    public ApiResultVO getChannelInfo(Long channelId){
        ChannelInfoPojo byId = channelService.getById(channelId);
        return ApiResultVO.success(byId);
    }
}
