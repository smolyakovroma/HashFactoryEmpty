package ru.hashfactory.empty.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
        return itemsRepository.getAllByTypeItem(TypeItem.ASIC);
    }

    @Override
    public List<Item> getAllFerms() {
        return itemsRepository.getAllByTypeItem(TypeItem.FERM);
    }

    @Override

    public List<Item> getAllGPUS() {
        return itemsRepository.getAllByTypeItem(TypeItem.GPU);
    }

    @Override
    public List<Item> getAllPSUS() {
        return itemsRepository.getAllByTypeItem(TypeItem.PSU);
    }

    @Override
    public List<Item> getAllOthers() {
        return itemsRepository.getAllByTypeItem(TypeItem.OTHER);
    }

    @Override
    public Item save(Item item) {
       return itemsRepository.save(item);
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
