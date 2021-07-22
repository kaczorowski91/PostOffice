package pl.kaczorowski.postoffice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.transaction.Transactional;

@AllArgsConstructor
@Entity
@NoArgsConstructor
@Data
@Transactional
public class Client implements Comparable<Client> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Status status;
    private Integer pin;
    private Integer number;

    public Client(String name, Status status) {
        this.name = name;
        this.status = status;
    }

    public Client(String name, Status status, Integer pin) {
        this.name = name;
        this.status = status;
        this.pin = pin;
    }

    public Client(String name, Status status, Integer pin, Integer number) {
        this.name = name;
        this.status = status;
        this.pin = pin;
        this.number = number;
    }

    @Override
    public int compareTo(Client o) {
        if (this.getStatus().getImportance() > o.getStatus().getImportance()) return -1;
        if (this.getId() > o.getId()) return 1;
        else return 0;
    }
}
