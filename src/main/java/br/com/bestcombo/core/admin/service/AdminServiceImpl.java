package br.com.bestcombo.core.admin.service;

import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;

import br.com.bestcombo.core.loja.entity.LojaEntity;
import br.com.bestcombo.ports.service.AdminService;

@ApplicationScoped
public class AdminServiceImpl implements AdminService {

    private static final LojaEntity LOJA_BESTCOMBO = LojaEntity.builder()
            .codigo(UUID.fromString("ea31c776-fadb-4c4d-895f-84d18d25b452"))
            .build();

    private static final UUID CODIGO_PESSOA_BESTCOMBO = UUID.fromString("c215d450-917f-4d7c-ac49-e14ff0498f15");

    @Override
    public LojaEntity getLojaAdministrador() {
        return LOJA_BESTCOMBO;
    }

    @Override
    public UUID getCodigoBestcombo() {
        return CODIGO_PESSOA_BESTCOMBO;
    }

}
