package me.croshaw.lab7.repository;

import me.croshaw.lab7.entity.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findEmployeeRoleByid(Integer id);
    Role findEmployeeRoleByname(String name);
}
