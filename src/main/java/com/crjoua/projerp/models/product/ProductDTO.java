package com.crjoua.projerp.models.product;

import java.util.Objects;

public record ProductDTO(Long id, String name, String description) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        ProductDTO that = (ProductDTO) o;
        return Objects.equals(this.id, that.id) && Objects.equals(this.name, that.name) && Objects.equals(this.description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.description);
    }
}