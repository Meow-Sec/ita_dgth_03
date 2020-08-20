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
    List<Role> roleLists;
}











