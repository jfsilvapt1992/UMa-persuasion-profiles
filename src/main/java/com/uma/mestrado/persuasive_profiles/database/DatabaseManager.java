package com.uma.mestrado.persuasive_profiles.database;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.uma.mestrado.persuasive_profiles.database.entities.Country;
import com.uma.mestrado.persuasive_profiles.database.entities.Gender;
import com.uma.mestrado.persuasive_profiles.database.entities.Person;
import com.uma.mestrado.persuasive_profiles.database.entities.Users;
import com.uma.mestrado.persuasive_profiles.database.repository.CountryDAO;
import com.uma.mestrado.persuasive_profiles.database.repository.GenderDAO;
import com.uma.mestrado.persuasive_profiles.database.repository.PersonDAO;
import com.uma.mestrado.persuasive_profiles.database.repository.UsersDAO;
import com.uma.mestrado.persuasive_profiles.exceptions.DatabaseException;
import com.uma.mestrado.persuasive_profiles.models.LoginOutput;
import com.uma.mestrado.persuasive_profiles.models.LoginRequest;
import com.uma.mestrado.persuasive_profiles.models.RegisterRequest;


@Component(value = "DatabaseManager")
public class DatabaseManager
{

  Logger logger = LoggerFactory.getLogger(DatabaseManager.class);

  @Autowired
  private PersonDAO personDAO;
  @Autowired
  private CountryDAO countryDAO;
  @Autowired
  private GenderDAO genderDAO;
  @Autowired
  private UsersDAO usersDAO;

  @Transactional
  public void register(RegisterRequest aRegisterInput) throws DatabaseException
  {
    try
    {
      Person personInserted = insertPerson(aRegisterInput);

      createUser(personInserted, aRegisterInput.getUsername(), aRegisterInput.getPwd());
    }
    catch (@SuppressWarnings("unused")
    Exception ex)
    {
      throw new DatabaseException("Error doing register");
    }
  }

  @Transactional
  public LoginOutput login(LoginRequest aLoginRequest) throws DatabaseException
  {
    LoginOutput response = null;
    try
    {
      logger.info("Selecting login information from database");

      Users user = usersDAO.findOneByUsername(aLoginRequest.getUsername());

      boolean pwdMatches = user.getPassword().equalsIgnoreCase(aLoginRequest.getPassword());

      if (pwdMatches)
      {
        response = new LoginOutput(user.getUsername(), user.getPerson().getId());
      }
      logger.info("Information selected successfully");
    }
    catch (@SuppressWarnings("unused")
    Exception ex)
    {
      throw new DatabaseException("Error in login information");
    }
    return response;
  }

  private Person insertPerson(RegisterRequest aRegisterInput) throws DatabaseException
  {
    Person personData = null;
    try
    {
      logger.info("Inserting person data in database");

      Country country = countryDAO.findById(aRegisterInput.getCountryId()).get();
      Gender gender = genderDAO.findById(aRegisterInput.getGenderId()).get();
      personData = new Person(country, gender, aRegisterInput.getName(), aRegisterInput.getAge(), aRegisterInput.getWeight(), aRegisterInput.getHeight(),
      aRegisterInput.isHadNutricionalConsult());
      personDAO.save(personData);

      logger.info("Person data inserted successfully");
    }
    catch (@SuppressWarnings("unused")
    Exception ex)
    {
      throw new DatabaseException("Error saving person data");
    }
    return personData;
  }

  private void createUser(Person aPerson, String aUsername, String aPwd) throws DatabaseException
  {
    try
    {
      logger.info("Creating new user in database");

      Users userData = new Users(aPerson, aUsername, aPwd);

      usersDAO.save(userData);

      logger.info("User created successfully");
    }
    catch (@SuppressWarnings("unused")
    Exception ex)
    {
      throw new DatabaseException("Error creating user");
    }
  }

}
