package com.cybertek.tests.pojo.countries_us;

import lombok.Data;

import java.util.List;

@Data
public class Countries {

    private String country_id;
    private String country_name;
    private int region_id;
    private List<Links> links;


}
