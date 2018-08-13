package com.adisolucoes.adimanager.dao;

import com.adisolucoes.adimanager.exceptions.ErroBancoDadosException;
import com.adisolucoes.adimanager.filtros.LazyFiltro;
import java.util.List;

/**
 *
 * @author ADI Soluções
 */
public interface LazyDAO<T> {

    int quantidadeLazy(LazyFiltro filtro) throws ErroBancoDadosException;

    List<T> buscarLazy(LazyFiltro filtro) throws ErroBancoDadosException;

    T buscarPorId(Long rowKey) throws ErroBancoDadosException;

}
