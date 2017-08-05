package ru.hashfactory.empty.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.hashfactory.empty.domain.CompoudFerm;
import ru.hashfactory.empty.domain.Ferm;
import ru.hashfactory.empty.domain.Message;
import ru.hashfactory.empty.domain.Profit;
import ru.hashfactory.empty.repository.CompoudFermRepository;
import ru.hashfactory.empty.repository.FermRepository;
import ru.hashfactory.empty.repository.MessageRepository;
import ru.hashfactory.empty.repository.ProfitRepository;

import java.util.List;

@Service("cabinetService")
public class CabinetServiceImpl implements CabinetService {

    @Autowired
    @Qualifier("fermRepository")
    private FermRepository fermRepository;

    @Autowired
    @Qualifier("profitRepository")
    private ProfitRepository profitRepository;

    @Autowired
    @Qualifier("compoudfermRepository")
    private CompoudFermRepository compoudFermRepository;

    @Autowired
    @Qualifier("messageRepository")
    private MessageRepository messageRepository;

    @Override
    public List<Ferm> findAllFerm() {
        return fermRepository.findAll();
    }

    @Override
    public Ferm findByName(String name) {
        return fermRepository.findByName(name);
    }

    @Override
    public Ferm findById(int id) {
        return fermRepository.findOne(id);
    }

    @Override
    public List<CompoudFerm> findByFermIdOrderByOrd(int id) {
        return compoudFermRepository.findByFermIdOrderByOrd(id);
    }

    @Override
    public List<CompoudFerm> findByFermNameOrderByOrd(String name) {
        return compoudFermRepository.findByFermNameOrderByOrd(name);
    }

    @Override
    public Profit findFirstByFermName(String name) {
        return profitRepository.findFirstByFermName(name);
    }

    @Override
    public Message findMessageById(int id) {
        return messageRepository.findOne(id);
    }

}
