package com.springboot.tennisCourtManagementApp.service.user;

import com.springboot.tennisCourtManagementApp.dao.MyUserRepository;
import com.springboot.tennisCourtManagementApp.entity.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private MyUserRepository myUserRepository;

    @Autowired
    public UserService(MyUserRepository myUserRepository) {
        this.myUserRepository = myUserRepository;
    }


    public void disableUsers(){
        List<MyUser> users = myUserRepository.findAll();
        for(var user : users){
            if(!user.getUsername().equalsIgnoreCase("admin")){
                if(user.isEnabled()){
                    user.setEnabled(false);
                    myUserRepository.save(user);
                }
            }
        }
    }
}
