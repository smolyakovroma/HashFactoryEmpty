package ru.hashfactory.empty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hashfactory.empty.domain.Item;
import ru.hashfactory.empty.domain.TypeItem;

import java.util.List;

@Repository("itemsRepository")
public interface ItemsRepository extends JpaRepository<Item, Integer> {

    Item findById(int id);

    @Query("select i from Item i where i.typeItem = ?1 and i.active>0 order by i.prioritet desc ")
    List<Item> getAllByTypeItem(TypeItem typeItem);

    byte[] getPicById(int id);

}
