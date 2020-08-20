package com.ita.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class Location{
    /**
     * 应该可以不写东西上去
     */
    String unLocode;
    String portName;
    String portCode;
    String city;
    String county;
    String state;
    String country;
    String facilityCode;
    String facilityName;
    String gpsLocation;
    String gln;
    String address;
    String timeZone;
    TerminalOperator terminalOperator;
}
