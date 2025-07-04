package com.example.demo.controller.request;

import com.example.demo.entity.Account;
import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequest {
    String name;
    String password;

    public Account toAccount() { return new Account(name, password); }
}
