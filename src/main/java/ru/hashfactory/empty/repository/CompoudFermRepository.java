package ru.hashfactory.empty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hashfactory.empty.domain.CompoudFerm;

import java.util.List;

@Repository("compoudfermRepository")
public interface CompoudFermRepository extends JpaRepository<CompoudFerm, Integer> {
    @Query("select cf from CompoudFerm cf where cf.ferm.id = ?1 order by cf.ord")
    List<CompoudFerm> findByFermIdOrderByOrd(int id);
    @Query("select cf from CompoudFerm cf where cf.ferm.name = ?1 order by cf.ord")
    List<CompoudFerm> findByFermNameOrderByOrd(String name);
}
