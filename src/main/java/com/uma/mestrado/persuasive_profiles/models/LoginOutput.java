package com.uma.mestrado.persuasive_profiles.models;


public class LoginOutput
{

  private String username;
  private int person_id;

  public LoginOutput(String aUsername, int aPerson_id)
  {
    super();
    username = aUsername;
    person_id = aPerson_id;
  }

  public String getUsername()
  {
    return username;
  }

  public int getPerson_id()
  {
    return person_id;
  }


}
