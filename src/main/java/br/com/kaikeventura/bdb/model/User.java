package br.com.kaikeventura.bdb.model;

import br.com.kaikeventura.bdb.aux.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class User extends AbstractModel implements UserDetails, Serializable {

    private static final long serialVersionUID = -5099374715924902675L;

    @JsonProperty("name")
    @Field(name = "name")
    private String name;

    @JsonProperty("last_name")
    @Field(name = "last_name")
    private String lastName;

    @JsonProperty("date_of_birth")
    @Field(name = "date_of_birth")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateOfBirth;

    @JsonProperty("email")
    @Field(name = "email")
    private String email;

    @JsonIgnore
    @Field(name = "password")
    private String password;

    @JsonProperty("roles")
    @Field(name = "roles")
    private List<Role> roles;

    @JsonProperty("active")
    @Field(name = "active")
    private Boolean active;

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return this.active;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(
                authority -> new SimpleGrantedAuthority(authority.name())).collect(Collectors.toList()
        );
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return this.password;
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return this.email;
    }

}
