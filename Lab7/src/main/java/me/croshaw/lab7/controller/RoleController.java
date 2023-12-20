package me.croshaw.lab7.controller;

import me.croshaw.lab7.entity.Role;
import me.croshaw.lab7.exception.RoleAlreadyExistException;
import me.croshaw.lab7.exception.RoleNotFoundException;
import me.croshaw.lab7.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee/roles")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/")
    public ResponseEntity addRole(@RequestBody Role role) {
        try {
            roleService.addRole(role);
            return ResponseEntity.ok("Success");
        }
        catch (RoleAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity getRole(@PathVariable("id") Integer id) {
        try {
            return ResponseEntity.ok(roleService.findRoleByID(id));
        }
        catch (RoleNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
    @GetMapping("/")
    public ResponseEntity getRoles() {
        try {
            return ResponseEntity.ok(roleService.getRoles());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity updateRole(@RequestBody Role role, @PathVariable("id") Integer roleId) {
        try {
            roleService.updateRole(role, roleId);
            return ResponseEntity.ok("Success");
        }
        catch (RoleNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteRole(@PathVariable("id") Integer id) {
        try {
            roleService.removeRoleById(id);
            return ResponseEntity.ok("Success");
        }
        catch (RoleNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
}
