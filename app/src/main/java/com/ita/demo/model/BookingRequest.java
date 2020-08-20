package com.ita.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookingRequest {
    String roleListType;
    AssetContent assetContent;
    RoleListLocatorKey roleListLocatorKey;
}
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class AssetContent{
    MessageInfo messageInfo;
    String requestorBookingReference;
    String actionType;
    String shipmentCargoType;
    List<Container> containers;
    List<Cargo> cargos;
}

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class MessageInfo{
    String messageID;
    String messageType;
    MessageDateTime messageDateTime;
}

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class MessageDateTime{
    String utc;
    String local;
    String timeZone;
}

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class Carrier{
    String scac;
}

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class Container{
    String carrierContainerSizeType;
    Integer quantity;
    EstimatedContainerGrossWeight estimatedContainerGrossWeight;
    EstimatedContainerNetWeight estimatedContainerNetWeight;
    Haulage haulage;
    List<RoutePoints> routePoints;
}

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class EstimatedContainerGrossWeight{
    Double weight;
    String weightUnit;
}

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class EstimatedContainerNetWeight{
    Double weight;
    String weightUnit;
}

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class Haulage{
    String outBound;
    String inBound;
}

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class Por{
    String city;
    String county;
    String state;
    String country;
    String unLocode;
}

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class Fnd{
    String city;
    String county;
    String state;
    String country;
    String unLocode;
}

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class RoutePoints{
    Integer pointSequence;
    Location location;
    ArrivingTransportInfo arrivingTransportInfo;
    DepartingTransportInfo departingTransportInfo;
}

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

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class TerminalOperator{
    String orgID;
    String smdg;
    String name;
}

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class ArrivingTransportInfo{
    String mode;
}

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class DepartingTransportInfo{
    String mode;
}


// Can be empty cargo meaning shipping of empty container such as SOC case
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class Cargo{
    String cargoNature;
    String cargoDescription;
}

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class RoleListLocatorKey{

}











