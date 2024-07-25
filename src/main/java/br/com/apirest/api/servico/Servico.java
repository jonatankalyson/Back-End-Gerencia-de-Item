package br.com.apirest.api.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.apirest.api.modelo.Item;
import br.com.apirest.api.modelo.Mensagem;
import br.com.apirest.api.repositorio.Repositorio;

@Service
public class Servico {
    
    @Autowired
    private Mensagem msg;

    @Autowired
    private Repositorio acao;

    //Método apra cadastra item
    public ResponseEntity<?> cadastrar(Item obj){

        if(obj.getNome().equals("")){
            msg.setMensagem("O nome precisa ser preenchido");
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }else if(obj.getDescricao().equals("")){
            msg.setMensagem("A descrição precisa ser preenchida");
            return new ResponseEntity<>(msg,HttpStatus.BAD_REQUEST );
        }else{
            return new ResponseEntity<>(acao.save(obj),HttpStatus.CREATED);
        }

    }

    //Método para selecionar item
    public ResponseEntity<?> selecionar(){
        return new ResponseEntity<>(acao.findAll(),HttpStatus.OK);

    }

    //Método para seleciona itens através do id
    public ResponseEntity<?> selecionarPeloID(long id){
        if(acao.countById(id) == 0){
            msg.setMensagem("Não foi encontrado nehum item");
            return new ResponseEntity<>(msg,HttpStatus.BAD_REQUEST );

        }else{
            return new ResponseEntity<>(acao.findById(id),HttpStatus.OK);
        }

    }
    //Método para editar item
    public ResponseEntity<?> editar(Item obj){

        if(acao.countById(obj.getId())== 0){
            msg.setMensagem("O id informado não existe");
            return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
        }else if(obj.getNome().equals("")){
            msg.setMensagem("É necessário informar um nome");
            return new ResponseEntity<>(msg,HttpStatus.BAD_REQUEST);
        }else if(obj.getDescricao().equals("")){
            msg.setMensagem(("É necessário informar um descrição"));
            return new ResponseEntity<>(msg,HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(acao.save(obj),HttpStatus.OK);
        }
        
    }
    //Método para remover itens
    public ResponseEntity<?> deletar(long id){

        if(acao.countById(id)== 0){
            msg.setMensagem("O id informado não existe");
            return new ResponseEntity<>(msg,HttpStatus.NOT_FOUND);

        }else{
            Item obj = acao.findById(id);
            acao.delete(obj);
            msg.setMensagem("Item deletado com sucesso");
            return new ResponseEntity<>(msg,HttpStatus.OK);
        }

    }

}
