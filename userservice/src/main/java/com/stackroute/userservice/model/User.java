package com.stackroute.userservice.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Class representing a user tracked by the application.")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "ID of the user", position = 0)
    private int id;

    @NotBlank(message = "Name cannot be empty")
    @ApiModelProperty(notes = "Name of the user", example = "Justin Trudeau", required = true, position = 1)
    private String name;

    @Email(message = "Please provide a valid email address")
    @ApiModelProperty(notes = "Email of the user", example = "justin.trudeau@cgi.com", required = true, position = 2)
    private String email;

    @ApiModelProperty(notes = "Admin boolean value is true if user is an admin. Default: false", example = "true", position = 3)
    private boolean admin = false;

    @Valid
    @Embedded
    private Login loginInfo;

    @Valid
    @Embedded
    private UserDetails userDetails;
}