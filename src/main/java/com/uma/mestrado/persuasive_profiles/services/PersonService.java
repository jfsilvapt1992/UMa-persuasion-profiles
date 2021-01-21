package com.uma.mestrado.persuasive_profiles.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.uma.mestrado.persuasive_profiles.database.DatabaseManager;
import com.uma.mestrado.persuasive_profiles.exceptions.DatabaseException;
import com.uma.mestrado.persuasive_profiles.models.GETLoginRequest;
import com.uma.mestrado.persuasive_profiles.models.LoginOutput;
import com.uma.mestrado.persuasive_profiles.models.POSTHistoricRequest;
import com.uma.mestrado.persuasive_profiles.models.POSTRegisterRequest;

import swaggerCodegen.models.BasicError;
import swaggerCodegen.models.GetLoginResponse;


@Service(value = "PersonService")
public class PersonService
{

  Logger logger = LoggerFactory.getLogger(PersonService.class);

  @Autowired
  private DatabaseManager dbManager;

  public ResponseEntity<Void> postRegister(String aName, String aAge, Integer aGenderId, Integer aCountryId, String aWeight, String aHeight,
  Boolean aHadNutricionalConsult, String aUsername, String aPassword)
  {
    try
    {
      logger.info("Starting register of person..");

      POSTRegisterRequest inputs =
      new POSTRegisterRequest(aName, aUsername, aPassword, aAge, aCountryId, aGenderId, aWeight, aHeight, aHadNutricionalConsult);

      dbManager.register(inputs);

      logger.info("Person registered with success");
    }
    catch (DatabaseException e)
    {
      logger.error(this.getClass().getName() + " error : ", e.getMessage());
      return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<Void>(HttpStatus.OK);
  }

  public ResponseEntity<GetLoginResponse> getV1Login(String aUsername, String aPwd)
  {
    try
    {
      logger.info("Starting login ..");

      GETLoginRequest request = new GETLoginRequest(aUsername, aPwd);

      LoginOutput userInfo = dbManager.login(request);

      boolean login = userInfo != null ? true : false;
      if (login)
      {
        logger.info("Login successffully!!");
        return new ResponseEntity<GetLoginResponse>(new GetLoginResponse().username(userInfo.getUsername()).personId(userInfo.getPerson_id()), HttpStatus.OK);
      }
      else
      {
        logger.info("Not authorize!!");
        return new ResponseEntity<GetLoginResponse>(
        new GetLoginResponse().error(new BasicError().message("Invalid username or password").type("UnauthorizeException")), HttpStatus.UNAUTHORIZED);
      }
    }
    catch (DatabaseException e)
    {
      logger.error(this.getClass().getName() + " error : ", e.getMessage());
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  public ResponseEntity<Void> postHistoric(Integer aPersonId, Integer aInfluencePrincipleId, Boolean aWasInfluenced)
  {
    try
    {
      logger.info("Starting inserting new person historic ..");

      dbManager.insertHistoric(new POSTHistoricRequest(aPersonId, aInfluencePrincipleId, aWasInfluenced));

      logger.info("Inserted historic successfully");

      return new ResponseEntity<>(HttpStatus.OK);
    }
    catch (DatabaseException e)
    {
      logger.error(this.getClass().getName() + " error : ", e.getMessage());
      return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
