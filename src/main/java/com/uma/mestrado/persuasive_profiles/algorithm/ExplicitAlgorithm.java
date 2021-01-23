package com.uma.mestrado.persuasive_profiles.algorithm;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uma.mestrado.persuasive_profiles.database.DatabaseManager;
import com.uma.mestrado.persuasive_profiles.enums.IMCEnum;
import com.uma.mestrado.persuasive_profiles.enums.InfluencePrincipleEnum;
import com.uma.mestrado.persuasive_profiles.exceptions.DatabaseException;
import com.uma.mestrado.persuasive_profiles.models.PersonDto;
import com.uma.mestrado.persuasive_profiles.models.PersuasionProfileDto;


// This one is using user data (age, weight, had or not nutricional consult,etc)
public class ExplicitAlgorithm implements PersuasionProfileAlgorithm
{

  Logger logger = LoggerFactory.getLogger(ExplicitAlgorithm.class);

  private DatabaseManager dbManager;
  private int personId;

  public ExplicitAlgorithm(DatabaseManager aDbManager, int aPersonId)
  {
    dbManager = aDbManager;
    personId = aPersonId;
  }

  @Override
  public PersuasionProfileDto execute() throws Exception
  {
    PersuasionProfileDto response = null;

    logger.info("Executing explit algorithm");

    PersonDto person = dbManager.selectPerson(personId);
    logger.info("############## DECIDING INFLUENCE PRINCIPLE TO APPLY ##################");
    IMCEnum imcEnum = IMCEnum.get(person.getWeight(), person.getHeight());
    switch (imcEnum)
    {
      case HIGH:
        response = getPersuasionProfile(InfluencePrincipleEnum.AUTHORITY.name());
        break;
      case LOW:
        response = getPersuasionProfile(InfluencePrincipleEnum.CONSENSUS.name());
        break;
      case NORMAL:
        response = getPersuasionProfile(InfluencePrincipleEnum.RECIPROCITY.name());
        break;
    }
    logger.info("Influence principle choosed: " + response.getName());
    logger.info("#######################################################################");
    logger.info("Executed explit algorithm succesffully!!!");

    return response;
  }

  private PersuasionProfileDto getPersuasionProfile(String aInfluencePrincipleName) throws DatabaseException
  {
    logger.info("Getting influence principle information");
    return dbManager.selectInfluencePrinciple(aInfluencePrincipleName);
  }
}
