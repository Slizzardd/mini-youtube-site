package ua.com.alevel.web.dto.response;

import ua.com.alevel.persistence.entity.channel.User;
import ua.com.alevel.persistence.type.Role;
import ua.com.alevel.persistence.type.Status;

public class UserResponseDto extends ResponseDto{
    private String email;
    private String login;
    private Status status;
    private Role role;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String country;

    public UserResponseDto(User user) {
        setId(user.getId());
        setCreated(user.getCreated());
        setUpdated(user.getUpdated());
        setVisible(user.getVisible());
        this.email = user.getEmail();
        this.status = user.getStatus();
        this.role = user.getRole();
        this.login = user.getLogin();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
