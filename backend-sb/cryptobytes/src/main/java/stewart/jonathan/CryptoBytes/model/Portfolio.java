package stewart.jonathan.CryptoBytes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "portfolios")
public class Portfolio{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String symbol;
    private String name;
    private Double amount;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    //@JsonIgnore
    private User user;

    public Portfolio() {}

    public Portfolio(String symbol, String name, Double amount) {
        this.symbol = symbol;
        this.name = name;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String id) {
        this.symbol = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Portfolio portfolio = (Portfolio) o;
        return symbol.equals(portfolio.symbol) && name.equals(portfolio.name) && amount.equals(portfolio.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, name, amount);
    }

    @Override
    public String toString() {
        return "Portfolio{" +
                "id='" + symbol + '\'' +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                '}';
    }
}
