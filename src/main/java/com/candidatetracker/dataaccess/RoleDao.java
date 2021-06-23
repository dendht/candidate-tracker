package com.candidatetracker.dataaccess;

import com.candidatetracker.entity.Role;

public interface RoleDao
{
  public Role findRoleByName(String theRoleName);
}
