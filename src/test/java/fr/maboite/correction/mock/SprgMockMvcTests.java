package fr.maboite.correction.mock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@SpringBootTest
class SprgMockMvcTests {

	@Autowired
	private MockMvc mvc;

	@Test
	@WithMockUser(value = "spring", authorities = {"SCOPE_READ_BOOKING","TOTO"})
	public void getBookingsWithScopeReadBooking() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/rest/bookings")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	@WithMockUser(value = "spring", authorities = {"TOTO"})
	public void getBookingsWithToto() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/rest/bookings")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isForbidden());
	}

	@Test
	@WithMockUser(value = "spring", authorities = {"SCOPE_CREATE_BOOKING"})
	public void postBookingsWithToto() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/rest/bookings")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{}")
				//.header("Authorization", "Bearer coucou")
				//.with(SecurityMockMvcRequestPostProcessors.csrf())
				//.with(SecurityMockMvcRequestPostProcessors.jwt().authorities(new SimpleGrantedAuthority("SCOPE_CREATE_BOOKING")))
				)
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

}