package ee.estonia.paymentapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Amount should not be empty")
    @Positive(message = "Amount should be positive")
    private Double amount;
    @JsonIgnore
    @CreationTimestamp
    private LocalDateTime issueDate;
    @JsonIgnore
    private LocalDateTime cancelTime;
    @NotNull(message = "debtorIBAN should not be empty")
    private String debtorIBAN;
    @NotNull(message = "creditorIBAN should not be empty")
    private String creditorIBAN;
    @Pattern(regexp = "EURO|USD|euro|usd",message = "Currency is not correct")
    private String currency;
    private String type;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("biccode")
    private String BICCode;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String details;

}
