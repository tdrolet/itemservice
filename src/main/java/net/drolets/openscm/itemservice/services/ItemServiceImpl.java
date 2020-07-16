package net.drolets.openscm.itemservice.services;

import lombok.extern.slf4j.Slf4j;
import net.drolets.openscm.itemservice.web.model.ItemDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by tjd 7/16/20
 */

@Slf4j
@Service
public class ItemServiceImpl implements ItemService {

    @Override
    public ItemDto saveNewItem(ItemDto itemDto){

        //todo stubbed for now
        return ItemDto.builder().id(UUID.randomUUID())
                .build();
    }

    @Override
    public void updateItem(UUID itemId, ItemDto itemDto){
        //todo stubbed for now
        log.debug("Updating a item...");

    }

    @Override
    public void deleteById(UUID itemId){
        //todo stubbed for now
        log.debug("Deleting a item...");

    }

    @Override
    public ItemDto getById(UUID itemId){

        //todo stubbed for now
        return ItemDto.builder().id(UUID.randomUUID())
                .itemName("S123456789")
                .description("Hardcoded sample item")
                .build();
    }
}
