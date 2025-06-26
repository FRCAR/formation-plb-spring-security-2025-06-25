package fr.maboite.correction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.maboite.correction.dao.BookingDaoCorrection;
import fr.maboite.correction.model.BookingCorrection;

@Service
public class BookingServiceCorrection {

	@Autowired
	private BookingDaoCorrection bookingDao;

	public Iterable<BookingCorrection> findAll(){
		return this.bookingDao.findAll();
	}
	
	public BookingCorrection save(BookingCorrection booking) {
		return this.bookingDao.save(booking);
	}

}
