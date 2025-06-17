package com.welab.k8s_backend_user.controller.open;

import com.welab.k8s_backend_user.common.dto.ApiResponseDto;
import com.welab.k8s_backend_user.domain.dto.SiteUserLoginDto;
import com.welab.k8s_backend_user.domain.dto.SiteUserRefreshDto;
import com.welab.k8s_backend_user.domain.dto.SiteUserRegisterDto;
import com.welab.k8s_backend_user.secret.jwt.dto.TokenDto;
import com.welab.k8s_backend_user.service.SiteUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/api/user/v1/auth", produces = MediaType.APPLICATION_JSON_VALUE)@RequiredArgsConstructor
public class UserAuthController {

    private final SiteUserService siteUserService;

    @PostMapping(value = "/register")
    public ApiResponseDto<String> register(@RequestBody @Valid SiteUserRegisterDto registerDto) {
        siteUserService.registerUser(registerDto);
        return ApiResponseDto.defaultOk();
    }

    @PostMapping(value = "/login")
    public ApiResponseDto<TokenDto.AccessRefreshToken> login(@RequestBody @Valid SiteUserLoginDto loginDto) {
        TokenDto.AccessRefreshToken token = siteUserService.login(loginDto);
        return ApiResponseDto.createOk(token);
    }

    @PostMapping(value = "/refresh")
    public ApiResponseDto<TokenDto.AccessToken> refresh(@RequestBody @Valid SiteUserRefreshDto refreshDto) {
        TokenDto.AccessToken token = siteUserService.refresh(refreshDto);
        return ApiResponseDto.createOk(token);
    }
    
    // 토큰 없이 진입하는 엔드포인트에 테스트 코드 추가
    @GetMapping(value = "/test")
    public ApiResponseDto<String> test() {
        return ApiResponseDto.createOk("버전 1입니다.");
    }

    // v2 버전 테스트
    @GetMapping(value = "/test2")
    public ApiResponseDto<String> test2() {
        return ApiResponseDto.createOk("버전 2 입니다.");
    }
}