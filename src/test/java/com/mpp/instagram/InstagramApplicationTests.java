//package com.mpp.instagram;
//
//import com.datastax.driver.core.LocalDate;
//import com.mpp.instagram.user.controller.UserController;
//import com.mpp.instagram.user.entity.UserEntity;
//import com.mpp.instagram.user.service.UserService;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import java.util.Date;
//import java.util.UUID;
//
//import static org.mockito.Mockito.*;
//
//@RunWith(MockitoJUnitRunner.class)
//public class UserControllerTest {
//    @InjectMocks
//    UserController userController;
//    @Mock
//    UserService userService;
//    @Before
//    public void setUp()
//    {
//        MockitoAnnotations.initMocks(this);
//    }
//    @Test
//    public void SaveToken()
//    {
//        Date date = new Date();
//        UUID uuid=UUID.randomUUID();
//        //UserEntity userEntity1=new UserEntity("Dani","AdnanShehzad","dani@mum.edu","2323");
//        UserEntity userEntity=new UserEntity("Adnan","AdnanShehzad","ashehzad@mum.edu","1234567");
//        //userEntity.setToken(uuid);
//        // userEntity.setToken_timestamp(LocalDate.fromMillisSinceEpoch(date.getTime()));
//        when(userService.saveToken(userEntity)).thenReturn(userEntity);
//        UserEntity returnvalue=userController.saveToken(userEntity,uuid);
//        Assert.assertNotNull(returnvalue);
//        verify(userService,times(1)).saveToken(userEntity);
//        Assert.assertEquals("Adnan",returnvalue.getUsername());
//    }
//
//    @Test
//    public void getUserPosts() {
//
//    }
//
//}
