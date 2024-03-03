package guru.qa.models.CreateBooking;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateBookingDatesRequest {
	private String checkin;
	private String checkout;
}