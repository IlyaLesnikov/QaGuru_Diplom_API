package guru.qa.models;

import lombok.Data;

@Data
public class CreateBookingEntityResponse {
	private String firstname;
	private String lastname;
	private Integer totalprice;
	private Boolean depositpaid;
	private CreateBookingDatesResponce bookingdates;
	private String additionalneeds;
}