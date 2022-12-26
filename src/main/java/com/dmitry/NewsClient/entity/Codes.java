package com.dmitry.NewsClient.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(name = "mr_codes_for_item")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Codes {
    @Id
    @GeneratedValue
    @NotNull
    private UUID uuid;

    @Column(name = "promocodes")
    private String promocodes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_uuid", insertable = false, updatable = false)
    private Shop shopUuid;
}
