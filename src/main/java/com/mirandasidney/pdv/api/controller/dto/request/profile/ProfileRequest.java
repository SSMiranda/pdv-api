package com.mirandasidney.pdv.api.controller.dto.request.profile;

import com.mirandasidney.pdv.api.controller.dto.request.module.ModuleRequestById;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProfileRequest implements Serializable {

    private Long id;
    private String profileName;
    private String description;
    private Set<ModuleRequestById> modules;
}
