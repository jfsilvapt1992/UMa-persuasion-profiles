package com.uma.mestrado.persuasive_profiles.algorithm;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uma.mestrado.persuasive_profiles.models.PersuasionProfileDto;


// This one is using user data (age, weight, had or not nutricional consult,etc)
public class ExplicitAlgorithm implements PersuasionProfileAlgorithm
{

  Logger logger = LoggerFactory.getLogger(ExplicitAlgorithm.class);

  @Override
  public PersuasionProfileDto execute() throws Exception
  {
    logger.info("EXECUTED EXPLICIT ALGORITHM");
    return null;
  }

}
