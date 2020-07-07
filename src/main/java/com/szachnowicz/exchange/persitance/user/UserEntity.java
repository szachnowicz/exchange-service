package com.szachnowicz.exchange.persitance.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity(name = "user")
@NoArgsConstructor
@Getter
@Setter
class UserEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String lastName;
    private LocalDate birthDay;
    private String pesel;
}
