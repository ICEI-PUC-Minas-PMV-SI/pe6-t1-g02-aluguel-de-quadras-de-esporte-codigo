package com.pucminas.gestaoquadras.usuarios.usecases.create;

import com.pucminas.gestaoquadras.usuarios.usecases.create.dto.DeleteUsuarioCaseInput;
import com.pucminas.gestaoquadras.usuarios.usecases.create.dto.UpdateUsuarioCaseInput;

public interface DeleteUsuarioUseCase {
    void delete(DeleteUsuarioCaseInput input);
}
