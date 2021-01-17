package com.uma.mestrado.persuasive_profiles.algorithm;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uma.mestrado.persuasive_profiles.models.PersuasionProfileDto;


// This one is using historic of user actions
public class ImplicitAlgorithm implements PersuasionProfileAlgorithm
{

  Logger logger = LoggerFactory.getLogger(ImplicitAlgorithm.class);

  // TODO -> ter em conta o ratio de numero de vezes que a persuasao funcionou ou nao
  // TODO -> wasInfluenced / total historico

  @Override
  public PersuasionProfileDto execute() throws Exception
  {
    PersuasionProfileDto response = null;

    logger.info("Executing implicit algorithm");

    logger.info("Executed explit algorithm succesffully!!!");
    logger.info("Selected principle : " + response.getName() + " !!");

    return response;
  }

}
