package ru.hackaton.logistic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hackaton.logistic.domain.Usr;

public interface UsrRepository extends JpaRepository<Usr,Long> {
    Usr findTopByUsernameAndPassword(String user, String password);
}
