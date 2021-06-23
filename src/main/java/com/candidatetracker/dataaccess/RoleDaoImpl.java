package com.candidatetracker.dataaccess;

import com.candidatetracker.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@Repository
public class RoleDaoImpl implements RoleDao
{
  @Autowired
  // private SessionFactory sessionFactory;
  // replaced SessionFactory with EntityManager to use standard JPA API
  private EntityManager entityManager;

  @Override
  public Role findRoleByName(String theRoleName)
  {
    // retrieve/read from database using name
    TypedQuery<Role> theQuery = entityManager.createQuery("from Role where name=:roleName", Role.class);
    theQuery.setParameter("roleName", theRoleName);

    Role theRole = null;
    try
    {
      theRole = theQuery.getSingleResult();
    }
    catch (Exception ex)
    {
      theRole = null;
    }

    return theRole;
  }
}
