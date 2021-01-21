package com.uma.mestrado.persuasive_profiles.models;


import org.hibernate.validator.constraints.NotEmpty;


public class POSTRegisterRequest
{

  @NotEmpty(message = "Nome em falta")
  private String name;
  @NotEmpty(message = "Username em falta")
  private String username;
  @NotEmpty(message = "Password em falta")
  private String pwd;
  @NotEmpty(message = "Idade incorrecta ( > 10 anos )")
  private String age;
  private int countryId;
  private int genderId;
  @NotEmpty(message = "Peso inválido ( > 50kg )")
  private String weight;
  @NotEmpty(message = "Altura inválida ( > 100cm )")
  private String height;
  private boolean hadNutricionalConsult;

  public POSTRegisterRequest()
  {
    //
  }

  public POSTRegisterRequest(String aName, String aUsername, String aPwd, String aAge, int aCountryId, int aGenderId, String aWeight, String aHeight,
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


  public String getUsername()
  {
    return username;
  }


  public void setUsername(String aUsername)
  {
    username = aUsername;
  }


  public String getPwd()
  {
    return pwd;
  }


  public void setPwd(String aPwd)
  {
    pwd = aPwd;
  }


  public String getAge()
  {
    return age;
  }


  public void setAge(String aAge)
  {
    age = aAge;
  }


  public int getCountryId()
  {
    return countryId;
  }


  public void setCountryId(int aCountryId)
  {
    countryId = aCountryId;
  }


  public int getGenderId()
  {
    return genderId;
  }


  public void setGenderId(int aGenderId)
  {
    genderId = aGenderId;
  }


  public String getWeight()
  {
    return weight;
  }


  public void setWeight(String aWeight)
  {
    weight = aWeight;
  }


  public String getHeight()
  {
    return height;
  }


  public void setHeight(String aHeight)
  {
    height = aHeight;
  }


  public boolean isHadNutricionalConsult()
  {
    return hadNutricionalConsult;
  }


  public void setHadNutricionalConsult(boolean aHadNutricionalConsult)
  {
    hadNutricionalConsult = aHadNutricionalConsult;
  }

}
