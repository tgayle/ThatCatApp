package app.endershrooms.thatcatapp.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import app.endershrooms.thatcatapp.model.helper.IntToBoolean;
import com.squareup.moshi.Json;

@Entity
public class Breed {

  @Json(name = "weight")
  private Weight weight;

  @Json(name = "id")
  @PrimaryKey
  @NonNull
  private String id;

  @Json(name = "name")
  private String name;
  @Json(name = "cfa_url")
  private String cfaUrl;
  @Json(name = "vetstreet_url")
  private String vetstreetUrl;
  @Json(name = "vcahospitals_url")
  private String vcahospitalsUrl;
  @Json(name = "temperament")
  private String temperament;
  @Json(name = "origin")
  private String origin;
  @Json(name = "country_codes")
  private String countryCodes;
  @Json(name = "country_code")
  private String countryCode;
  @Json(name = "description")
  private String description;
  @Json(name = "life_span")
  private String lifeSpan;
  @Json(name = "indoor")
  private Integer indoor;
  @Json(name = "lap")
  private Integer lap;
  @Json(name = "alt_names")
  private String altNames;
  @Json(name = "adaptability")
  private Integer adaptability;
  @Json(name = "affection_level")
  private Integer affectionLevel;
  @Json(name = "child_friendly")
  private Integer childFriendly;
  @Json(name = "dog_friendly")
  private Integer dogFriendly;
  @Json(name = "energy_level")
  private Integer energyLevel;
  @Json(name = "grooming")
  private Integer grooming;
  @Json(name = "health_issues")
  private Integer healthIssues;
  @Json(name = "intelligence")
  private Integer intelligence;
  @Json(name = "shedding_level")
  private Integer sheddingLevel;
  @Json(name = "social_needs")
  private Integer socialNeeds;
  @Json(name = "stranger_friendly")
  private Integer strangerFriendly;
  @Json(name = "vocalisation")
  private Integer vocalisation;

  @Json(name = "experimental")
  @IntToBoolean
  private boolean experimental;

  @Json(name = "hairless")
  @IntToBoolean
  private boolean hairless;

  @Json(name = "natural")
  @IntToBoolean
  private boolean natural;

  @Json(name = "rare")
  @IntToBoolean
  private boolean rare;

  @Json(name = "rex")
  @IntToBoolean
  private boolean rex;

  @Json(name = "suppressed_tail")
  @IntToBoolean
  private boolean suppressedTail;

  @Json(name = "short_legs")
  @IntToBoolean
  private boolean shortLegs;

  @Json(name = "wikipedia_url")
  private String wikipediaUrl;

  @Json(name = "hypoallergenic")
  @IntToBoolean
  private boolean hypoallergenic;

  /**
   * No args constructor for use in serialization
   */
  public Breed() {
  }

  @Ignore
  public Breed(Weight weight, String id, String name, String cfaUrl, String vetstreetUrl,
      String vcahospitalsUrl, String temperament, String origin, String countryCodes,
      String countryCode, String description, String lifeSpan, Integer indoor, Integer lap,
      String altNames, Integer adaptability, Integer affectionLevel, Integer childFriendly,
      Integer dogFriendly, Integer energyLevel, Integer grooming, Integer healthIssues,
      Integer intelligence, Integer sheddingLevel, Integer socialNeeds, Integer strangerFriendly,
      Integer vocalisation, boolean experimental, boolean hairless, boolean natural, boolean rare,
      boolean rex, boolean suppressedTail, boolean shortLegs, String wikipediaUrl,
      boolean hypoallergenic) {
    super();
    this.weight = weight;
    this.id = id;
    this.name = name;
    this.cfaUrl = cfaUrl;
    this.vetstreetUrl = vetstreetUrl;
    this.vcahospitalsUrl = vcahospitalsUrl;
    this.temperament = temperament;
    this.origin = origin;
    this.countryCodes = countryCodes;
    this.countryCode = countryCode;
    this.description = description;
    this.lifeSpan = lifeSpan;
    this.indoor = indoor;
    this.lap = lap;
    this.altNames = altNames;
    this.adaptability = adaptability;
    this.affectionLevel = affectionLevel;
    this.childFriendly = childFriendly;
    this.dogFriendly = dogFriendly;
    this.energyLevel = energyLevel;
    this.grooming = grooming;
    this.healthIssues = healthIssues;
    this.intelligence = intelligence;
    this.sheddingLevel = sheddingLevel;
    this.socialNeeds = socialNeeds;
    this.strangerFriendly = strangerFriendly;
    this.vocalisation = vocalisation;
    this.experimental = experimental;
    this.hairless = hairless;
    this.natural = natural;
    this.rare = rare;
    this.rex = rex;
    this.suppressedTail = suppressedTail;
    this.shortLegs = shortLegs;
    this.wikipediaUrl = wikipediaUrl;
    this.hypoallergenic = hypoallergenic;
  }

  public Weight getWeight() {
    return weight;
  }

