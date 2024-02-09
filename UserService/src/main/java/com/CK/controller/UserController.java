package com.CK.controller;

import com.CK.domain.User;
import com.CK.dto.request.UserSaveRequestDto;
import com.CK.dto.request.UserUpdateRequestDto;
import com.CK.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.ref.PhantomReference;
import static com.CK.constants.RestApiUrls.*;

/**
 * http://localhost:9094/dev/v1/user
 */
@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
@Slf4j // loglama işlem için kullanılıyor
public class UserController {
    private final UserService userService;

//    @Value("${userservice.deger2}")
//    private String deger2;


    @PostMapping(ADD)
    public ResponseEntity<Void> save(@RequestBody UserSaveRequestDto dto){
        userService.save(dto);
        return ResponseEntity.ok().build();
    }
    @PutMapping(UPDATE)
    public ResponseEntity<Void> update(UserUpdateRequestDto dto){
        userService.update(dto);
        return ResponseEntity.ok().build();

    }
}
