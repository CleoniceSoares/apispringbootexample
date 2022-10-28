package com.example.apiexamplespringboot.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.apiexamplespringboot.model.Produto;
import com.example.apiexamplespringboot.repository.ProdutoRepository;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository _produtoRepository;

    public List<Produto> findAll() {
        return _produtoRepository.findAll();
    }

    public ResponseEntity<Produto> findById(UUID id) {
        Optional<Produto> produto = _produtoRepository.findById(id);

        if (produto.isPresent()) {
            return new ResponseEntity<Produto>(produto.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public Produto save(Produto produto) {
        return _produtoRepository.save(produto);
    }

    public ResponseEntity<Produto> update(UUID id, Produto newProduto) {
        Optional<Produto> oldProduto = _produtoRepository.findById(id);

        if (oldProduto.isPresent()) {
            Produto produto = oldProduto.get();

            produto.setNome(newProduto.getNome());
            produto.setPreco(newProduto.getPreco());
            produto.setQuantidade(newProduto.getQuantidade());

            _produtoRepository.save(produto);

            return new ResponseEntity<Produto>(produto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Object> delete(UUID id) {
        Optional<Produto> produto = _produtoRepository.findById(id);

        if (produto.isPresent()) {
            _produtoRepository.delete(produto.get());

            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
