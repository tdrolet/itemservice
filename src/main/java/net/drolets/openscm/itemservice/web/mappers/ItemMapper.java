package net.drolets.openscm.itemservice.web.mappers;

import org.mapstruct.Mapper;
import net.drolets.openscm.itemservice.domain.Item;
import net.drolets.openscm.itemservice.web.model.ItemDto;

/**
 * Created by tjd 8/26/20
 */


@Mapper(uses = {DateMapper.class})
public interface ItemMapper {

    ItemDto ItemToItemDto(Item item);

    Item ItemDtoToItem(ItemDto dto);
}
