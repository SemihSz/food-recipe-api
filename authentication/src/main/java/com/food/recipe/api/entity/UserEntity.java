package com.food.recipe.api.entity;

import com.food.recipe.api.entity.audit.DateAudit;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Semih, 2.07.2023
 */

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserEntity extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String username;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @NotBlank
    private String password;

    @NaturalId
    @NotBlank
    @Size(max = 80)
    @Email
    private String email;

    private boolean isPrivate;

    private String url;

    private String bioHeader;

    private String bioDesc;

    private String gender;

    private String phone;

    private String profileImageId;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
      joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles = new HashSet<>();

}
