package com.users.users.dto;

import com.users.users.model.CustomUser;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class RoleDTO {

    private Integer id;
    private Map<String, List<CustomUser>> infoRoles;

    public RoleDTO(Integer id, Map<String, List<CustomUser>> infoRoles) {
        this.id = id;
        this.infoRoles = infoRoles;
    }
}
