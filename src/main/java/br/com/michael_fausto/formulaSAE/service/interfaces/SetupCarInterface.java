package br.com.michael_fausto.formulaSAE.service.interfaces;

import java.time.LocalDateTime;
import java.util.List;

public interface SetupCarInterface<T> {

    T create(T dto);

    T delete(String name);

    T update(T dto, String name);

    T findByName(String name);

    Boolean isEmpty();

    List<T> showAll();
}
