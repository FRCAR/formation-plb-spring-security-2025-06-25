package fr.maboite.correction.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.maboite.correction.model.BookingCorrection;
import fr.maboite.correction.service.BookingServiceCorrection;

@RestController
public class BookingControllerCorrection {

	@Autowired
	private BookingServiceCorrection bookingService;

	@GetMapping(path = "/rest/bookings")
	public Iterable<BookingCorrection> findAll() {
		return this.bookingService.findAll();
	}

	@PostMapping(path = "/rest/bookings", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public BookingCorrection save(@RequestBody BookingCorrection booking) {
		return this.bookingService.save(booking);
	}

}
