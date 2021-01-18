package nl.gettoworktogether.demo_spring_jpa.repository;

import nl.gettoworktogether.demo_spring_jpa.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByLastNameIgnoreCase(String lastName);
    Client findByClientNr(long clientNr);

    @Query("SELECT * FROM Client c WHERE c.client_nr = %:nr%")
    List<Client> searchByClientNr(@Param("nr") long nr);

}
