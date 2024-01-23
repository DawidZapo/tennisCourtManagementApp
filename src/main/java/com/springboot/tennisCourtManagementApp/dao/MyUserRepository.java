package com.springboot.tennisCourtManagementApp.dao;

import com.springboot.tennisCourtManagementApp.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyUserRepository extends JpaRepository<MyUser, String> {
}
