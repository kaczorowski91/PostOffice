package pl.kaczorowski.postoffice.mapper;

import org.springframework.stereotype.Component;
import pl.kaczorowski.postoffice.dto.ClientDto;
import pl.kaczorowski.postoffice.entity.Client;

import java.util.ArrayDeque;
import java.util.stream.Collectors;

@Component
public class ClientMapper {

    public Client mapToClient(final ClientDto clientDto) {
        return new Client(
                clientDto.getName(),
                clientDto.getStatus(),
                clientDto.getPin());
    }

    public ClientDto mapToClientDto(final Client client) {
        return new ClientDto(
                client.getName(),
                client.getNumber());
    }

    public ArrayDeque<ClientDto> mapToClientDtoList(ArrayDeque<Client> clients) {
        return clients.stream()
                .map(this::mapToClientDto)
                .collect(Collectors.toCollection(ArrayDeque::new));
    }
}
