package net.drolets.openscm.itemservice.repositories;

import net.drolets.openscm.itemservice.domain.Item;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by tjd 7/27/20
 */

@Component
public interface ItemRepository extends PagingAndSortingRepository <Item, UUID>{

}
