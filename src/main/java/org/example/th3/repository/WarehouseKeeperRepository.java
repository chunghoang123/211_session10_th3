package org.example.th3.repository;

import org.example.th3.entity.WarehouseKeeper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseKeeperRepository
        extends JpaRepository<WarehouseKeeper, Long> {
}