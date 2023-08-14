package com.preproject.seb_pre_15.member.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MemberPatchDto {
    @NotNull
    private String name;

}