  public void setWeight(Weight weight) {
    this.weight = weight;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCfaUrl() {
    return cfaUrl;
  }

  public void setCfaUrl(String cfaUrl) {
    this.cfaUrl = cfaUrl;
  }

  public String getVetstreetUrl() {
    return vetstreetUrl;
  }

  public void setVetstreetUrl(String vetstreetUrl) {
    this.vetstreetUrl = vetstreetUrl;
  }

  public String getVcahospitalsUrl() {
    return vcahospitalsUrl;
  }

  public void setVcahospitalsUrl(String vcahospitalsUrl) {
    this.vcahospitalsUrl = vcahospitalsUrl;
  }

  public String getTemperament() {
    return temperament;
  }

  public void setTemperament(String temperament) {
    this.temperament = temperament;
  }

  public String getOrigin() {
    return origin;
  }

  public void setOrigin(String origin) {
    this.origin = origin;
  }

  public String getCountryCodes() {
    return countryCodes;
  }

  public void setCountryCodes(String countryCodes) {
    this.countryCodes = countryCodes;
  }

  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getLifeSpan() {
    return lifeSpan;
  }

  public void setLifeSpan(String lifeSpan) {
    this.lifeSpan = lifeSpan;
  }

  public Integer getIndoor() {
    return indoor;
  }

  public void setIndoor(Integer indoor) {
    this.indoor = indoor;
  }

  public Integer getLap() {
    return lap;
  }

  public void setLap(Integer lap) {
    this.lap = lap;
  }

  public String getAltNames() {
    return altNames;
  }

  public void setAltNames(String altNames) {
    this.altNames = altNames;
  }

  public Integer getAdaptability() {
    return adaptability;
  }

  public void setAdaptability(Integer adaptability) {
    this.adaptability = adaptability;
  }

  public Integer getAffectionLevel() {
    return affectionLevel;
  }

  public void setAffectionLevel(Integer affectionLevel) {
    this.affectionLevel = affectionLevel;
  }

  public Integer getChildFriendly() {
    return childFriendly;
  }

  public void setChildFriendly(Integer childFriendly) {
    this.childFriendly = childFriendly;
  }

  public Integer getDogFriendly() {
    return dogFriendly;
  }

  public void setDogFriendly(Integer dogFriendly) {
    this.dogFriendly = dogFriendly;
  }

  public Integer getEnergyLevel() {
    return energyLevel;
  }

  public void setEnergyLevel(Integer energyLevel) {
    this.energyLevel = energyLevel;
  }

  public Integer getGrooming() {
    return grooming;
  }

  public void setGrooming(Integer grooming) {
    this.grooming = grooming;
  }

  public Integer getHealthIssues() {
    return healthIssues;
  }

  public void setHealthIssues(Integer healthIssues) {
    this.healthIssues = healthIssues;
  }

  public Integer getIntelligence() {
    return intelligence;
  }

  public void setIntelligence(Integer intelligence) {
    this.intelligence = intelligence;
  }

  public Integer getSheddingLevel() {
    return sheddingLevel;
  }

  public void setSheddingLevel(Integer sheddingLevel) {
    this.sheddingLevel = sheddingLevel;
  }

  public Integer getSocialNeeds() {
    return socialNeeds;
  }

  public void setSocialNeeds(Integer socialNeeds) {
    this.socialNeeds = socialNeeds;
  }

  public Integer getStrangerFriendly() {
    return strangerFriendly;
  }

  public void setStrangerFriendly(Integer strangerFriendly) {
    this.strangerFriendly = strangerFriendly;
  }

  public Integer getVocalisation() {
    return vocalisation;
  }

  public void setVocalisation(Integer vocalisation) {
    this.vocalisation = vocalisation;
  }

  public boolean getExperimental() {
    return experimental;
  }

  public void setExperimental(boolean experimental) {
    this.experimental = experimental;
  }

  public boolean getHairless() {
    return hairless;
  }

  public void setHairless(boolean hairless) {
    this.hairless = hairless;
  }

  public boolean getNatural() {
    return natural;
  }

  public void setNatural(boolean natural) {
    this.natural = natural;
  }

  public boolean getRare() {
    return rare;
  }

  public void setRare(boolean rare) {
    this.rare = rare;
  }

  public boolean getRex() {
    return rex;
  }

  public void setRex(boolean rex) {
    this.rex = rex;
  }

  public boolean getSuppressedTail() {
    return suppressedTail;
  }

  public void setSuppressedTail(boolean suppressedTail) {
    this.suppressedTail = suppressedTail;
  }

  public boolean getShortLegs() {
    return shortLegs;
  }

  public void setShortLegs(boolean shortLegs) {
    this.shortLegs = shortLegs;
  }

  public String getWikipediaUrl() {
    return wikipediaUrl;
  }

  public void setWikipediaUrl(String wikipediaUrl) {
    this.wikipediaUrl = wikipediaUrl;
  }

  public boolean getHypoallergenic() {
    return hypoallergenic;
  }

  public void setHypoallergenic(boolean hypoallergenic) {
    this.hypoallergenic = hypoallergenic;
  }

}