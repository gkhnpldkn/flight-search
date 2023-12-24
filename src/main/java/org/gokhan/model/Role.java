package org.gokhan.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "roles")

public class Role implements GrantedAuthority {

    @Id
    private String role;

    @Override
    public String getAuthority() {
        return role;
    }
}
