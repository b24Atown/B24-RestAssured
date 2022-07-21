package com.cybertek.tests.pojo.zipCode;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ZIpInfo {

    @JsonProperty("post code")
    private String postCode;
    private String country;
    @JsonProperty("country abbreviation")
    private String countryAbbreviation;
    private List<Place> places;
}
