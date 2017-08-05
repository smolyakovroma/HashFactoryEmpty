package ru.hashfactory.empty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hashfactory.empty.domain.Ferm;
import ru.hashfactory.empty.domain.Item;

@Repository("fermRepository")
public interface FermRepository  extends JpaRepository<Ferm, Integer> {

    Ferm findByName(String name);
}
