package com.candidatetracker.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "name")
  @Enumerated(EnumType.STRING) // Persisting Enum of RoleType
  private RoleType name;

  public Role()
  {
  }

  public Role(String name)
  {
    this.name = RoleType.valueOf(name);
  }

  public Role(RoleType name)
  {
    this.name = name;
  }

  public Long getId()
  {
    return id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  public String getName()
  {
    return name.name();
  }

  public void setName(String name)
  {
    this.name = RoleType.valueOf(name);
  }

  @Override
  public String toString()
  {
    return "Role{" +
        "id=" + id +
        ", name='" + name + '\'' +
        '}';
  }

  public enum RoleType
  {
    ROLE_EMPLOYEE,
    ROLE_MANAGER,
    ROLE_ADMIN;

    public String shortName()
    {
      return this.name().replace("ROLE_", "");
    }
  }
}
