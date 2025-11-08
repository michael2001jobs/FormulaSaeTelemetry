package br.com.michael_fausto.formulaSAE.service.interfaces;

import java.util.List;

public interface SetupCarServiceInterface<T> {

    T create(T dto);

    T deleteById(Long id);

    T updateName(String updateName, Long id);

    T copyAndEdit(Long id);

    Boolean isEmpty();

    List<T> showAll();

    T findById(Long id);

}
