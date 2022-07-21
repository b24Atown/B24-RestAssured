package com.cybertek.tests.pojo;

import lombok.Data;

import java.util.List;

@Data
public class SpartanSearch {

    private List<Spartan> content;

    private int totalElement;

    /**
     * this is the template were making pojo for
     * {
     *     "content": [
     *         {
     *             "id": 13,
     *             "name": "Jaimie",
     *             "gender": "Female",
     *             "phone": 7842554879
     *         },
     *         {
     *             "id": 92,
     *             "name": "Caitlin",
     *             "gender": "Female",
     *             "phone": 6911121800
     *         }
     *     ],
     *     "totalElement": 2
     * }
     */

}
