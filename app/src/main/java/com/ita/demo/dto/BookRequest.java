package com.ita.demo.dto;

import com.ita.demo.model.Container;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest {
    String requestorBookingReference;
    String carrier;
    List<Container> containers;


}
