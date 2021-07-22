package pl.kaczorowski.postoffice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kaczorowski.postoffice.dto.ClientDto;
import pl.kaczorowski.postoffice.entity.Client;
import pl.kaczorowski.postoffice.entity.Status;
import pl.kaczorowski.postoffice.exception.EntityAlreadyExistsException;
import pl.kaczorowski.postoffice.exception.ExceptionType;
import pl.kaczorowski.postoffice.exception.PinIsNotCorrectException;
import pl.kaczorowski.postoffice.mapper.ClientMapper;
import pl.kaczorowski.postoffice.repository.ClientRepository;

import java.util.ArrayDeque;
import java.util.Random;

@Service
public class ClientService {

    private ClientRepository clientRepository;
    private ClientMapper clientMapper;
    private QueueService queueService;

    @Autowired
    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper, QueueService queueService) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
        this.queueService = queueService;
    }

    public ArrayDeque<ClientDto> getClients() {
        return clientMapper.mapToClientDtoList(queueService.createQueue());
    }

    public String getClientPlaceInQueueByNumber(Integer number) {
        if (clientRepository.findByNumber(number) == null) {
            throw new EntityAlreadyExistsException(ExceptionType.CLIENT_NUMBER_NOT_FIND, number.toString());
        }
        ArrayDeque<Client> clients = queueService.createQueue();
        int counter = 1;
        int time = 0;
        for (Client clientNumber : clients) {
            if (clientNumber.getNumber().equals(number)) {
                break;
            } else {
                counter++;
                {
                    if (clientNumber.getStatus().equals(Status.REGULAR)) {
                        time += 20;
                    }
                    if (clientNumber.getStatus().equals(Status.VIP)) {
                        time += 20;
                    }
                    if (clientNumber.getStatus().equals(Status.URGENT)) {
                        time += 40;
                    }
                }
            }

        }
        return "Place in queue " + counter + " waiting time " + time + " s";
    }


    public String getClientPlaceInQueueByName(String name) {
        if (clientRepository.findByName(name) == null) {
            throw new EntityAlreadyExistsException(ExceptionType.CLIENT_NAME_NOT_FIND, name);
        }
        ArrayDeque<Client> clients = queueService.createQueue();
        int counter = 1;
        int time = 0;
        for (Client clientNumber : clients) {
            if (clientNumber.getName().equals(name)) {
                break;
            } else {
                counter++;
                {
                    if (clientNumber.getStatus().equals(Status.REGULAR)) {
                        time += 20;
                    }
                    if (clientNumber.getStatus().equals(Status.VIP)) {
                        time += 20;
                    }
                    if (clientNumber.getStatus().equals(Status.URGENT)) {
                        time += 40;
                    }
                }
            }

        }
        return "Place in queue " + counter + " waiting time " + time + " s";
    }

    public Client createClient(Client client) {
        if (clientRepository.findByName(client.getName()) != null) {
            throw new EntityAlreadyExistsException(ExceptionType.CLIENT_FOUND, client.getName());
        }
        if (Status.VIP.equals(client.getStatus())) {
            if (client.getPin() != 8888) {
                throw new PinIsNotCorrectException(ExceptionType.CLIENT_PIN, client.getName());
            } else {
                this.save(client);
            }
        }
        if (client.getStatus().equals(Status.URGENT)) {
            if (client.getPin() != 0000) {
                throw new PinIsNotCorrectException(ExceptionType.CLIENT_PIN, client.getName());
            } else {
                this.save(client);
            }
        }
        if (client.getStatus().equals(Status.REGULAR)) {
            this.save(client);
        }
        return client;
    }

    public Client save(Client client) {
        int i = 0;
        while (i < 10) {
            Integer clientNumber = this.numberCreator();
            if (clientRepository.findByNumber(clientNumber) == null) {
                client.setNumber(clientNumber);
                clientRepository.save(client);
                break;
            }
            i++;
            if (i == 9) {
                throw new EntityAlreadyExistsException(ExceptionType.CLIENT_NUMBER_NOT_SAVE, client.getName());
            }
        }
        return client;
    }

    public int numberCreator() {
        Random random = new Random();
        int x = random.nextInt(1000) + 1;
        return x;
    }

}