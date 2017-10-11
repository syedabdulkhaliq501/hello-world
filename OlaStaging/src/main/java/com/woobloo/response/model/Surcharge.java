package com.woobloo.response.model;

public class Surcharge
{
  private String name;

  public String getName() { return this.name; }

  public void setName(String name) { this.name = name; }

  private String type;

  public String getType() { return this.type; }

  public void setType(String type) { this.type = type; }

  private String description;

  public String getDescription() { return this.description; }

  public void setDescription(String description) { this.description = description; }

  private double value;

  public double getValue() { return this.value; }

  public void setValue(double value) { this.value = value; }
}
