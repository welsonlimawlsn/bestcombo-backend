package br.com.bestcombo.adapters.email.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public abstract class EmailTemplate {

    private String to;

    private String subject;

}
