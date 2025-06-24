package fr.maboite.secureapp.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.maboite.secureapp.model.Booking;
import fr.maboite.secureapp.service.BookingService;

@RestController
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@GetMapping(path = "/rest/bookings")
	public Iterable<Booking> findAll() {
		return this.bookingService.findAll();
	}

	@PostMapping(path = "/rest/bookings", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Booking save(@RequestBody Booking booking) {
		return this.bookingService.save(booking);
	}

}
