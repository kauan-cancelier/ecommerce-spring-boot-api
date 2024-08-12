package ecommerce.models;

import ecommerce.models.enums.OrderStatus;
import ecommerce.models.enums.SendType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "number", nullable = false, unique = true)
    private String number;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "shopping_cart_id", nullable = false, unique = true)
    private ShoppingCart shoppingCart;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "send_type")
    private SendType sendType;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address shippingAddress;

    @Column(name = "tracking_number", nullable = false)
    private String trackingNumber;

    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate;

    @Column(name = "observations")
    private String observations;

    @Column(name = "discount")
    private BigDecimal discount;

}
