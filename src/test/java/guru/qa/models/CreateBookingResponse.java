package guru.qa.models;

import lombok.Data;

@Data
public class CreateBookingResponse {
	private Integer bookingid;
	private CreateBookingEntityResponse booking;
}