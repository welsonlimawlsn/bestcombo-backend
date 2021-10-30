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

    @Override
    public LojaEntity getLojaAdministrador() {
        return LOJA_BESTCOMBO;
    }

}
