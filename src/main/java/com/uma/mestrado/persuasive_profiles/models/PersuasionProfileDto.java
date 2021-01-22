package com.uma.mestrado.persuasive_profiles.models;


public class PersuasionProfileDto
{

  private int id, totalInfluenced, total;
  private String name;

  public PersuasionProfileDto()
  {
    super();
    totalInfluenced = 0;
    total = 1;// has to be 1 for cases where haven't been any historic of an principle
  }

  public PersuasionProfileDto(int aId, String aName)
  {
    super();
    id = aId;
    name = aName;
  }


  public PersuasionProfileDto(int aId, int aTotalInfluenced, int aTotal, String aName)
  {
    super();
    id = aId;
    totalInfluenced = aTotalInfluenced;
    total = aTotal;
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


  public int getTotalInfluenced()
  {
    return totalInfluenced;
  }


  public void setTotalInfluenced(int aTotalInfluenced)
  {
    totalInfluenced = aTotalInfluenced;
  }


  public int getTotal()
  {
    return total;
  }


  public void setTotal(int aTotal)
  {
    total = aTotal;
  }


  public void setId(int aId)
  {
    id = aId;
  }


  public void setName(String aName)
  {
    name = aName;
  }

  public void increaseTotalInflueced()
  {
    totalInfluenced = totalInfluenced + 1;
  }

  public void increaseTotal(int aValue)
  {
    total = total + aValue;
  }
}
