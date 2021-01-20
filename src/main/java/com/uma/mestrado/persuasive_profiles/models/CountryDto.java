package com.uma.mestrado.persuasive_profiles.models;


public class CountryDto
{

  private int id;
  private String name;

  public CountryDto()
  {
    super();
  }

  public CountryDto(int aId, String aName)
  {
    super();
    id = aId;
    name = aName;
  }


  public int getId()
  {
    return id;
  }


  public void setId(int aId)
  {
    id = aId;
  }


  public String getName()
  {
    return name;
  }


  public void setName(String aName)
  {
    name = aName;
  }


}
