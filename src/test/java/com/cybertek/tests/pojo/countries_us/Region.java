package com.cybertek.tests.pojo.countries_us;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Region {
    @JsonProperty("region_id")
    private int regionId;
    @JsonProperty("region_name")
    private String regionName;
    private List<Links> links;
}
