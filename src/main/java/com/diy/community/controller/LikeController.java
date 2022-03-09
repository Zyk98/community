package com.diy.community.controller;

import com.diy.community.event.EventProducer;
import com.diy.community.service.LikeService;
import com.diy.community.util.CommunityConstant;
import com.diy.community.util.CommunityUtil;
import com.diy.community.util.HostHolder;
import com.diy.community.util.RedisKeyUtil;
import com.google.code.kaptcha.Producer;
import com.diy.community.entity.Event;
import com.diy.community.entity.User;
import org.apache.ibatis.ognl.ObjectElementsAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LikeController.java
 * @Description 点赞 Controller
 */

@Controller
public class LikeController implements CommunityConstant {

    @Autowired
    private LikeService likeService;

    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private EventProducer eventProducer;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(path = "/like", method = RequestMethod.POST)
    @ResponseBody
    public String like(int entityType, int entityId, int entityUserId, int postId) {
        User user = hostHolder.getUser();
        //点赞
        likeService.like(user.getId(), entityType, entityId, entityUserId);
        //点赞数量
        long likeCount = likeService.findEntityLikeCount(entityType, entityId);
        //点赞状态
        int likeStatus = likeService.findEntityLikeStatus(entityType, entityId, user.getId());
        //返回的结果
        Map<String, Object> map = new HashMap<>();
        map.put("likeCount", likeCount);
        map.put("likeStatus", likeStatus);

        // 触发点赞事件,仅点赞需要通知
        if (likeStatus == 1) {
            Event event = new Event()
                    .setTopic(TOPIC_LIKE)
                    .setUserId(hostHolder.getUser().getId())
                    .setEntityType(entityType)
                    .setEntityId(entityId)
                    .setEntityUserId(entityUserId)
                    .setData("postId", postId);
            eventProducer.fireEvent(event);

            if (entityType == ENTITY_TYPE_POST) {
                // 计算帖子分数
                String redisKey = RedisKeyUtil.getPostScoreKey();
                redisTemplate.opsForSet().add(redisKey, postId);
            }
        }
        return CommunityUtil.getJSONString(0, null, map);
    }
}
