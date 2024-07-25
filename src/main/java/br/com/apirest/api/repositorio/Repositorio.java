package br.com.apirest.api.repositorio;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.apirest.api.modelo.Item;

@Repository
public interface Repositorio extends CrudRepository<Item, Long> {

    List<Item> findAll();

    Item findById(long id);

    List<Item> findByOrderByNome();

    int countById(long id);
    
}
