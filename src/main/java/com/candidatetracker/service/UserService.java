package com.candidatetracker.service;

import com.candidatetracker.entity.User;
import com.candidatetracker.user.CrmUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService
{
  User findByUserName(String userName);
  void save(CrmUser crmUser);
}
