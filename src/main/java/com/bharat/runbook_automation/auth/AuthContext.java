package com.bharat.runbook_automation.auth;

import java.util.Set;

public record AuthContext(
        boolean loggedIn,
        String username,
        Set<String> roles) {

    public boolean hasRole(String role) {
        return roles.contains(role);
    }

    public boolean hasAnyRole(String... rolesToCheck) {
        for (String role : rolesToCheck) {
            if (roles.contains(role.startsWith("ROLE_") ? role : "ROLE_" + role)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasAllRoles(String... rolesToCheck) {
        for (String role : rolesToCheck) {
            if (!roles.contains(role.startsWith("ROLE_") ? role : "ROLE_" + role)) {
                return false;
            }
        }
        return true;
    }

    public static AuthContext anonymous() {
        return new AuthContext(false, null, Set.of());
    }

    public static AuthContext of(String username, Set<String> roles) {
        return new AuthContext(true, username, roles);
    }
}