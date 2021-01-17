package com.uma.mestrado.persuasive_profiles.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.uma.mestrado.persuasive_profiles.services.PersonService;
import com.uma.mestrado.persuasive_profiles.services.PersuasionProfileService;

import swaggerCodegen.api.V1Api;
import swaggerCodegen.models.GetInfluencePrincipleResponse;
import swaggerCodegen.models.GetLoginResponse;


@RestController
public class BackendAPIControllers implements V1Api
{

  Logger logger = LoggerFactory.getLogger(BackendAPIControllers.class);

  @Autowired
  private PersonService personServices;
  @Autowired
  private PersuasionProfileService persuasionProfileService;

  @Override
  public ResponseEntity<Void> postRegister(String aName, Integer aAge, Integer aGenderId, Integer aCountryId, Integer aWeight, Integer aHeight,
  Boolean aHadNutricionalConsult, String aUsername, String aPassword)
  {
    return personServices.postRegister(aName, aAge, aGenderId, aCountryId, aWeight, aHeight, aHadNutricionalConsult, aUsername, aPassword);
  }

  @Override
  public ResponseEntity<GetLoginResponse> getV1Login(String aUsername, String aPwd)
  {
    return personServices.getV1Login(aUsername, aPwd);
  }

  @Override
  public ResponseEntity<Void> postHistoric(Integer aPersonId, Integer aInfluencePrincipleId, Boolean aWasInfluenced)
  {
    return personServices.postHistoric(aPersonId, aInfluencePrincipleId, aWasInfluenced);
  }

  @Override
  public ResponseEntity<GetInfluencePrincipleResponse> getPersonInfluencePrinciple(Integer aPersonId)
  {
    return persuasionProfileService.getPersonInfluencePrinciple(aPersonId);
  }
}
