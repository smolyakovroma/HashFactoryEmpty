package ru.hashfactory.empty.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.hashfactory.empty.domain.Item;
import ru.hashfactory.empty.service.ShopService;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping
public class ShopController {

    @Autowired
    private ShopService shopService;

    @RequestMapping(value = "/shop")
    public ModelAndView shop() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("shop");
        List<Item> asics = shopService.getAllAsics();
        List<Item> ferms = shopService.getAllFerms();
        List<Item> gpus = shopService.getAllGPUS();
        List<Item> psus = shopService.getAllPSUS();
        List<Item> others = shopService.getAllOthers();

        modelAndView.addObject("asics", asics);
        modelAndView.addObject("ferms", ferms);
        modelAndView.addObject("gpus", gpus);
        modelAndView.addObject("psus", psus);
        modelAndView.addObject("others", others);
        return modelAndView;
    }

    @RequestMapping(value = "/shop/{id}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Item> getItemById(@PathVariable int id) {
        Item item = shopService.findById(id);
        return new ResponseEntity<Item>(item,  HttpStatus.OK);

    }

    @RequestMapping(value = "/image/{image_id}/{number}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable("image_id") int imageId, @PathVariable("number") int number) throws IOException {
        byte[] imageContent = null;
        switch (number) {
            case 0:
                imageContent = shopService.findById(imageId).getPic();
                break;
            case 1:
                imageContent = shopService.findById(imageId).getPic1();
                break;
            case 2:
                imageContent = shopService.findById(imageId).getPic2();
                break;
            case 3:
                imageContent = shopService.findById(imageId).getPic3();
                break;
            case 4:
                imageContent = shopService.findById(imageId).getPic4();
                break;
            default:
                imageContent = shopService.findById(imageId).getPic();
                break;
        }

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<byte[]>(imageContent, headers, HttpStatus.OK);
    }
}
