package org.example.ch11.versionall;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

@Entity
@OptimisticLocking(type = OptimisticLockType.ALL)
@DynamicUpdate
@Getter
@Setter
@Table(name = "v_item")
public class VersionalItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
