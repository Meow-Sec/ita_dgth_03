package com.ita.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Role {
    RoleListContent roleListContent;
    RoleListLocatorKey roleListLocatorKey;
    String roleListType;
}
