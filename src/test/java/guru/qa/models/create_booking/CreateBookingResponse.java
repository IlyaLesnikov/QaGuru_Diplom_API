package guru.qa.models.create_booking;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreateBookingResponse {
	@JsonProperty("bookingid")
	private Integer bookingId;
	private CreateBookingEntityResponse booking;
}