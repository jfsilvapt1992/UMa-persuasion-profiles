package com.uma.mestrado.persuasive_profiles.services;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uma.mestrado.persuasive_profiles.database.DatabaseManager;
import com.uma.mestrado.persuasive_profiles.models.CountryDto;
import com.uma.mestrado.persuasive_profiles.models.GenderDto;


@Service(value = "UtilsService")
public class UtilsServices
{

  Logger logger = LoggerFactory.getLogger(UtilsServices.class);

  @Autowired
  private DatabaseManager dbManager;

  public List<CountryDto> listAllCountries() throws Exception
  {
    try
    {
      logger.info("Starting list of all countries");
      return dbManager.selectCountries();
    }
    catch (Exception e)
    {
      throw e;
    }
  }

  public List<GenderDto> listAllGenders() throws Exception
  {
    try
    {
      logger.info("Starting list of all genders");
      return dbManager.selectGenders();
    }
    catch (Exception e)
    {
      throw e;
    }
  }
}
