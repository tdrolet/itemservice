package net.drolets.openscm.itemservice.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.UUID;
import javax.persistence.*;

/**
 * Created by tjd 7/27/20
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Item {

        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        @Column(length = 36, columnDefinition = "varchar", updatable = false, nullable = false)
        private UUID id;

        @Version
        private long version;

        @CreationTimestamp
        @Column(updatable = false)
        private Timestamp createdDate;

        @UpdateTimestamp
        private Timestamp lastModifiedDate;

        private String itemName;
        private String description;
}
