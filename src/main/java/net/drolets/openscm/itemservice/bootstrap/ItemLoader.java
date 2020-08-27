package net.drolets.openscm.itemservice.bootstrap;

import net.drolets.openscm.itemservice.domain.Item;
import net.drolets.openscm.itemservice.repositories.ItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by tjd 7/27/20
 */

@Component
public class ItemLoader implements CommandLineRunner {

    private final ItemRepository itemRepository;


    public ItemLoader(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadItemObjects();
    }

    private void loadItemObjects(){
        itemRepository.save(Item.builder()
                .itemName("123456789")
                .description("Item 123456789")
                .build());

        itemRepository.save(Item.builder()
                .itemName("987654321")
                .description("Item 987654321")
                .build());

    }
}
