package ru.hashfactory.empty.service;

import ru.hashfactory.empty.domain.Item;
import ru.hashfactory.empty.domain.TypeItem;

import java.lang.reflect.Type;
import java.util.List;

public interface ShopService {

    List<Item> getAllAsics();
    List<Item> getAllGPU();
    List<Item> findAll(TypeItem typeItem);
    Item findById(int id);
}
