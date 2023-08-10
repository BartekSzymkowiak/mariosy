package com.deloitte.ads.mariosy.controller;

import com.deloitte.ads.mariosy.mappers.UserMapper;
import com.deloitte.ads.mariosy.repository.MariosRepository;
import com.deloitte.ads.mariosy.repository.UserRepository;
import com.deloitte.ads.mariosy.service.UserService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class UserControllerTest {

    @Mock
    MariosRepository mariosRepository;

    @Mock
    UserRepository userRepository;

    @Spy
    UserMapper userMapper;

    @Spy
    UserService userService;

    @InjectMocks
    UserController userController;

}
