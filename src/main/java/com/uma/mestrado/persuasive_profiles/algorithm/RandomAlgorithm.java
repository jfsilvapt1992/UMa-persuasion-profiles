package com.uma.mestrado.persuasive_profiles.algorithm;


import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uma.mestrado.persuasive_profiles.database.DatabaseManager;
import com.uma.mestrado.persuasive_profiles.enums.InfluencePrincipleEnum;
import com.uma.mestrado.persuasive_profiles.models.PersuasionProfileDto;


public class RandomAlgorithm implements PersuasionProfileAlgorithm
{

  Logger logger = LoggerFactory.getLogger(RandomAlgorithm.class);

  private DatabaseManager dbManager;

  public RandomAlgorithm(DatabaseManager aDatabaseManager)
  {
    dbManager = aDatabaseManager;
  }

  @Override
  public PersuasionProfileDto execute() throws Exception
  {
    PersuasionProfileDto response = null;

    logger.info("Executing random algorithm");
    logger.info("############## DECIDING INFLUENCE PRINCIPLE TO APPLY ##################");

    InfluencePrincipleEnum persuasion = InfluencePrincipleEnum.get(getRandomNumber());

    response = dbManager.selectInfluencePrinciple(persuasion.name());

    logger.info("Influence principle choosed: " + response.getName());
    logger.info("#######################################################################");
    logger.info("Executed random algorithm succesffully!!!");

    return response;
  }

  private int getRandomNumber()
  {
    Random rand = new Random();
    return rand.nextInt(4);
  }
}
