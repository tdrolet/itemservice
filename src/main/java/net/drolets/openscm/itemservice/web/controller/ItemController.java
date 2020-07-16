package net.drolets.openscm.itemservice.web.controller;

import net.drolets.openscm.itemservice.services.ItemService;
import net.drolets.openscm.itemservice.web.model.ItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Created by tjd 7/15/20
 */

@RequestMapping("/api/v1/item")
@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;
/*
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    } */

    @GetMapping("/{itemId}")
    public ResponseEntity<ItemDto> getItemById(@PathVariable("itemId") UUID itemId){
        //todo impl
        return new ResponseEntity<>(itemService.getById(itemId), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<ItemDto> handlePost(@RequestBody ItemDto itemDto){

        ItemDto savedDto = itemService.saveNewItem(itemDto);

        HttpHeaders headers = new HttpHeaders();
        //todo add hostname to url
        headers.add("Location", "/api/v1/item/" + savedDto.getId().toString());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping({"/{itemId}"})
    public ResponseEntity<ItemDto> handleUpdate(@PathVariable("itemId") UUID itemId, @RequestBody ItemDto itemDto){

        itemService.updateItem(itemId, itemDto);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping({"/{itemId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteItem(@PathVariable("itemId") UUID itemId){
        itemService.deleteById(itemId);
    }

}
