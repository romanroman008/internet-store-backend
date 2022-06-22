package pl.meating.meatingback.user.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class JwtResponse {
    private String token;
    private String type="Bearer";
    private Long id;
    private String login;
    private String email;
    private List<String> roles;

    public JwtResponse(String token, Long id, String login, String email, List<String> roles) {
        this.token = token;
        this.id = id;
        this.login = login;
        this.email = email;
        this.roles = roles;
    }
}
