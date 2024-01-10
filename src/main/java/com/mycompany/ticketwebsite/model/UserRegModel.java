package com.mycompany.ticketwebsite.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserRegModel {
    private String username;
    private String password;
    private String mobile;
    private String email;
    private String confirmPassword; // 新增這個屬性
    public String getPassword() {
        return password != null ? password : "";
    }
}