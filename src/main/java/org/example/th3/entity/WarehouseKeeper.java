package org.example.th3.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "warehouse_keepers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WarehouseKeeper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Column(unique = true)
    private String staffCode;
}