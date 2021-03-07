
package com.nhz.mycode.completed.mongo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class UserProbability {

    private int lastCount;

    private String userDesc;

    private Map<String, Map<String, Double>> probabilityMap;
}
