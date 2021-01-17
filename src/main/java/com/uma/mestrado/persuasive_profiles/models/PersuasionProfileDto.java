package com.uma.mestrado.persuasive_profiles.models;


public class PersuasionProfileDto
{

  private int id;
  private String name;

  public PersuasionProfileDto(int aId, String aName)
  {
    super();
    id = aId;
    name = aName;
  }

  public int getId()
  {
    return id;
  }

  public String getName()
  {
    return name;
  }

}
