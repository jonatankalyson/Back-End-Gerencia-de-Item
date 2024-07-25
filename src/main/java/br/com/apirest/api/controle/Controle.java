package br.com.apirest.api.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.apirest.api.modelo.Item;
import br.com.apirest.api.repositorio.Repositorio;
import br.com.apirest.api.servico.Servico;

@RestController
public class Controle {

    @Autowired
    private Repositorio acao;

    @Autowired
    private Servico servico;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody Item obj){
        return servico.cadastrar(obj);

    }

    @GetMapping("/listar")
    public ResponseEntity<?> selecionar(){
        return servico.selecionar();
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> selecionarPeloId(@PathVariable long id){
        return servico.selecionarPeloID(id);
    }

    @PutMapping("/editar")
    public ResponseEntity<?> editar(@RequestBody Item obj){
        return servico.editar(obj);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
      return servico.deletar(id);
    }

    @GetMapping("/lista/contador")
    public long contador(){
        return acao.count();
    }

    @GetMapping("listar/ordenarNome")
    public List<Item> ordenarNomes(){
        return acao.findByOrderByNome();
    }

    @GetMapping("/status")
    public ResponseEntity<?> status(){
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    @GetMapping("")
    public String mensagem(){
        return "hello world";

    }

    @PostMapping("/item")
    public Item item(@RequestBody Item i){
        return i;


    }
    
}
