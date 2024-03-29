package com.generation.farmaciaVoceBem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.farmaciaVoceBem.model.Produtos;
import com.generation.farmaciaVoceBem.repository.ProdutosRepository;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutosController {
	
	@Autowired
	private ProdutosRepository repositorio;
	
	//Find All
	
	@GetMapping
	public ResponseEntity<List<Produtos>> findAllProdutos(){
		return ResponseEntity.ok(repositorio.findAll());
	}
	
	//Find By Id
	
	@GetMapping("/{id}")
	public ResponseEntity<Produtos> findByIdProdutos(@PathVariable long id){
		return repositorio.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	//Find By Nome
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produtos>> findByNomeCategorias(@PathVariable String nome){
		return ResponseEntity.ok(repositorio.findAllByNomeContainingIgnoreCase(nome));
	}
	
	//Post
	
	@PostMapping
	public ResponseEntity<Produtos> postProdutos(@RequestBody Produtos produtos){
		return ResponseEntity.status(HttpStatus.CREATED).body(repositorio.save(produtos));
	}
	
	//Put
	
	@PutMapping
	public ResponseEntity<Produtos> putProdutos(@RequestBody Produtos produtos){
			return ResponseEntity.status(HttpStatus.OK).body(repositorio.save(produtos));
		}
	
	//Delete
	@DeleteMapping("/{id}")
	public void deleteProdutos (@PathVariable long id) {
		repositorio.deleteById(id);
	}

}
