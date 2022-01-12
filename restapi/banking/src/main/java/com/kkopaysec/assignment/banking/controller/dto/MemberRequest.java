package com.kkopaysec.assignment.banking.controller.dto;

import lombok.Getter;
import javax.validation.constraints.*;

@Getter
public class MemberRequest {

    @NotBlank(message = "이름은 필수 값입니다.")
    private String name;

    @Min(value = 1, message = "나이는 1세 이상이여야합니다.") @Max(value = 100, message = "나이는 100세 이하여야합니다.")
    @NotNull(message = "나이는 필수 값입니다.")
    private int age;

    @NotBlank
    @Pattern(regexp = "\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])")
    private String membershipDate;

    public MemberRequest(String name, int age, String membershipDate) {
        this.name = name;
        this.age = age;
        this.membershipDate = membershipDate;
    }
}

