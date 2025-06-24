package fr.maboite.secureapp.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.maboite.secureapp.model.Booking;

@Repository
public interface BookingDao extends CrudRepository<Booking, Long> {

}
