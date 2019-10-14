package com.mpp.instagram.User;

import com.mpp.instagram.user.controller.UserController;
import com.mpp.instagram.user.service.UserService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
    private UserController userController;
    @Mock
    UserService userService;
    @Before
    public void setUp()
    {
    }

}
