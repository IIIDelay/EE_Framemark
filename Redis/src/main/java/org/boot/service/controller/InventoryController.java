package org.boot.service.controller;

import org.boot.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * InventoryController
 *
 * @author IIIDelay
 * @createTime 2023年03月23日 20:13:00
 */
@RestController
@RequestMapping("")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @RequestMapping("inventory/sale")
    public String sale() {
        return inventoryService.sale();
    }
}
