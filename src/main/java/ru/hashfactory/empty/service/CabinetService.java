package ru.hashfactory.empty.service;

import ru.hashfactory.empty.domain.CompoudFerm;
import ru.hashfactory.empty.domain.Ferm;
import ru.hashfactory.empty.domain.Message;

import java.util.List;

public interface CabinetService {
    List<Ferm> findAllFerm();
    Ferm findByName(String name);
    Ferm findById(int id);
    List<CompoudFerm> findByFermIdOrderByOrd(int id);
    List<CompoudFerm> findByFermNameOrderByOrd(String name);
    Message findMessageById(int id);
}
