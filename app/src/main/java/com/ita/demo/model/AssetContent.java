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
class AssetContent{
    MessageInfo messageInfo;
    String requestorBookingReference;
    String actionType;
    String shipmentCargoType;
    List<Container> containers;
    List<Cargo> cargos;

}
