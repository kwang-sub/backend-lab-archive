package com.example.chap11.controller;

import com.example.chap11.domain.item.Book;
import com.example.chap11.domain.item.Item;
import com.example.chap11.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/items/new")
    public String createFrom(@ModelAttribute("form") Book book) {
        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String create(Book item) {
        itemService.saveItem(item);
        return "redirect:/items";
    }

    @GetMapping("/items")
    public String list(Model model) {
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }

    @GetMapping("/items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model) {
        Item item = itemService.findOne(itemId);
        System.out.println(item);
        model.addAttribute("item", (Book)item);
        return "items/updateItemForm";
    }

    @PostMapping("/items/{itemId}/edit")
    public String updateItem(@PathVariable("itemId") Long itemId, Book item) {
        item.setId(itemId);
        System.out.println(item);
        itemService.saveItem(item);
        return "redirect:/items";
    }

}
