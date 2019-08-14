package com.infogrupo.loja;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.infogrupo.loja.entity.Categoria;
import com.infogrupo.loja.entity.Cidade;
import com.infogrupo.loja.entity.Cliente;
import com.infogrupo.loja.entity.Endereco;
import com.infogrupo.loja.entity.Estado;
import com.infogrupo.loja.entity.Produto;
import com.infogrupo.loja.entity.enums.TipoCliente;
import com.infogrupo.loja.repository.CategoriaRepository;
import com.infogrupo.loja.repository.CidadeRepository;
import com.infogrupo.loja.repository.ClienteRepository;
import com.infogrupo.loja.repository.EnderecoRepository;
import com.infogrupo.loja.repository.EstadoRepository;
import com.infogrupo.loja.repository.ProdutoRepository;

@SpringBootApplication
public class SpringbootionicApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootionicApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 60.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().add(cat1);
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.save(Arrays.asList(cat1,cat2));
		produtoRepository.save(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado(null, "São Paulo");
		Estado est2 = new Estado(null, "Rio de Janeiro");
		
		Cidade cid1 = new Cidade(null, "Campinas",est1);
		Cidade cid2 = new Cidade(null, "Campos",est2);
		Cidade cid3 = new Cidade(null, "Itapeva",est2);
		
		est1.getCidades().addAll(Arrays.asList(cid1));
		est2.getCidades().addAll(Arrays.asList(cid2,cid3));
		
		estadoRepository.save(Arrays.asList(est1,est2));
		cidadeRepository.save(Arrays.asList(cid1,cid2,cid3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "1179293706", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("23458877","981759533"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "234", "ap 304", "Jardim", "21635440", cli1, cid1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "234", "ap 304", "Jardim", "21635440", cli1, cid2);
		
		cli1.getEnderecos().addAll((Arrays.asList(e1,e2)));
		cli1.getTelefones().addAll(Arrays.asList("23458877","981759533"));
		
		clienteRepository.save(Arrays.asList(cli1));
		
		enderecoRepository.save(Arrays.asList(e1,e2));
		
	}

}
