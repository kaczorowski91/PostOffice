package pl.kaczorowski.postoffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kaczorowski.postoffice.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByName(String name);

    Client findByNumber(Integer number);

}
