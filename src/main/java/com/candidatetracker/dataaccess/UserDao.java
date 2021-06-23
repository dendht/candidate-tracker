package com.candidatetracker.dataaccess;

import com.candidatetracker.entity.User;

public interface UserDao
{
  User findByUserName(String userName);
  void save(User user);
}
