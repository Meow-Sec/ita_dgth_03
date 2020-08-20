package com.ita.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class RoleListLocatorKey{
    String carrierScac;
    String requestorBookingReference;
    String roleListType;
}
