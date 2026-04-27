package com.example.praktikum6.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@Table (name = "20230140140_users")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String Id;
    private String nama;
    private String nim;
    private String jenisKelamin;
}
