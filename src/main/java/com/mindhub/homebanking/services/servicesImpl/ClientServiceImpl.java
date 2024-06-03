package com.mindhub.homebanking.services.servicesImpl;

import com.mindhub.homebanking.dtos.ClientDTO;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositorios.ClientRepository;
import com.mindhub.homebanking.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<Client> getListClients() {
        return clientRepository.findAll();
    }

    @Override
    public List<ClientDTO> getListClientsDTO() {
        return getListClients().stream().map(ClientDTO::new).collect(Collectors.toList());
    }

    @Override
    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public ClientDTO getClientDTO(Client client) {
        return new ClientDTO(client);
    }

    @Override
    public Client getClientByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    @Override
    public void saveClient(Client client) {
        clientRepository.save(client);
    }
    @Override
    public boolean existEmail(String email) {
        return clientRepository.existsByEmail(email);
    }
}
