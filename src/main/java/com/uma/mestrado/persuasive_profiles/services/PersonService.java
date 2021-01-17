package com.uma.mestrado.persuasive_profiles.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.uma.mestrado.persuasive_profiles.database.DatabaseManager;
import com.uma.mestrado.persuasive_profiles.exceptions.DatabaseException;
import com.uma.mestrado.persuasive_profiles.models.LoginOutput;
import com.uma.mestrado.persuasive_profiles.models.LoginRequest;
import com.uma.mestrado.persuasive_profiles.models.RegisterRequest;

import swaggerCodegen.models.BasicError;
import swaggerCodegen.models.LoginResponse;


@Service(value = "PersonService")
public class PersonService
{

  Logger logger = LoggerFactory.getLogger(PersonService.class);

  @Autowired
  private DatabaseManager dbManager;

  public ResponseEntity<Void> postRegister(String aName, Integer aAge, Integer aGenderId, Integer aCountryId, Integer aWeight, Integer aHeight,
  Boolean aHadNutricionalConsult, String aUsername, String aPassword)
  {
    try
    {
      logger.info("Starting register of person..");

      RegisterRequest inputs = new RegisterRequest(aName, aUsername, aPassword, aAge, aCountryId, aGenderId, aWeight, aHeight, aHadNutricionalConsult);

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

  public ResponseEntity<LoginResponse> getV1Login(String username, String password)
  {
    try
    {
      logger.info("Starting login ..");

      LoginRequest request = new LoginRequest(username, password);

      LoginOutput userInfo = dbManager.login(request);

      boolean login = userInfo != null ? true : false;
      if (login)
      {
        logger.info("Login successffully!!");
        return new ResponseEntity<LoginResponse>(new LoginResponse().username(userInfo.getUsername()).personId(userInfo.getPerson_id()), HttpStatus.OK);
      }
      else
      {
        logger.info("Not authorize!!");
        return new ResponseEntity<LoginResponse>(
        new LoginResponse().error(new BasicError().message("Invalid username or password").type("UnauthorizeException")), HttpStatus.UNAUTHORIZED);
      }
    }
    catch (DatabaseException e)
    {
      logger.error(this.getClass().getName() + " error : ", e.getMessage());
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
