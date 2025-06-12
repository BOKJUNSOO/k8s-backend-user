package com.welab.k8s_backend_user.domain.event;


import com.welab.k8s_backend_user.domain.SiteUser;

import java.time.LocalDateTime;

public class SiteUserInfoEvent {
    public static final String Topic ="userinfo";

    private String action;

    private String userId;

    private String phoneNumber;

    private LocalDateTime localDateTime;

    // 회원가입시 토픽에 발행할 데이터
    public static SiteUserInfoEvent fromEntity(String action, SiteUser siteUser) {
        SiteUserInfoEvent event = new SiteUserInfoEvent();

        event.action = action;
        event.userId = siteUser.getUserId();
        event.phoneNumber = siteUser.getPhoneNumber();
        event.localDateTime = LocalDateTime.now();

        return event;
    }
}
