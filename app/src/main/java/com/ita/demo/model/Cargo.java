package com.ita.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Can be empty cargo meaning shipping of empty container such as SOC case
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class Cargo{
    String cargoNature;
    String cargoDescription;
}
