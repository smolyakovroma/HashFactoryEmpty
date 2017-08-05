package ru.hashfactory.empty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hashfactory.empty.domain.CompoudFerm;
import ru.hashfactory.empty.domain.Message;

@Repository("messageRepository")
public interface MessageRepository extends JpaRepository<Message, Integer> {
}
