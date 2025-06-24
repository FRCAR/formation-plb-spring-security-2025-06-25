package fr.maboite.secureapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.maboite.secureapp.dao.BookingDao;
import fr.maboite.secureapp.model.Booking;

@Service
public class BookingService {

	@Autowired
	private BookingDao bookingDao;

	public Iterable<Booking> findAll(){
		return this.bookingDao.findAll();
	}
	
	public Booking save(Booking booking) {
		return this.bookingDao.save(booking);
	}

}
