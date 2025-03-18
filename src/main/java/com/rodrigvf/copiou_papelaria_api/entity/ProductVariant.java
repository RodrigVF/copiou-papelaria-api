package com.rodrigvf.copiou_papelaria_api.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class ProductVariant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_variant_id")
    private Long id;

    private Long barcode;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", foreignKey = @ForeignKey(name = "fk_product_variant_product"), nullable = true)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "color_id", referencedColumnName = "color_id", foreignKey = @ForeignKey(name = "fk_product_variant_color"), nullable = true)
    private Color color;

    @ManyToOne
    @JoinColumn(name = "size_id", referencedColumnName = "size_id", foreignKey = @ForeignKey(name = "fk_product_variant_size"), nullable = true)
    private Size size;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

}
