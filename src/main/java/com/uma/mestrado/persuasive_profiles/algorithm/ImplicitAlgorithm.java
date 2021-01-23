package com.uma.mestrado.persuasive_profiles.algorithm;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uma.mestrado.persuasive_profiles.database.DatabaseManager;
import com.uma.mestrado.persuasive_profiles.enums.InfluencePrincipleEnum;
import com.uma.mestrado.persuasive_profiles.exceptions.DatabaseException;
import com.uma.mestrado.persuasive_profiles.models.HistoricDto;
import com.uma.mestrado.persuasive_profiles.models.PersuasionProfileDto;


// This one is using historic of user actions
public class ImplicitAlgorithm implements PersuasionProfileAlgorithm
{

  Logger logger = LoggerFactory.getLogger(ImplicitAlgorithm.class);

  private DatabaseManager dbManager;
  private int personId;

  public ImplicitAlgorithm(DatabaseManager aDatabaseManager, int aPersonId)
  {
    personId = aPersonId;
    dbManager = aDatabaseManager;
  }

  @Override
  public PersuasionProfileDto execute() throws Exception
  {
    PersuasionProfileDto response = null;
    try
    {
      logger.info("Executing implicit algorithm");

      HistoricDto historicDto = dbManager.selectAllHistoric(personId);

      Map<PersuasionProfileDto, Double> ratios = new HashMap<PersuasionProfileDto, Double>();
      for (PersuasionProfileDto profile : historicDto.getPersuasions())
      {
        ratios.put(profile, getFormulaResult(profile, historicDto.getTotalHistoric()));
      }

      validateMissingPrinciple(ratios, historicDto.getTotalHistoric());

      response = selectHigherRatio(ratios);

      logger.info("Executed implicit algorithm succesffully!!!");
    }
    catch (Exception e)
    {
      logger.error("", e);
      throw e;
    }
    return response;
  }

  private PersuasionProfileDto selectHigherRatio(Map<PersuasionProfileDto, Double> aRatios)
  {
    PersuasionProfileDto selectedProfile = null;
    logger.info("############## DECIDING INFLUENCE PRINCIPLE TO APPLY ##################");

    double higherRatio = 0;
    for (Map.Entry<PersuasionProfileDto, Double> entry : aRatios.entrySet())
    {
      logger.info(entry.getKey().getName() + " -> " + entry.getValue());
      if (Double.compare(entry.getValue(), higherRatio) > 0)
      {
        higherRatio = entry.getValue();
        selectedProfile = entry.getKey();
      }
    }
    logger.info("Influence principle choosed: " + selectedProfile.getName());
    logger.info("#######################################################################");
    return selectedProfile;
  }

  private void validateMissingPrinciple(Map<PersuasionProfileDto, Double> aRatios, int aTotalHistoric) throws DatabaseException
  {
    for (String temp : InfluencePrincipleEnum.getAllNames())
    {
      if (!aRatios.entrySet().stream().anyMatch(t -> t.getKey().getName().equalsIgnoreCase(temp)))
      {
        PersuasionProfileDto currentPrinciple = dbManager.selectInfluencePrinciple(temp);
        currentPrinciple.setTotal(0);
        currentPrinciple.setTotalInfluenced(0);
        aRatios.put(currentPrinciple, getFormulaResult(currentPrinciple, aTotalHistoric));
      }
    }
  }

  private Double getFormulaResult(PersuasionProfileDto aPersuasionProfileDto, int aTotalHistoric)
  {
    logger.info("Applying formula for persuasion : " + aPersuasionProfileDto.getName());
    logger.info("######## " + aPersuasionProfileDto.getName() + " | was influenced: " + aPersuasionProfileDto.getTotalInfluenced() + " | total of historic: " +
    aPersuasionProfileDto.getTotal());

    // number of influence times / total of historic times
    double influenceRatio = calculateRatio(aPersuasionProfileDto.getTotalInfluenced(), aPersuasionProfileDto.getTotal());

    // influence ratio * random number
    double totalRatio = getRandomNumber() * (calculateRatio(influenceRatio, new Double(aTotalHistoric)) + 1);

    totalRatio = applyPriority(aPersuasionProfileDto, totalRatio);

    logger.info("######## " + aPersuasionProfileDto.getName() + " | formula result: " + totalRatio);

    return totalRatio;
  }

  private double calculateRatio(double aNumber, double aTotalofNumber)
  {
    return aNumber / aTotalofNumber;
  }

  private double applyPriority(PersuasionProfileDto aPersuasionProfileDto, double aTotalRatio)
  {
    double value = 0;

    InfluencePrincipleEnum principleEnum = InfluencePrincipleEnum.getByName(aPersuasionProfileDto.getName());
    switch (principleEnum)
    {
      case AUTHORITY:
      case CONSENSUS:
      case RECIPROCITY:
      case SCARCITY:
        logger.info("######## " + aPersuasionProfileDto.getName() + " | priority applied: " + principleEnum.getPriority());
        value = principleEnum.getPriority() * aTotalRatio;
        break;
    }

    return value;
  }


  private double getRandomNumber()
  {
    Random rand = new Random();
    return (rand.nextInt(5) + 1) * 0.08;
  }
}
