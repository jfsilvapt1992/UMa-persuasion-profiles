package com.uma.mestrado.persuasive_profiles.models;


public class POSTRegisterRequest
{

  private String name, username, pwd;
  private int age, countryId, genderId, weight, height;
  private boolean hadNutricionalConsult;

  public POSTRegisterRequest()
  {
    //
  }

  public POSTRegisterRequest(String aName, String aUsername, String aPwd, int aAge, int aCountryId, int aGenderId, int aWeight, int aHeight,
  boolean aHadNutricionalConsult)
  {
    super();
    name = aName;
    username = aUsername;
    pwd = aPwd;
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


  public void setName(String aName)
  {
    name = aName;
  }


  public void setUsername(String aUsername)
  {
    username = aUsername;
  }


  public void setPwd(String aPwd)
  {
    pwd = aPwd;
  }


  public void setAge(int aAge)
  {
    age = aAge;
  }


  public void setCountryId(int aCountryId)
  {
    countryId = aCountryId;
  }


  public void setGenderId(int aGenderId)
  {
    genderId = aGenderId;
  }


  public void setWeight(int aWeight)
  {
    weight = aWeight;
  }


  public void setHeight(int aHeight)
  {
    height = aHeight;
  }


  public void setHadNutricionalConsult(boolean aHadNutricionalConsult)
  {
    hadNutricionalConsult = aHadNutricionalConsult;
  }

  public String getUsername()
  {
    return username;
  }

  public String getPwd()
  {
    return pwd;
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
