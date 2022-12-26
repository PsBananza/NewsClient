package com.dmitry.NewsClient.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "mr_shop")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Shop {

    @Id
    @GeneratedValue
    @NotNull
    private UUID uuid;

    @Column(name = "image")
    private String image;

    @Column(name = "item_name")
    private String itemName;

    private String description;

    @Column(name = "used_count")
    private String usedCount;

    @Column(name = "total_count")
    private String totalCount;

    private double price;

    private String pageUrl;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "shopUuid")
    private Set<Codes> codes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Shop shop = (Shop) o;
        return uuid != null && Objects.equals(uuid, shop.uuid);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "uuid = " + uuid + ", " +
                "image = " + image + ", " +
                "name = " + itemName + ", " +
                "description = " + description + ", " +
                "price = " + price + ", " +
                "pageUrl = " + pageUrl + ")";
    }
}
