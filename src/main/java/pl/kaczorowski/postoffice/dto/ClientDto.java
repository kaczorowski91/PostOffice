package pl.kaczorowski.postoffice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.kaczorowski.postoffice.entity.Status;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientDto {

    private String name;
    private Status status;
    private Integer pin;
    private Integer number;

    public ClientDto(String name, Integer number) {
        this.name = name;
        this.number = number;
    }
}
