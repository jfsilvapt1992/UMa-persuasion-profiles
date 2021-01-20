package com.uma.mestrado.persuasive_profiles.models;


public class GenderDto
{

  private int id;
  private String type;

  public GenderDto()
  {
    super();
  }

  public GenderDto(int aId, String aType)
  {
    super();
    id = aId;
    type = aType;
  }


  public int getId()
  {
    return id;
  }


  public void setId(int aId)
  {
    id = aId;
  }


  public String getType()
  {
    return type;
  }



}
