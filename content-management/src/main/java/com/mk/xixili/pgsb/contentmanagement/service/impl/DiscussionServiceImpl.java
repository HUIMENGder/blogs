package com.mk.xixili.pgsb.contentmanagement.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mk.xixili.pgsb.contentmanagement.entity.Discussion;
import com.mk.xixili.pgsb.contentmanagement.mapper.DiscussionMapper;
import com.mk.xixili.pgsb.contentmanagement.service.DiscussionService;
import org.springframework.stereotype.Service;

@Service
public class DiscussionServiceImpl extends ServiceImpl<DiscussionMapper, Discussion> implements DiscussionService {
}
