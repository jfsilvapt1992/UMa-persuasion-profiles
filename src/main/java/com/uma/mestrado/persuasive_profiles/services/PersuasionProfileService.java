package com.uma.mestrado.persuasive_profiles.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.uma.mestrado.persuasive_profiles.algorithm.ExplicitAlgorithm;
import com.uma.mestrado.persuasive_profiles.algorithm.ImplicitAlgorithm;
import com.uma.mestrado.persuasive_profiles.algorithm.PersuasionProfileAlgorithm;
import com.uma.mestrado.persuasive_profiles.algorithm.RandomAlgorithm;
import com.uma.mestrado.persuasive_profiles.database.DatabaseManager;
import com.uma.mestrado.persuasive_profiles.enums.AlgorithmEnum;
import com.uma.mestrado.persuasive_profiles.models.PersuasionProfileDto;

import swaggerCodegen.models.BasicError;
import swaggerCodegen.models.GetInfluencePrincipleResponse;


@Component(value = "PersuasionProfileService")
public class PersuasionProfileService
{

  Logger logger = LoggerFactory.getLogger(PersuasionProfileService.class);

  @Autowired
  private DatabaseManager dbManager;

  public ResponseEntity<GetInfluencePrincipleResponse> getPersonInfluencePrinciple(Integer aPersonId)
  {
    try
    {
      logger.info("Starting defining influence principle to apply");

      PersuasionProfileAlgorithm algorithm = selectAlgorithm(aPersonId);

      PersuasionProfileDto selectedPersuasion = algorithm.execute();

      return new ResponseEntity<GetInfluencePrincipleResponse>(
      new GetInfluencePrincipleResponse().name(selectedPersuasion.getName()).id(selectedPersuasion.getId()), HttpStatus.OK);
    }
    catch (Exception e)
    {
      logger.error(this.getClass().getName() + " error : ", e.getMessage());
      return new ResponseEntity<GetInfluencePrincipleResponse>(
      new GetInfluencePrincipleResponse().error(new BasicError().message("Error applying algorithm").type("InfluencePrincipleServiceException")),
      HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  private PersuasionProfileAlgorithm selectAlgorithm(int aPersonId) throws Exception
  {
    PersuasionProfileAlgorithm persuasionProfileAlgorithm = null;

    logger.info("Selecting algorithm to apply");

    int historicCounter = dbManager.selectHistoricCount(aPersonId);

    com.uma.mestrado.persuasive_profiles.enums.AlgorithmEnum algorithmEnum = AlgorithmEnum.get(historicCounter);
    switch (algorithmEnum)
    {
      case EXPLICIT:
        logger.info("Selected EXPLICIT algorithm");
        persuasionProfileAlgorithm = new ExplicitAlgorithm(dbManager, aPersonId);
        break;
      case IMPLICIT:
        logger.info("Selected IMPLICIT algorithm");
        persuasionProfileAlgorithm = new ImplicitAlgorithm(dbManager, aPersonId);
        break;
      case RANDOM:
        logger.info("Selected RANDOM algorithm");
        persuasionProfileAlgorithm = new RandomAlgorithm(dbManager);
        break;
      default:
        break;
    }

    return persuasionProfileAlgorithm;
  }
}
