package com.springAprendendo.br.aprendendo;

import com.springAprendendo.br.aprendendo.domain.Categoria;
import com.springAprendendo.br.aprendendo.domain.Cidade;
import com.springAprendendo.br.aprendendo.domain.Estado;
import com.springAprendendo.br.aprendendo.domain.Produto;
import com.springAprendendo.br.aprendendo.repositories.CategoriaRepository;
import com.springAprendendo.br.aprendendo.repositories.CidadeRepository;
import com.springAprendendo.br.aprendendo.repositories.EstadoRepository;
import com.springAprendendo.br.aprendendo.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class AprendendoApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;

	public static void main(String[] args) {
		SpringApplication.run(AprendendoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null,"Informática");
		Categoria cat2 = new Categoria(null,"Escritório");

		Produto p1 = new Produto(null,"Computador",2000.00);
		Produto p2 = new Produto(null,"Impressora",800.00);
		Produto p3 = new Produto(null,"Mouse",80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Collections.singletonList(p2));

		p1.getCategoriaList().addAll(Collections.singletonList(cat1));
		p2.getCategoriaList().addAll(Arrays.asList(cat1,cat2));
		p3.getCategoriaList().addAll(Collections.singletonList(cat1));

		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));


		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null,"São Paulo");

		Cidade c1 = new Cidade(null,"Uberlândia",est1);
		Cidade c2 = new Cidade(null,"São Paulo",est2);
		Cidade c3 = new Cidade(null,"Campinas",est2);

		est1.getCidades().addAll(Collections.singletonList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));

		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));

	}
}
