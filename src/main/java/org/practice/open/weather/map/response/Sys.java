package org.practice.open.weather.map.response;

public class Sys {

	 private float type;
	 private float id;
	 private String country;
	 private float sunrise;
	 private float sunset;


	 // Getter Methods 

	 public float getType() {
	  return type;
	 }

	 public float getId() {
	  return id;
	 }

	 public String getCountry() {
	  return country;
	 }

	 public float getSunrise() {
	  return sunrise;
	 }

	 public float getSunset() {
	  return sunset;
	 }

	 // Setter Methods 

	 public void setType(float type) {
	  this.type = type;
	 }

	 public void setId(float id) {
	  this.id = id;
	 }

	 public void setCountry(String country) {
	  this.country = country;
	 }

	 public void setSunrise(float sunrise) {
	  this.sunrise = sunrise;
	 }

	 public void setSunset(float sunset) {
	  this.sunset = sunset;
	 }

	@Override
	public String toString() {
		return "Sys [type=" + type + ", id=" + id + ", country=" + country + ", sunrise=" + sunrise + ", sunset="
				+ sunset + "]";
	}
	

}
