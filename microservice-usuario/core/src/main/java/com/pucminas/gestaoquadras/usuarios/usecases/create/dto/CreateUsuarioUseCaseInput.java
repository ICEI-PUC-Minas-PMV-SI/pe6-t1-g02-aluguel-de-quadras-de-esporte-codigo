package com.pucminas.gestaoquadras.usuarios.usecases.create.dto;

import com.pucminas.gestaoquadras.usuarios.entities.valueobjects.CNPJ;
import com.pucminas.gestaoquadras.usuarios.entities.valueobjects.CPF;
import com.pucminas.gestaoquadras.usuarios.entities.valueobjects.Email;
import com.pucminas.gestaoquadras.usuarios.entities.valueobjects.Senha;

public record CreateUsuarioUseCaseInput(
        Senha senha,
        Email email,
        String telefone,
        String nome,
        CNPJ cnpj,
        CPF cpf
) {
}
