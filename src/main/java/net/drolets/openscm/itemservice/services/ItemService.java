package net.drolets.openscm.itemservice.services;

import net.drolets.openscm.itemservice.web.model.ItemDto;

import java.util.UUID;

/**
 * Created by tjd 7/16/20
 */

@SuppressWarnings("unused")
public interface ItemService {

    ItemDto saveNewItem(ItemDto itemDto);

    void updateItem(UUID itemId, ItemDto itemDto);

    void deleteById(UUID itemId);

    ItemDto getById(UUID itemId);

    ItemDto[] getItems(int limit, int offset);
}
