package ru.hashfactory.empty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hashfactory.empty.domain.CompoudFerm;
import ru.hashfactory.empty.domain.Profit;

@Repository("profitRepository")
public interface ProfitRepository extends JpaRepository<Profit, Integer> {

    Profit findFirstByFermName(String name);
}
