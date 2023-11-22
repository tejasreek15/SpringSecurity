package com.springsecurity.controller;

import com.springsecurity.model.UserInfo;
import com.springsecurity.service.UserInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserInfoController {


    private final UserInfoService userInfoService;


    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @PostMapping("/saveUserInfo")
    public ResponseEntity<String> saveUserInfo(@RequestBody UserInfo userInfo){
        return new ResponseEntity<>(userInfoService.saveUserInfo(userInfo), HttpStatus.OK);
    }
}
