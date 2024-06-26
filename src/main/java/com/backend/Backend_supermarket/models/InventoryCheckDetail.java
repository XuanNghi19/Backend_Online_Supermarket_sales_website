package com.backend.Backend_supermarket.models;

import com.backend.Backend_supermarket.dtos.InventoryCheckDetailDTO;
import com.backend.Backend_supermarket.dtos.UpdateInventoryCheckDetailDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;

@Entity
@Table(name = "inventory_check_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryCheckDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "inventory_check_id")
    private InventoryCheck inventoryCheck;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "actual_inventory")
    private int actualInventory;

    @Column(name = "reason_missing")
    private String reasonMissing;

    @Column(name = "note")
    private String note;

    public static InventoryCheckDetail fromInventoryCheckDetailDTO(
            InventoryCheckDetailDTO inventoryCheckDetailDTO,
            InventoryCheck inventoryCheck,
            Product product
    ) {
        return InventoryCheckDetail.builder()
                .inventoryCheck(inventoryCheck)
                .product(product)
                .actualInventory(inventoryCheckDetailDTO.getActualInventory())
                .reasonMissing(inventoryCheckDetailDTO.getReasonMissing())
                .note(inventoryCheckDetailDTO.getNote())
                .build();
    }

    public static InventoryCheckDetail fromUpdateInventoryCheckDetailDTO(
            UpdateInventoryCheckDetailDTO updateInventoryCheckDetailDTO,
            InventoryCheck inventoryCheck,
            Product product
    ) {
        InventoryCheckDetail inventoryCheckDetail = new InventoryCheckDetail();
        inventoryCheckDetail.setId(updateInventoryCheckDetailDTO.getId());
        inventoryCheckDetail.setInventoryCheck(inventoryCheck);
        inventoryCheckDetail.setProduct(product);
        inventoryCheckDetail.setActualInventory(updateInventoryCheckDetailDTO.getActualInventory());
        inventoryCheckDetail.setReasonMissing(updateInventoryCheckDetailDTO.getReasonMissing());
        inventoryCheckDetail.setReasonMissing(updateInventoryCheckDetailDTO.getReasonMissing());
        inventoryCheckDetail.setNote(updateInventoryCheckDetailDTO.getNote());
        return inventoryCheckDetail;
    }
}
