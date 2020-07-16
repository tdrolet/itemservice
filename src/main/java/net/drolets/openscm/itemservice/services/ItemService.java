package net.drolets.openscm.itemservice.services;

import net.drolets.openscm.itemservice.web.model.ItemDto;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

/**
 * Created by tjd 7/16/20
 */

public interface ItemService {


    public ItemDto saveNewItem(ItemDto itemDto);

    public void updateItem(UUID itemId, ItemDto itemDto);

    public void deleteById(UUID itemId);

    public ItemDto getById(UUID itemId);

}
