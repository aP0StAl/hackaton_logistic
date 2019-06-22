package ru.hackaton.logistic.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.hackaton.logistic.utils.RandomString;
import ru.hackaton.logistic.utils.Utils;

import javax.persistence.*;

import static ru.hackaton.logistic.utils.RandomString.randomString;

public interface CarRepository extends JpaRepository<Car, Long> {

}
