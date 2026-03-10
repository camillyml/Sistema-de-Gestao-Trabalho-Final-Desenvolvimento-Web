package com.example.demo.service;

import com.example.demo.dto.ClienteEnderecoDTO;
import com.example.demo.entity.Cliente;
import com.example.demo.entity.EnderecoCliente;
import com.example.demo.entity.Produto;
import com.example.demo.repository.ClienteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    // Injeção de Dependência: O Spring "injeta" o objeto repository automaticamente.
    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Cliente registrarClienteEEndereco(ClienteEnderecoDTO dto) {
        // Instanciação da entidade Cliente (principal)
        Cliente cliente = new Cliente();
        cliente.setNome(dto.nome());
        cliente.setCpf(dto.cpf());

        // Instanciação da entidade EnderecoCliente (dependente)
        EnderecoCliente endereco = new EnderecoCliente();
        endereco.setEndereco(dto.endereco());

        // Criação do vínculo
        cliente.adicionarEndereco(endereco);

        // Gravação no banco
        // O Hibernate identificará que o cliente possui um endereço e fará o INSERT
        // na tabela 'cliente' seguido pelo INSERT na tabela 'endereco_cliente'.
        return repository.save(cliente);
    }

    public List<Cliente> listarTodos() {
        return repository.findAll();
    }

    public Cliente buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    @Transactional
    public void excluir(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado");
        }

        Cliente cliente = buscarPorId(id);

        repository.delete(cliente);

    }
    public Cliente buscarPorCpf(String cpf) {
        return repository.findByCpf(cpf)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Cliente com CPF " + cpf + " não encontrado"
                ));
    }
}