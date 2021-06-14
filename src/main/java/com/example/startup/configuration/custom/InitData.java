package com.example.startup.configuration.custom;

import com.example.startup.enumeration.RankName;
import com.example.startup.enumeration.RoleName;
import com.example.startup.model.entity.Loyalty;
import com.example.startup.model.entity.Role;
import com.example.startup.model.entity.User;
import com.example.startup.service.loyalty.ILoyaltyService;
import com.example.startup.service.role.IRoleService;
import com.example.startup.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
public class InitData {
    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private ILoyaltyService loyaltyService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        List<User> users = (List<User>) userService.findAll();
        List<Role> roleList = (List<Role>) roleService.findAll();
        List<Loyalty> loyalties = (List<Loyalty>) loyaltyService.findAll();
        if (loyalties.isEmpty()) {
            RankName[] rankNames = RankName.values();
            for (RankName rankName : rankNames) {
                Loyalty loyalty = new Loyalty();
                loyalty.setName(rankName.toString());
                loyaltyService.save(loyalty);
            }
        }
        if (roleList.isEmpty()) {
            Role roleAdmin = new Role();
            roleAdmin.setId(1L);
            roleAdmin.setName(RoleName.ROLE_ADMIN.toString());
            roleService.save(roleAdmin);
            Role roleUser = new Role();
            roleUser.setId(2L);
            roleUser.setName(RoleName.ROLE_USER.toString());
            roleService.save(roleUser);
        }
        if (users.isEmpty()) {
            User admin = new User();
            Set<Role> roles = new HashSet<>();
            roles.add(new Role(1L, RoleName.ROLE_ADMIN.toString()));
            admin.setUsername("admin1");
            admin.setEmail("admin@gmail.com");
            admin.setPassword(passwordEncoder.encode("123456"));
            admin.setRoles(roles);
            userService.save(admin);
        }
    }
}
