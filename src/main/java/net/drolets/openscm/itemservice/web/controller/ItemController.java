package net.drolets.openscm.itemservice.web.controller;

import net.drolets.openscm.itemservice.web.model.ItemDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Created by tjd 7/15/20
 */

@RequestMapping("/api/v1/item")
@RestController
public class ItemController {

    @GetMapping("/{itemId}")
    public ResponseEntity<ItemDto> getItemById(@PathVariable("itemId") UUID itemId){
        //todo impl
        return new ResponseEntity<>(ItemDto.builder().build(), HttpStatus.OK);
    }

}
