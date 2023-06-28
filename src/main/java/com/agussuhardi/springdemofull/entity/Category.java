package com.agussuhardi.springdemofull.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Table(name = "category")
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@SuperBuilder(toBuilder = true)
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "update category set is_deleted=true where id=?")
@Where(clause = "is_deleted=false")
@FieldNameConstants
public class Category extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @GeneratedValue
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "text")
    private String text;

    @Column(name = "icon")
    private String icon;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
