package pl.meating.meatingback.user;




import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.meating.meatingback.user.userdetails.UserDetails;

import javax.persistence.*;


@Entity
@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    @OneToOne(cascade = CascadeType.ALL)
    private UserDetails userDetails;
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name="authority_id")
//    private Authority authority;

}
