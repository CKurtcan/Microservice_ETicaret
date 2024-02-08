package com.CK.service;

import com.CK.dto.request.LoginRequestDto;
import com.CK.dto.request.RegisterRequestDto;
import com.CK.dto.request.UserSaveRequestDto;
import com.CK.entity.Auth;
import com.CK.manager.UserProfileManager;
import com.CK.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
    private final UserProfileManager manager;

    public Boolean register(RegisterRequestDto dto){
        Auth auth = Auth.builder()
                .userName(dto.getUserName())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .createAt(System.currentTimeMillis())
                .updateAt(System.currentTimeMillis())
                .isActive(true)
                .build();
        authRepository.save(auth);
        /*
        Burada kullanıcıyı authDB ye kaydettikten sonra UserService e Profil oluşturması
        için bilgileri iletmemiz gereklidir
         */
        UserSaveRequestDto userSaveRequestDto = UserSaveRequestDto.builder()
                .authId(auth.getId())
                .userName(dto.getUserName())
                .email(dto.getEmail())
                .build();
        manager.save(userSaveRequestDto);
        return true;
    }

    public Optional<Auth> doLogin(LoginRequestDto dto){
        Optional<Auth> auth = authRepository.findOptionalByUserNameAndPassword(dto.getUserName(), dto.getPassword());
        return auth;
    }
}
