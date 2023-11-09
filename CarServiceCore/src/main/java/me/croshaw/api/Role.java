package me.croshaw.api;

import java.util.Arrays;

public enum Role {
    Auto_Mechanic(new Permission[]{Permission.EditRequest}),
    Manager(new Permission[]{Permission.EditRequest, Permission.CreateRequest}),
    Secretary(new Permission[]{Permission.EditRequest, Permission.CreateRequest}),
    Director(null),
    Client(null);

    private final Permission[] permissions;
    Role(Permission[] permissions) {
        this.permissions = permissions;
    }
    public boolean hasPermission(Permission permission) {
        if(permission == null)
            return false;
        return Arrays.stream(permissions).anyMatch(x -> x == permission);
    }
    public Permission[] getPermissions() {
        return permissions;
    }
}
