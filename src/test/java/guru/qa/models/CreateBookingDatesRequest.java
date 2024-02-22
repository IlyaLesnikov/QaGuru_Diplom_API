package guru.qa.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateBookingDatesRequest {
	private String checkin;
	private String checkout;
}