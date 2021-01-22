package com.englishapp.demoen.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Component
public class User  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(message = "Invalid Email")
    @NotBlank(message = "Email is required")
    @Column(unique = true)
    private String username;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;


    @NotBlank(message = "Password is required")
    private String password;

    @Transient
//    @NotBlank(message = "Confirm password is required")
    private String confirmPassword;


    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;


    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name="favorite_user_bucket_folder",
    joinColumns = {@JoinColumn(name="user_id", referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name="bucket_folder_id", referencedColumnName = "id")})
//    @JsonBackReference(value = "BucketFolder-User")
    private Set<BucketFolder> fvBucketFolder = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "user")
//    @JsonManagedReference(value = "UserPageInfo-User")
    @JsonBackReference(value="UserPageInfo-User")
    private Set<UserPageInfo> userPageInfo = new HashSet<>();

    public User(Long id, @Email(message = "Invalid Email") @NotBlank(message = "Email is required") String username, @NotBlank(message = "First name is required") String firstName, @NotBlank(message = "Last name is required") String lastName, @NotBlank(message = "Password is required") String password, String confirmPassword, Status status, List<Role> roles, Set<BucketFolder> fvBucketFolder, Set<UserPageInfo> userPageInfo) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.status = status;
        this.roles = roles;
        this.fvBucketFolder = fvBucketFolder;
        this.userPageInfo = userPageInfo;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Set<BucketFolder> getFvBucketFolder() {
        return fvBucketFolder;
    }

    public void setFvBucketFolder(Set<BucketFolder> fvBucketFolder) {
        this.fvBucketFolder = fvBucketFolder;
    }

    public Set<UserPageInfo> getUserPageInfo() {
        return userPageInfo;
    }

    public void setUserPageInfo(Set<UserPageInfo> userPageInfo) {
        this.userPageInfo = userPageInfo;
    }
}
