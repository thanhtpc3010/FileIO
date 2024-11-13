package Entity;

import java.time.LocalDateTime;

public class Order {
    private int id;
    private Customer customer;
    private LocalDateTime dateTime;

    public Order(int id, Customer customer, LocalDateTime dateTime) {
        this.id = id;
        this.customer = customer;
        this.dateTime = dateTime;
    }

                public Order(int id, int i, String s) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
