package com.springAprendendo.br.aprendendo.services;

import com.springAprendendo.br.aprendendo.domain.Categoria;
import com.springAprendendo.br.aprendendo.domain.Cidade;
import com.springAprendendo.br.aprendendo.domain.Cliente;
import com.springAprendendo.br.aprendendo.domain.Endereco;
import com.springAprendendo.br.aprendendo.domain.domain.enums.TipoCliente;
import com.springAprendendo.br.aprendendo.dto.ClienteDTO;
import com.springAprendendo.br.aprendendo.dto.ClienteNewDTO;
import com.springAprendendo.br.aprendendo.repositories.ClienteRepository;
import com.springAprendendo.br.aprendendo.repositories.EnderecoRepository;
import com.springAprendendo.br.aprendendo.services.services.exceptions.DateIntegrityException;
import com.springAprendendo.br.aprendendo.services.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;


    private void updateData(Cliente newObj, Cliente obj){
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }

    public Cliente find(Integer id) {
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
    }

    @Transactional
    public Cliente insert (Cliente cliente){
        cliente.setId(null);
        cliente = clienteRepository.save(cliente);
        enderecoRepository.saveAll(cliente.getEnderecos());
        return cliente;
    }


    public Cliente update(Cliente cliente){
        Cliente newObj = find(cliente.getId());
        updateData(newObj,cliente);
        return clienteRepository.save(newObj);
    }

    public void delete(Integer id){
        find(id);
             Optional<Cliente> cliente = clienteRepository.findById(id);
            if(cliente.get().getPedidos().size() > 0){
                throw new DateIntegrityException("Não é possível excluir porque há entidades relacionadas");
            }
            clienteRepository.deleteById(id);
    }

    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page,linesPerPage, Sort.Direction.valueOf(direction),orderBy);
        return clienteRepository.findAll(pageRequest);
    }

    public Cliente fromDTO(ClienteDTO clienteDTO){
        return new Cliente(clienteDTO.getId(),clienteDTO.getNome(),clienteDTO.getEmail(),null,null);
    }

    public Cliente fromDTO(ClienteNewDTO clienteNewDTO){

        Cliente cliente = new Cliente(null,clienteNewDTO.getNome(),clienteNewDTO.getEmail(),
               clienteNewDTO.getCpfOuCnpj(),TipoCliente.toEnum(clienteNewDTO.getTipo()));

       Cidade cid = new Cidade(clienteNewDTO.getCidadeId(), null, null);

       Endereco endereco = new Endereco(null,clienteNewDTO.getLogradouro(),clienteNewDTO.getNumero(),
                clienteNewDTO.getComplemento(),clienteNewDTO.getBairro(),clienteNewDTO.getCep(),cliente,cid);

       cliente.getEnderecos().add(endereco);
       cliente.getTelefones().add(clienteNewDTO.getTelefone1());

        if(clienteNewDTO.getTelefone2() != null) {
            cliente.getTelefones().add(clienteNewDTO.getTelefone2());
        }
        if(clienteNewDTO.getTelefone3() != null){
            cliente.getTelefones().add(clienteNewDTO.getTelefone3());
        }
        return cliente;

    }


}
