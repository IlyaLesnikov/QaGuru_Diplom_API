package guru.qa.models.createtoken;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateTokenRequest {
    private String username;
    private String password;
}
