package pl.kaczorowski.postoffice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.kaczorowski.postoffice.entity.Client;
import pl.kaczorowski.postoffice.entity.Status;
import pl.kaczorowski.postoffice.repository.ClientRepository;

@Service
public class CreateDataService {

    private ClientRepository clientRepository;
    private ClientService clientService;

    @Autowired
    public CreateDataService(ClientRepository clientRepository, ClientService clientService) {
        this.clientRepository = clientRepository;
        this.clientService = clientService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void addData() {
        clientRepository.save(new Client("Mateusz(Regular)", Status.REGULAR, null, 2));
        clientRepository.save(new Client("Asia(VIP)", Status.VIP, 8888, 3));
        clientRepository.save(new Client("Przemek(Regular)", Status.REGULAR, null, 5));
        clientRepository.save(new Client("Mike(URGENT)", Status.URGENT, 0000, 4));
        clientRepository.save(new Client("SZYMON(URGENT)", Status.URGENT, 0000, 6));
        clientRepository.save(new Client("Marzena(VIP)", Status.VIP, 8888, 1));

    }
}
