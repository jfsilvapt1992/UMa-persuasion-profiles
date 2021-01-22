package com.uma.mestrado.persuasive_profiles.models;


import java.util.List;


public class HistoricDto
{

  private PersonDto person;
  private List<PersuasionProfileDto> persuasions;
  private int totalHistoric;

  public HistoricDto(PersonDto aPerson, List<PersuasionProfileDto> aPersuasions, int aTotalHistoric)
  {
    super();
    person = aPerson;
    persuasions = aPersuasions;
    totalHistoric = aTotalHistoric;
  }

  public PersonDto getPerson()
  {
    return person;
  }

  public void setPerson(PersonDto aPerson)
  {
    person = aPerson;
  }

  public List<PersuasionProfileDto> getPersuasions()
  {
    return persuasions;
  }

  public void setPersuasions(List<PersuasionProfileDto> aPersuasions)
  {
    persuasions = aPersuasions;
  }


  public int getTotalHistoric()
  {
    return totalHistoric;
  }


  public void setTotalHistoric(int aTotalHistoric)
  {
    totalHistoric = aTotalHistoric;
  }


}

