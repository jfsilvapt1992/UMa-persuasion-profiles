package com.uma.mestrado.persuasive_profiles.models;


public class GETLoginRequest
{

  private String username, password;


  public GETLoginRequest()
  {
    super();
  }

  public GETLoginRequest(String aUsername, String aPassword)
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


  public void setUsername(String aUsername)
  {
    username = aUsername;
  }


  public void setPassword(String aPassword)
  {
    password = aPassword;
  }


}
