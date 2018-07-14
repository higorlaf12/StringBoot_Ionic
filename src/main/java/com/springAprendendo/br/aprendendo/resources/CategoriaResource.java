package com.springAprendendo.br.aprendendo.resources;


import com.springAprendendo.br.aprendendo.domain.Categoria;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @RequestMapping(method = RequestMethod.GET)
    public List<Categoria> listar(){
        Categoria cat = new Categoria(1,"Informatica");
        Categoria cat2 = new Categoria(2,"Escritorio");

        List<Categoria> catList = new ArrayList<>();
        catList.add(cat);
        catList.add(cat2);

        return catList;
    }

}
