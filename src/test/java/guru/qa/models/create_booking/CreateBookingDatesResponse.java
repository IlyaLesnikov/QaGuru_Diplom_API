package guru.qa.models.create_booking;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreateBookingDatesResponse {
	@JsonProperty("checkin")
	private String checkin;
	private String checkout;
}