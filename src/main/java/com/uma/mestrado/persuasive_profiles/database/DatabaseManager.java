package com.uma.mestrado.persuasive_profiles.database;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.uma.mestrado.persuasive_profiles.database.entities.Country;
import com.uma.mestrado.persuasive_profiles.database.entities.Gender;
import com.uma.mestrado.persuasive_profiles.database.entities.Historic;
import com.uma.mestrado.persuasive_profiles.database.entities.InfluencePrinciple;
import com.uma.mestrado.persuasive_profiles.database.entities.Person;
import com.uma.mestrado.persuasive_profiles.database.entities.QHistoric;
import com.uma.mestrado.persuasive_profiles.database.entities.QInfluencePrinciple;
import com.uma.mestrado.persuasive_profiles.database.entities.QPerson;
import com.uma.mestrado.persuasive_profiles.database.entities.Users;
import com.uma.mestrado.persuasive_profiles.database.repository.CountryDAO;
import com.uma.mestrado.persuasive_profiles.database.repository.GenderDAO;
import com.uma.mestrado.persuasive_profiles.database.repository.HistoricDAO;
import com.uma.mestrado.persuasive_profiles.database.repository.InfluencePrincipleDAO;
import com.uma.mestrado.persuasive_profiles.database.repository.PersonDAO;
import com.uma.mestrado.persuasive_profiles.database.repository.UsersDAO;
import com.uma.mestrado.persuasive_profiles.exceptions.DatabaseException;
import com.uma.mestrado.persuasive_profiles.models.CountryDto;
import com.uma.mestrado.persuasive_profiles.models.GETLoginRequest;
import com.uma.mestrado.persuasive_profiles.models.GenderDto;
import com.uma.mestrado.persuasive_profiles.models.HistoricDto;
import com.uma.mestrado.persuasive_profiles.models.LoginOutput;
import com.uma.mestrado.persuasive_profiles.models.POSTHistoricRequest;
import com.uma.mestrado.persuasive_profiles.models.POSTRegisterRequest;
import com.uma.mestrado.persuasive_profiles.models.PersonDto;
import com.uma.mestrado.persuasive_profiles.models.PersuasionProfileDto;


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
  @Autowired
  private InfluencePrincipleDAO influencePrincipleDAO;
  @Autowired
  private HistoricDAO historicDAO;

  @Transactional
  public void register(POSTRegisterRequest aRegisterInput) throws DatabaseException
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
  public LoginOutput login(GETLoginRequest aLoginRequest) throws DatabaseException
  {
    LoginOutput response = null;
    try
    {
      logger.info("Selecting login information from database");

      Users user = usersDAO.findOneByUsername(aLoginRequest.getUsername());

      boolean pwdMatches = user == null ? false : user.getPassword().equalsIgnoreCase(aLoginRequest.getPassword());

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

  @Transactional
  public void insertHistoric(POSTHistoricRequest aHistoricReq) throws DatabaseException
  {
    try
    {
      Person person = personDAO.findById(aHistoricReq.getPersonId()).get();
      InfluencePrinciple influencePrinciple = influencePrincipleDAO.findById(aHistoricReq.getInfluencePrincipleId()).get();

      Date currentDate = new Date();

      historicDAO.save(new Historic(influencePrinciple, person, currentDate, currentDate, aHistoricReq.getWasInfluenced()));
    }
    catch (@SuppressWarnings("unused")
    Exception ex)
    {
      throw new DatabaseException("Error doing register");
    }
  }

  @Transactional
  public int selectHistoricCount(int aPersonId) throws DatabaseException
  {
    try
    {
      return historicDAO.findAll(QPerson.person.id.eq(aPersonId)).size();
    }
    catch (@SuppressWarnings("unused")
    Exception ex)
    {
      throw new DatabaseException("Error selecting historic");
    }
  }

  @Transactional
  public PersuasionProfileDto selectInfluencePrinciple(String aName) throws DatabaseException
  {
    try
    {
      InfluencePrinciple influencePrinciple = influencePrincipleDAO.findOne(QInfluencePrinciple.influencePrinciple.name.equalsIgnoreCase(aName)).get();

      return new PersuasionProfileDto(influencePrinciple.getId(), influencePrinciple.getName());
    }
    catch (@SuppressWarnings("unused")
    Exception ex)
    {
      throw new DatabaseException("Error selecting historic");
    }
  }

  @Transactional
  public PersonDto selectPerson(int aPersonId) throws DatabaseException
  {
    try
    {
      Person person = personDAO.findById(aPersonId).get();

      return new PersonDto(person.getName(), person.getAge(), person.getWeight(), person.getHeight());
    }
    catch (@SuppressWarnings("unused")
    Exception ex)
    {
      throw new DatabaseException("Error selecting historic");
    }
  }

  @Transactional
  public List<CountryDto> selectCountries() throws DatabaseException
  {
    try
    {
      List<Country> countries = countryDAO.findAll();

      return countries.stream().map(temp -> new CountryDto(temp.getId(), temp.getName())).collect(Collectors.toList());
    }
    catch (@SuppressWarnings("unused")
    Exception ex)
    {
      throw new DatabaseException("Error selecting countries");
    }
  }

  @Transactional
  public List<GenderDto> selectGenders() throws DatabaseException
  {
    try
    {
      List<Gender> genders = genderDAO.findAll();

      return genders.stream().map(temp -> new GenderDto(temp.getId(), temp.getType())).collect(Collectors.toList());
    }
    catch (@SuppressWarnings("unused")
    Exception ex)
    {
      throw new DatabaseException("Error selecting genders");
    }
  }

  @Transactional
  public HistoricDto selectAllHistoric(int aPersonId) throws DatabaseException
  {
    try
    {
      List<Historic> historicList = historicDAO.findAll(QHistoric.historic.person.id.eq(aPersonId));

      List<Integer> influencePrinciplesIds =
      historicList.stream().map(curr -> curr.getInfluencePrinciple().getId()).collect(Collectors.toList()).stream().distinct().collect(Collectors.toList());

      List<PersuasionProfileDto> persuasionsProfile = new ArrayList<PersuasionProfileDto>();
      for (int tempId : influencePrinciplesIds)
      {
        PersuasionProfileDto tempPersuasionProfile = new PersuasionProfileDto();
        tempPersuasionProfile.setId(tempId);
        tempPersuasionProfile.setName(influencePrincipleDAO.getOne(tempId).getName());
        tempPersuasionProfile.setTotal(historicList.size());

        List<Historic> tempHistoricList =
        historicDAO.findAll(QHistoric.historic.person.id.eq(aPersonId).and(QHistoric.historic.influencePrinciple.id.eq(tempId)));

        for (Historic tempHistoric : tempHistoricList)
        {
          if (tempHistoric.isWasInfluenced())
          {
            tempPersuasionProfile.increaseTotalInflueced();
          }
        }
        persuasionsProfile.add(tempPersuasionProfile);
      }

      Person person = personDAO.getOne(aPersonId);
      PersonDto personDto = new PersonDto(person.getName(), person.getAge(), person.getWeight(), person.getHeight());

      return new HistoricDto(personDto, persuasionsProfile, historicList.size());
    }
    catch (@SuppressWarnings("unused")
    Exception ex)
    {
      throw new DatabaseException("Error selecting genders");
    }
  }

  private Person insertPerson(POSTRegisterRequest aRegisterInput) throws DatabaseException
  {
    Person personData = null;
    try
    {
      logger.info("Inserting person data in database");

      Country country = countryDAO.findById(aRegisterInput.getCountryId()).get();
      Gender gender = genderDAO.findById(aRegisterInput.getGenderId()).get();

      personData = new Person(country, gender, aRegisterInput.getName(), Integer.parseInt(aRegisterInput.getAge()),
      Integer.parseInt(aRegisterInput.getWeight()), Integer.parseInt(aRegisterInput.getHeight()),
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
