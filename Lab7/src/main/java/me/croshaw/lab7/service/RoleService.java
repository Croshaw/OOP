package me.croshaw.lab7.service;

import me.croshaw.lab7.entity.Role;
import me.croshaw.lab7.exception.RoleAlreadyExistException;
import me.croshaw.lab7.exception.RoleNotFoundException;
import me.croshaw.lab7.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public ArrayList<Role> getRoles() {
        ArrayList<Role> result = new ArrayList<>();
        roleRepository.findAll().forEach(result::add);
        return result;
    }
    public Role findRoleByID(Integer id) throws RoleNotFoundException {
        Optional<Role> opt = roleRepository.findById(id);
        if (opt.isPresent())
            return opt.get();
        else
            throw new RoleNotFoundException();
    }
    public void updateRole(Role updRole, Integer id) throws RoleNotFoundException {
        var role = roleRepository.findEmployeeRoleByid(id);
        if(role == null)
            throw new RoleNotFoundException();
        if(Objects.nonNull(updRole.getName()))
            role.setName(updRole.getName());
        roleRepository.save(role);
    }
    public void addRole(Role role) throws RoleAlreadyExistException {
        if(roleRepository.findEmployeeRoleByname(role.getName()) != null) {
            throw new RoleAlreadyExistException();
        }
        roleRepository.save(role);
    }
    public void removeRoleById(Integer id) throws RoleNotFoundException {
        Role employee = roleRepository.findEmployeeRoleByid(id);
        if(employee == null) {
            throw new RoleNotFoundException();
        }
        roleRepository.delete(employee);
    }
    public void removeRole(Role role) throws RoleNotFoundException {
        if(roleRepository.findEmployeeRoleByid(role.getId()) == null) {
            throw new RoleNotFoundException();
        }
        roleRepository.delete(role);
    }
    public void deleteAllData() {
        roleRepository.deleteAll();
    }
}
