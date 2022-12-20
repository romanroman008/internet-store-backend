package pl.meating.meatingback.order;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderedProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int amount;
}
