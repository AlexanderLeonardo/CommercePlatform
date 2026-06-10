package commercePlatform.orderService.domain.PaymentStrategy;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

import java.math.BigDecimal;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Payment {

    @Id
    private Long id;

    public abstract BigDecimal applyDiscount(BigDecimal total);
}
