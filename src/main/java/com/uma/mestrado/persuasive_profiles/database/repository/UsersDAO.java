package com.uma.mestrado.persuasive_profiles.database.repository;


import com.uma.mestrado.persuasive_profiles.database.entities.Users;


public interface UsersDAO extends ICommonRepository<Users, Integer>
{

  Users findOneByUsername(String aUsername);
}
