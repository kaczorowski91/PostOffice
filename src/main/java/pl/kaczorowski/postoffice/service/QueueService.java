package pl.kaczorowski.postoffice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import pl.kaczorowski.postoffice.entity.Client;
import pl.kaczorowski.postoffice.entity.Status;
import pl.kaczorowski.postoffice.repository.ClientRepository;

import java.util.*;

@Configuration
@EnableScheduling
public class QueueService {

    private ClientRepository clientRepository;

    @Autowired
    public QueueService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ArrayDeque<Client> createQueue() {
        List<Client> clientsList = clientRepository.findAll();
        Collections.sort(clientsList);
        ArrayDeque<Client> clientQueue = new ArrayDeque<>();
        for (int i = 0; i < clientsList.size(); i++) {
            clientQueue.offer(clientsList.get(i));
        }
        return clientQueue;
    }

    @Scheduled(fixedDelay = 20000)
    public void handlingQueue() {

        ArrayDeque<Client> clientQueue = this.createQueue();

        System.out.println(clientQueue);
        if (clientQueue.size() != 0) {
            Status status = clientQueue.peekFirst().getStatus();
            if (status.equals(Status.URGENT))
                try {
                    Thread.sleep(20000);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }

            Long id = clientQueue.peekFirst().getId();
            clientRepository.deleteById(id);
        }
    }

}

