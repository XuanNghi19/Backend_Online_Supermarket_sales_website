package com.backend.Backend_supermarket.responses;

import java.util.Date;

import com.backend.Backend_supermarket.enums.Role;
import com.backend.Backend_supermarket.models.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("email")
    private String email;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("address")
    private String address;

    @JsonProperty("date_of_birth")
    private Date dateOfBirth;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("avatar")
    private String avatar;

    @JsonProperty("role")
    private Role role;

    @JsonProperty("active")
    private Boolean active;

    // cho admin
    public static UserResponse fromUser(User user) {
        String avatar = user.getAvatar() != null ? "https://objectstorage.ap-singapore-1.oraclecloud.com/n/axegw7pib4cf/b/PXN_img/o/Products/" + (user.getAvatar()).replaceAll(" ", "%20") : "";

        return UserResponse.builder()
            .id(user.getId())
            .email(user.getEmail())
            .phoneNumber(user.getPhoneNumber())
            .address(user.getAddress())
            .dateOfBirth(user.getDateOfBirth())
            .fullName(user.getFullName())
            .active(user.getActive())
            .role(user.getRole())
            .avatar(avatar)
            .build();
    }

}
