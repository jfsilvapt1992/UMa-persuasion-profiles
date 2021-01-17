package com.uma.mestrado.persuasive_profiles.models;


public class POSTHistoricRequest
{

  private int personId, influencePrincipleId;

  public POSTHistoricRequest(int aPersonId, int aInfluencePrincipleId)
  {
    super();
    personId = aPersonId;
    influencePrincipleId = aInfluencePrincipleId;
  }

  public int getPersonId()
  {
    return personId;
  }


  public int getInfluencePrincipleId()
  {
    return influencePrincipleId;
  }


}
