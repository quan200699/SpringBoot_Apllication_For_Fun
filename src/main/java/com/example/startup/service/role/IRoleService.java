package com.example.startup.service.role;


import com.example.startup.model.entity.Role;
import com.example.startup.service.IGeneralService;

public interface IRoleService extends IGeneralService<Role> {
    Role findByName(String name);
}
