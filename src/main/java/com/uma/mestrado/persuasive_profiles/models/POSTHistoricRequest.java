package com.uma.mestrado.persuasive_profiles.models;


public class POSTHistoricRequest
{

  private int personId, influencePrincipleId;
  private boolean wasInfluenced;

  public POSTHistoricRequest(int aPersonId, int aInfluencePrincipleId, boolean aWasInfluenced)
  {
    super();
    personId = aPersonId;
    influencePrincipleId = aInfluencePrincipleId;
    wasInfluenced = aWasInfluenced;
  }

  public int getPersonId()
  {
    return personId;
  }

  public int getInfluencePrincipleId()
  {
    return influencePrincipleId;
  }

  public boolean getWasInfluenced()
  {
    return wasInfluenced;
  }
}
