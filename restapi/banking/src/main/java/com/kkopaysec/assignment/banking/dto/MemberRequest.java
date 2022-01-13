package com.kkopaysec.assignment.banking.dto;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Getter
@NoArgsConstructor
public class MemberRequest {

    @NotBlank(message = "이름은 필수 값입니다.")
    @ApiParam(value = "사용자 이름")
    private String name;

    @Min(value = 1, message = "나이는 1세 이상이여야합니다.") @Max(value = 100, message = "나이는 100세 이하여야합니다.")
    @NotNull(message = "나이는 필수 값입니다.")
    @ApiParam(value = "사용자 나이")
    private int age;

    @NotBlank
    @Pattern(regexp = "\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])")
    @ApiParam(value = "가입 일자")
    private String membershipDate;

    public MemberRequest(String name, int age, String membershipDate) {
        this.name = name;
        this.age = age;
        this.membershipDate = membershipDate;
    }
}

