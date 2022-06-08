package click.pranjalonline.bakibohibackend.persistance.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@Entity
@Table(name = "shops")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Shop{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String address;
    private String image_path;
    private String coordinates;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "shopkeeper_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User shopkeeper;


}
