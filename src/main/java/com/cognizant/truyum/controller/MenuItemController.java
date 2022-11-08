package com.cognizant.truyum.controller;

import java.nio.file.FileSystemException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.service.MenuItemService;

@Controller
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemDao;
    

    private static final Logger LOGGER = LoggerFactory.getLogger(MenuItemController.class);

    @GetMapping(value = "/show-menu-list-admin")
    public String showMenuItemListAdmin(ModelMap model) throws FileSystemException {
        LOGGER.info("Start - showMenuItemListAdmin");
        model.addAttribute("menuItemListAdmin", menuItemDao.getMenuItemListAdmin());
        LOGGER.info("End - showMenuItemListAdmin");
        return "menu-item-list-admin";
    }

    @GetMapping(value = "/show-menu-list-customer")
    public String showMenuItemListCustomer(ModelMap model) throws FileSystemException {
        LOGGER.info("Start - showMenuItemListCustomer");
        List<MenuItem> menuItemListAdmin = menuItemDao.getMenuItemListCustomer();
        Collections.sort(menuItemListAdmin);
        model.addAttribute("menuItemListCustomer", menuItemListAdmin );
        LOGGER.info("End - showMenuItemListCustomer");

        return "menu-item-list-customer";
    }

    @GetMapping(value = "/show-edit-menu-item")
    public String showEditMenuItem(@RequestParam long menuItemId, ModelMap model) {
        LOGGER.info("Start - showEditMenuItem");
        MenuItem item = menuItemDao.getMenuItem(menuItemId);
        
        List<String> categories = Arrays.asList(new String[]{"Starters", "Main Course", "Desert", "Drinks"});

        model.addAttribute("menuItem", item);
        model.addAttribute("catogoryList", categories);
        
        LOGGER.info("End - showEditMenuItem");
        return "edit-menu-item";
    }
    
    @PostMapping(value = "edit-menu-item")
    public String showEditMenuStatus(@ModelAttribute("menuItem") @Valid MenuItem menuItem, BindingResult result) {
        
        if(result.hasErrors()) {
            return "edit-menu-item";
        }
        
        menuItemDao.editMenuItem(menuItem);
        return "edit-menu-item-status";
    }
    

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
    

}
