package com.uma.mestrado.persuasive_profiles.models;


public class GETInfluencePrincipleRequest
{

  private int personId;
  private String influencePrincipleName;
  private int influencePrincipleId;

  public GETInfluencePrincipleRequest()
  {
    super();
  }

  public GETInfluencePrincipleRequest(int aPersonId, String aInfluencePrincipleName, int aInfluencePrincipleId)
  {
    super();
    personId = aPersonId;
    influencePrincipleName = aInfluencePrincipleName;
    influencePrincipleId = aInfluencePrincipleId;
  }


  public int getPersonId()
  {
    return personId;
  }


  public void setPersonId(int aPersonId)
  {
    personId = aPersonId;
  }


  public String getInfluencePrincipleName()
  {
    return influencePrincipleName;
  }


  public void setInfluencePrincipleName(String aInfluencePrincipleName)
  {
    influencePrincipleName = aInfluencePrincipleName;
  }


  public int getInfluencePrincipleId()
  {
    return influencePrincipleId;
  }


  public void setInfluencePrincipleId(int aInfluencePrincipleId)
  {
    influencePrincipleId = aInfluencePrincipleId;
  }


}
