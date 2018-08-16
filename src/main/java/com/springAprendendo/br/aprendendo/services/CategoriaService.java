package com.springAprendendo.br.aprendendo.services;

import com.springAprendendo.br.aprendendo.domain.Categoria;
import com.springAprendendo.br.aprendendo.dto.CategoriaDTO;
import com.springAprendendo.br.aprendendo.repositories.CategoriaRepository;
import com.springAprendendo.br.aprendendo.services.services.exceptions.DateIntegrityException;
import com.springAprendendo.br.aprendendo.services.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;


    private void updateData(Categoria newObj, Categoria obj){
        newObj.setNome(obj.getNome());
    }


    public Categoria find(Integer id) {
        Optional<Categoria> obj = categoriaRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
    }

    public Categoria insert (Categoria categoria){
        categoria.setId(null);
        return categoriaRepository.save(categoria);
    }

    public Categoria update(Categoria categoria ){
        Categoria newObj = find(categoria.getId());
        updateData(newObj,categoria);
        return categoriaRepository.save(newObj);
    }

    public void delete(Integer id){
        find(id);
            Optional<Categoria> categoria = categoriaRepository.findById(id);
            if(categoria.get().getProdutos().size() > 0){
                throw new DataIntegrityViolationException("Não é possível excluir uma categoria que apresenta produtos");
            }
            categoriaRepository.deleteById(id);
    }

    public List<Categoria> findAll(){
        return categoriaRepository.findAll();
    }

    public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page,linesPerPage, Sort.Direction.valueOf(direction),orderBy);
        return categoriaRepository.findAll(pageRequest);
    }

    public Categoria fromDTO(CategoriaDTO categoriaDTO){
        return new Categoria(categoriaDTO.getId(),categoriaDTO.getNome());
    }

}
