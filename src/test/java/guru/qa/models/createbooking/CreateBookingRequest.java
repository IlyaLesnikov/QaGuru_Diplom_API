package guru.qa.models.createbooking;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateBookingRequest {
	private String firstname;
	private String lastname;
	@JsonProperty("totalprice")
	private Integer totalPrice;
	private Boolean depositpaid;
	@JsonProperty("bookingdates")
	private CreateBookingDatesRequest bookingdDates;
	private String additionalneeds;
}