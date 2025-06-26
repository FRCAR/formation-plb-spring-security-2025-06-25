package fr.maboite.correction.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.maboite.correction.model.BookingCorrection;

@Repository
public interface BookingDaoCorrection extends CrudRepository<BookingCorrection, Long> {

}
