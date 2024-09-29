package com.pucminas.gestaoquadras.usuarios.usecases.create;

import com.pucminas.gestaoquadras.usuarios.usecases.create.dto.CreateUsuarioUseCaseInput;
import com.pucminas.gestaoquadras.usuarios.usecases.create.dto.CreateUsuarioUseCaseOutput;

public interface CreateUsuarioUseCase {

    CreateUsuarioUseCaseOutput create(CreateUsuarioUseCaseInput input);
}
