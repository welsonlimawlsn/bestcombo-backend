package br.com.bestcombo.ports.service;

import java.util.UUID;

import br.com.bestcombo.core.loja.entity.LojaEntity;

public interface AdminService {

    LojaEntity getLojaAdministrador();

    UUID getCodigoBestcombo();

}
