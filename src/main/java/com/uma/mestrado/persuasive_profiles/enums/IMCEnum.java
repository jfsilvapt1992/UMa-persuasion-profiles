package com.uma.mestrado.persuasive_profiles.enums;


public enum IMCEnum
{
  LOW,
  NORMAL,
  HIGH;

  private static final double MAX_LOWER_IMC = 18.5;
  private static final double MAX_NORMAL_IMC = 24.9;

  // IMC = Peso ÷ (Altura × Altura)
  public static IMCEnum get(int aWeight, int aHeight)
  {
    IMCEnum response = null;

    // cm to meters
    double height = aHeight * 0.01;

    double imc = calculateImc(new Double(aWeight), height);
    if (compare(imc, MAX_LOWER_IMC) < 0)
    {
      response = IMCEnum.LOW;
    }
    else if (compare(imc, MAX_NORMAL_IMC) < 0)
    {
      response = IMCEnum.NORMAL;
    }
    else
    {
      response = IMCEnum.HIGH;
    }

    return response;
  }

  private static double calculateImc(double aWeight, double aHeight)
  {
    return aWeight / (aHeight * aHeight);
  }

  private static int compare(double aValue1, double aValue2)
  {
    return Double.compare(aValue1, aValue2);
  }
}
