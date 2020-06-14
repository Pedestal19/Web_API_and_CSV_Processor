package com.gabe.hosanna.model;

/**
 * Author: Hosanna Gabe-Oji
 * Project: Wallpapers
 * Date:   2020-06-14
 */
public class FilteredResults {

    private String firstName;
    private String lastName;
    private String email;
    private String country;
    private String carModel;
    private String carModelYear;
    private String carColour;
    private String gender;
    private String jobTitle;
    private String bio;

    public FilteredResults(String firstName, String lastName, String email, String country, String carModel, String carModelYear, String carColour, String gender, String jobTitle, String bio) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.country = country;
        this.carModel = carModel;
        this.carModelYear = carModelYear;
        this.carColour = carColour;
        this.gender = gender;
        this.jobTitle = jobTitle;
        this.bio = bio;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarModelYear() {
        return carModelYear;
    }

    public void setCarModelYear(String carModelYear) {
        this.carModelYear = carModelYear;
    }

    public String getCarColour() {
        return carColour;
    }

    public void setCarColour(String carColour) {
        this.carColour = carColour;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
