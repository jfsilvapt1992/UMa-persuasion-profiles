package com.uma.mestrado.persuasive_profiles.enums;


public enum AlgorithmEnum
{
  RANDOM,
  EXPLICIT,
  IMPLICIT;

  public static AlgorithmEnum get(int aCounter)
  {
    AlgorithmEnum response = null;
    if (aCounter == 0)
    {
      response = AlgorithmEnum.EXPLICIT;
    }
    else if (aCounter <= 5)
    {
      response = AlgorithmEnum.RANDOM;
    }
    else
    {
      response = AlgorithmEnum.IMPLICIT;
    }
    return response;
  }
}
