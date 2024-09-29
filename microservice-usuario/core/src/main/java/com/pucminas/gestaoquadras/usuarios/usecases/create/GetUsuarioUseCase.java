package com.pucminas.gestaoquadras.usuarios.usecases.create;

import com.pucminas.gestaoquadras.usuarios.usecases.create.dto.CreateUsuarioUseCaseInput;
import com.pucminas.gestaoquadras.usuarios.usecases.create.dto.CreateUsuarioUseCaseOutput;
import com.pucminas.gestaoquadras.usuarios.usecases.create.dto.GetUsuarioUseCaseInput;
import com.pucminas.gestaoquadras.usuarios.usecases.create.dto.GetUsuarioUseCaseOutput;

public interface GetUsuarioUseCase {
    GetUsuarioUseCaseOutput get(GetUsuarioUseCaseInput input);
}
