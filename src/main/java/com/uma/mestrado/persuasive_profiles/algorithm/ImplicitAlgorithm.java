package com.uma.mestrado.persuasive_profiles.algorithm;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uma.mestrado.persuasive_profiles.models.PersuasionProfileDto;


// This one is using historic of user actions
public class ImplicitAlgorithm implements PersuasionProfileAlgorithm
{

  Logger logger = LoggerFactory.getLogger(ImplicitAlgorithm.class);

  @Override
  public PersuasionProfileDto execute() throws Exception
  {
    logger.info("EXECUTED IMPLICIT ALGORITHM");
    return null;
  }

}
