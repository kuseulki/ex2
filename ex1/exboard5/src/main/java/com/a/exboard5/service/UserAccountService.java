package com.a.exboard5.service;

import com.a.exboard5.domain.UserAccount;
import com.a.exboard5.dto.UserAccountDto;
import com.a.exboard5.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;

    @Transactional(readOnly = true)
    public Optional<UserAccountDto> searchUser(String username) {
        return userAccountRepository.findById(username)
                .map(UserAccountDto::from);
    }

    public UserAccountDto saveUser(String username, String userPassword, String email, String nickname, String memo){
        return UserAccountDto.from(
                userAccountRepository.save(UserAccount.of(username, userPassword, email, nickname, memo, username))
        );
    }
}
