package com.uma.mestrado.persuasive_profiles.models;


public class LoginRequest
{

  private String username, password;

  public LoginRequest(String aUsername, String aPassword)
  {
    super();
    username = aUsername;
    password = aPassword;
  }

  public String getUsername()
  {
    return username;
  }

  public String getPassword()
  {
    return password;
  }
}
