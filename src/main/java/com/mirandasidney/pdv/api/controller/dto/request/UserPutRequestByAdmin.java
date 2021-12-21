package com.mirandasidney.pdv.api.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserPutRequestByAdmin {
    private Long id;
    private String profile;
}
