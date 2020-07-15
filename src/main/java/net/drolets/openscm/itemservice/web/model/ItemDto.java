package net.drolets.openscm.itemservice.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Created by tjd 7/15/20
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDto {
    private UUID id;
    private String itemName;
    private String description;
}
