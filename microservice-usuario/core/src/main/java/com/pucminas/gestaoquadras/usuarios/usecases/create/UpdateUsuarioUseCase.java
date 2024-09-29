package com.pucminas.gestaoquadras.usuarios.usecases.create;

import com.pucminas.gestaoquadras.usuarios.usecases.create.dto.CreateUsuarioUseCaseInput;
import com.pucminas.gestaoquadras.usuarios.usecases.create.dto.CreateUsuarioUseCaseOutput;
import com.pucminas.gestaoquadras.usuarios.usecases.create.dto.UpdateUsuarioCaseInput;

public interface UpdateUsuarioUseCase {
    void update(UpdateUsuarioCaseInput input);
}

