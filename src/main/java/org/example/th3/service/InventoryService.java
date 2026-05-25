package org.example.th3.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.th3.dto.ExportRequest;
import org.example.th3.dto.ImportRequest;
import org.example.th3.entity.Product;
import org.example.th3.repository.ProductRepository;
import org.example.th3.repository.WarehouseKeeperRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class InventoryService {

    private final ProductRepository productRepository;
    private final WarehouseKeeperRepository keeperRepository;

    public void importProduct(
            ImportRequest request
    ) {

        keeperRepository.findById(
                request.getKeeperId()
        ).orElseThrow(() -> {
            log.warn("Không tìm thấy nhân viên kho");
            return new RuntimeException();
        });

        Product product =
                productRepository.findBySku(
                        request.getSku()
                ).orElseThrow(() -> {
                    log.warn("SKU không tồn tại");
                    return new RuntimeException();
                });

        productRepository.importProduct(
                product.getSku(),
                request.getQuantity()
        );

        log.info(
                "Nhập kho SKU={} số lượng={}",
                request.getSku(),
                request.getQuantity()
        );
    }

    public void exportProduct(
            ExportRequest request
    ) {

        keeperRepository.findById(
                request.getKeeperId()
        ).orElseThrow(() -> {
            log.warn("Không tìm thấy nhân viên kho");
            return new RuntimeException();
        });

        Product product =
                productRepository.findBySku(
                        request.getSku()
                ).orElseThrow(() -> {
                    log.warn("SKU không tồn tại");
                    return new RuntimeException();
                });

        if(product.getQuantity()
                < request.getQuantity()) {

            log.warn(
                    "Xuất kho vượt tồn kho"
            );

            throw new IllegalArgumentException(
                    "Số lượng xuất hàng vượt quá tồn kho hiện tại!"
            );
        }

        productRepository.exportProduct(
                product.getSku(),
                request.getQuantity()
        );

        log.info(
                "Xuất kho SKU={} số lượng={}",
                request.getSku(),
                request.getQuantity()
        );
    }

    public List<Product> lowStock() {

        return productRepository
                .findByQuantityLessThan(5L);
    }
}