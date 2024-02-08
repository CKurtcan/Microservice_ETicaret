package com.CK.service;

import com.CK.domain.User;
import com.CK.dto.request.UserSaveRequestDto;
import com.CK.dto.request.UserUpdateRequestDto;
import com.CK.exception.ErrorType;
import com.CK.exception.UserServiceException;
import com.CK.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void save(UserSaveRequestDto dto){
        userRepository.save(User.builder()
                        .userName(dto.getUserName())
                        .email(dto.getEmail())
                        .authId(dto.getAuthId())
                .build());
    }

    public void update(UserUpdateRequestDto dto){
        /**
         * dto icinde gelen user id bilgisi ile repository de parametre gecerek
         * bu if ye sahip bir kullanici olup olmadigi bilgisini cektik
         */
        Optional<User> user = userRepository.findById(dto.getId());
        if (user.isEmpty())
            throw new UserServiceException(ErrorType.ERROR_INVALID_USER_ID);
        User updateUser = user.get();
        updateUser.setName(dto.getName());
        updateUser.setAbout(dto.getAbout());
        updateUser.setPhoto(dto.getPhoto());
        updateUser.setPhone(dto.getPhone());
        userRepository.save(updateUser);

    }
}
