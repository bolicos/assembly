package com.analuciabolico.assembly.v1.core.authority;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserAuthorityEnum {
    READ_PRODUCT("hasAuthority(PRODUCTS)"),
    FULL_PRODUCT("hasAuthority(PRODUCTS)");

    private String authority;
}
