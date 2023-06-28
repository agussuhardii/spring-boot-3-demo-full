package com.agussuhardi.springdemofull.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author agus.suhardii@gmail.com
 * @created 14/06/23/06/2023 :21.16
 * @project spring-demo-full
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EntityListeners(AuditingEntityListener.class)
@SuperBuilder(toBuilder = true)
public abstract class BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "updated_at")
    protected Long updatedAt;
    @Column(name = "created_by", columnDefinition = "varchar(50)")
    @CreatedBy
    protected String createdBy;
    @Column(name = "modified_by", columnDefinition = "varchar(50)")
    @LastModifiedBy
    protected String modifiedBy;
    @Column(name = "created_at", nullable = false, updatable = false)
    protected Long createdAt;
    @Column(name = "is_deleted", nullable = false)
    private Boolean deleted;


    @PrePersist
    public void prePersist() {
        if (createdAt == null) this.createdAt = System.currentTimeMillis();
        this.deleted=false;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = System.currentTimeMillis();
    }
}