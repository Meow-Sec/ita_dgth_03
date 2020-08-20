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
class Container{
    String carrierContainerSizeType;
    Integer quantity;
    EstimatedContainerGrossWeight estimatedContainerGrossWeight;
    EstimatedContainerNetWeight estimatedContainerNetWeight;
    Haulage haulage;
    List<RoutePoint> routePoints;
    Por por;
    Fnd fnd;
}
