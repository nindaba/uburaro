package bi.uburaro.core.types;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = GeneratedKey.ITEM_TYPE)
public class GeneratedKey {
    public static final String ITEM_TYPE = "generatedKey";
    public static final String VALUE = "generatedValue";

    @Id
    @GeneratedValue
    @Column(columnDefinition = "bigint DEFAULT 0")
    private Long generatedValue;
}
