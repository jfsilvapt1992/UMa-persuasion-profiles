package com.uma.mestrado.persuasive_profiles.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.uma.mestrado.persuasive_profiles.services.PersonService;

import swaggerCodegen.api.V1Api;
import swaggerCodegen.models.LoginResponse;


@RestController
public class BackendAPIControllers implements V1Api
{

  Logger logger = LoggerFactory.getLogger(BackendAPIControllers.class);

  @Autowired
  private PersonService personServices;

  @Override
  public ResponseEntity<Void> postRegister(String aName, Integer aAge, Integer aGenderId, Integer aCountryId, Integer aWeight, Integer aHeight,
  Boolean aHadNutricionalConsult, String aUsername, String aPassword)
  {
    return personServices.postRegister(aName, aAge, aGenderId, aCountryId, aWeight, aHeight, aHadNutricionalConsult, aUsername, aPassword);
  }

  @Override
  public ResponseEntity<LoginResponse> getV1Login(String aUsername, String aPwd)
  {
    return personServices.getV1Login(aUsername, aPwd);
  }

}
