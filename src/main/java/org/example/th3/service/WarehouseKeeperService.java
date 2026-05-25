package org.example.th3.service;

import lombok.RequiredArgsConstructor;

import org.example.th3.dto.WarehouseKeeperRequest;
import org.example.th3.entity.WarehouseKeeper;
import org.example.th3.repository.WarehouseKeeperRepository;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class WarehouseKeeperService {

    private final WarehouseKeeperRepository repository;

    public WarehouseKeeper create(
            WarehouseKeeperRequest request
    ) {

        WarehouseKeeper keeper =
                WarehouseKeeper.builder()
                        .fullName(request.getFullName())
                        .staffCode(request.getStaffCode())
                        .build();

        return repository.save(keeper);
    }
}