package com.uma.mestrado.persuasive_profiles.enums;


public enum InfluencePrincipleEnum
{
  AUTHORITY,
  CONSENSUS,
  SCARCITY,
  LIKING;

  public static InfluencePrincipleEnum get(int aIndex)
  {
    InfluencePrincipleEnum[] array = InfluencePrincipleEnum.values();

    return array[aIndex];
  }
}
