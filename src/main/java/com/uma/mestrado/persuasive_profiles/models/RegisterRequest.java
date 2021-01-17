package com.uma.mestrado.persuasive_profiles.models;


public class RegisterRequest
{

  private String name, username, password;
  private int age, countryId, genderId, weight, height;
  private boolean hadNutricionalConsult;


  public RegisterRequest(String aName, String aUsername, String aPassword, int aAge, int aCountryId, int aGenderId, int aWeight, int aHeight,
  boolean aHadNutricionalConsult)
  {
    super();
    name = aName;
    username = aUsername;
    password = aPassword;
    age = aAge;
    countryId = aCountryId;
    genderId = aGenderId;
    weight = aWeight;
    height = aHeight;
    hadNutricionalConsult = aHadNutricionalConsult;
  }

  public String getName()
  {
    return name;
  }

  public String getUsername()
  {
    return username;
  }

  public String getPwd()
  {
    return password;
  }

  public int getAge()
  {
    return age;
  }

  public int getCountryId()
  {
    return countryId;
  }

  public int getGenderId()
  {
    return genderId;
  }

  public int getWeight()
  {
    return weight;
  }

  public int getHeight()
  {
    return height;
  }

  public boolean isHadNutricionalConsult()
  {
    return hadNutricionalConsult;
  }


}
