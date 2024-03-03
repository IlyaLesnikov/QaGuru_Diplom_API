package guru.qa.models.createbooking;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreateBookingEntityResponse {
	private String firstname;
	private String lastname;
	@JsonProperty("totalprice")
	private Integer totalPrice;
	private Boolean depositpaid;
	@JsonProperty("bookingdates")
	private CreateBookingDatesResponse bookingDates;
	private String additionalneeds;
}