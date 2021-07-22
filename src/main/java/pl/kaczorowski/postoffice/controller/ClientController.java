package pl.kaczorowski.postoffice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.kaczorowski.postoffice.dto.ClientDto;

import pl.kaczorowski.postoffice.mapper.ClientMapper;
import pl.kaczorowski.postoffice.service.ClientService;

import java.util.ArrayDeque;

@RestController
@RequestMapping("/v1/client")
public class ClientController {

    private ClientService clientService;
    private ClientMapper clientMapper;

    @Autowired
    public ClientController(ClientService clientService, ClientMapper clientMapper) {
        this.clientService = clientService;
        this.clientMapper = clientMapper;
    }

    @GetMapping
    public ArrayDeque<ClientDto> getClients() {
        return clientService.getClients();
    }

    @PostMapping
    public ClientDto createClient(@RequestBody ClientDto clientDto) {
        return clientMapper.mapToClientDto(clientService.createClient(clientMapper.mapToClient(clientDto)));
    }

    @GetMapping("/number/{number}")
    public String getClientPlaceInQueueByNumber(@PathVariable Integer number) {
        return clientService.getClientPlaceInQueueByNumber(number);
    }

    @GetMapping("/name/{name}")
    public String getClientPlaceInQueueByName(@PathVariable String name) {
        return clientService.getClientPlaceInQueueByName(name);
    }

}
