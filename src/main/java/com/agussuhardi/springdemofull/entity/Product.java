package com.agussuhardi.springdemofull.entity;

import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;

@Table(name = "product")
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@SuperBuilder(toBuilder = true)
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "update product set is_deleted=true where id=?")
@Where(clause = "is_deleted=false")
@FieldNameConstants
public class Product extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    Long qty;
    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @GeneratedValue
    private String id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "text")
    private String text;
    @Column(name = "image")
    private String image;
    @NotFound(action = NotFoundAction.IGNORE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @FieldNameConstants.Exclude
    @ManyToOne(targetEntity = Category.class)
    @JoinColumn(name = "category_id")
    private Category category;
}
