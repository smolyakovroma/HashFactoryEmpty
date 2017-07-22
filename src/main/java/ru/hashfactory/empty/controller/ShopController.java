package ru.hashfactory.empty.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.hashfactory.empty.domain.Item;
import ru.hashfactory.empty.domain.TypeItem;
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
        modelAndView.addObject("asics", asics);
        return modelAndView;
    }

    @RequestMapping(value = "/image/{image_id}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable("image_id") int imageId) throws IOException {

        byte[] imageContent = shopService.findById(imageId).getPic();
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<byte[]>(imageContent, headers, HttpStatus.OK);
    }
}
