package ru.hashfactory.empty.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import ru.hashfactory.empty.domain.Item;
import ru.hashfactory.empty.domain.TypeItem;
import ru.hashfactory.empty.repository.ItemsRepository;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    @Qualifier("itemsRepository")
    private ItemsRepository itemsRepository;


    @Override
    public List<Item> getAllAsics() {
        return itemsRepository.getAllAsics(TypeItem.ASIC);
    }

    @Override

    public List<Item> getAllGPU() {
        return itemsRepository.getAllGPU();
    }

    @Override
    public List<Item> findAll(TypeItem typeItem) {
        return null;
    }


    @Override
    public Item findById(int id) {
        return itemsRepository.findById(id);
    }
}
