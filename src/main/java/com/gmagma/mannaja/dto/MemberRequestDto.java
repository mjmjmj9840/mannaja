package com.gmagma.mannaja.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MemberRequestDto {
    private String groupLink;
    private String nickname;
    private String password;
}
