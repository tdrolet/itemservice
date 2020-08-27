package net.drolets.openscm.itemservice.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import java.util.UUID;

/**
 * Created by tjd 7/15/20
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDto {

    @Null
    private UUID id;

    @NotBlank
    @Size(min = 3, max = 100)
    private String itemName;

    @NotBlank
    @Size(min = 3, max = 100)
    private String description;
}
