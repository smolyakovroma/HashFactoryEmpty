package ru.hashfactory.empty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hashfactory.empty.domain.Ferm;
import ru.hashfactory.empty.domain.Item;

import java.util.List;

@Repository("fermRepository")
public interface FermRepository  extends JpaRepository<Ferm, Integer> {

    List<Ferm> findByActive(int active);
    Ferm findByName(String name);
}
