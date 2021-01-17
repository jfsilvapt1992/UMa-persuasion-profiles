package com.uma.mestrado.persuasive_profiles.models;


public class PersonDto
{

  private String name;
  private int age, weight, height;


  public PersonDto(String aName, int aAge, int aWeight, int aHeight)
  {
    super();
    name = aName;
    age = aAge;
    weight = aWeight;
    height = aHeight;
  }

  public String getName()
  {
    return name;
  }

  public int getAge()
  {
    return age;
  }

  public int getWeight()
  {
    return weight;
  }

  public int getHeight()
  {
    return height;
  }
}
