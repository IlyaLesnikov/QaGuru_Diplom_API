package guru.qa.models;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateTokenRequest {
    private String username;
    private String password;
}
