package com.uma.mestrado.persuasive_profiles.enums;


public enum InfluencePrincipleEnum
{
  AUTHORITY(1.5),
  CONSENSUS(1.4),
  SCARCITY(1.2),
  RECIPROCITY(1.3);

  private final double priority;

  private InfluencePrincipleEnum(double aPriority)
  {
    priority = aPriority;
  }

  public static InfluencePrincipleEnum get(int aIndex)
  {
    InfluencePrincipleEnum[] array = InfluencePrincipleEnum.values();

    return array[aIndex];
  }

  public static InfluencePrincipleEnum getByName(String aName)
  {
    InfluencePrincipleEnum response = null;
    for (InfluencePrincipleEnum current : InfluencePrincipleEnum.values())
    {
      if (current.name().equalsIgnoreCase(aName))
      {
        response = current;
      }
    }
    return response;
  }

  public double getPriority()
  {
    return priority;
  }
}
