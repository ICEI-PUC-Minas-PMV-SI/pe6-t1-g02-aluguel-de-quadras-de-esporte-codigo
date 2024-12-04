package com.pucminas.gestaoquadras.usuarios.entrypoint;

import com.pucminas.gestaoquadras.configuration.exception.ErrorResponse;
import com.pucminas.gestaoquadras.usuarios.entrypoint.requests.CreateUserRequest;
import com.pucminas.gestaoquadras.usuarios.entrypoint.requests.UpdateUserRequest;
import com.pucminas.gestaoquadras.usuarios.entrypoint.responses.CreateUserResponse;
import com.pucminas.gestaoquadras.usuarios.usecases.create.dto.GetUsuarioUseCaseOutput;
import com.pucminas.gestaoquadras.usuarios.usecases.create.dto.ListarUsuarioUseCaseOutput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
@RequestMapping("api/v1/usuarios")
@Tag(name = "Usuário")
public interface UsuarioRestEntrypoint {
    /**
     * get -  recuperar recurso
     * post - criacao de um recurso
     * put - edicao completa de um recurso
     * delete - deleco de um recurso
     * patch - edicao parcial de um recurso
     **/
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Criar usuário",
            description = "Cria um usuário específico"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CreateUserResponse.class)
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500", description = "Erro interno",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    ResponseEntity<CreateUserResponse> createUser(
            @RequestBody @Valid CreateUserRequest body
    );

    @PutMapping
            (
                    path = "/{id}",
                    consumes = MediaType.APPLICATION_JSON_VALUE,
                    produces = MediaType.APPLICATION_JSON_VALUE
            )
    @Operation(
            summary = "Editar usuário",
            description = "Editar um usuário específico"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário editado com sucesso",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500", description = "Erro interno",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    ResponseEntity<?> updateUser(
            @RequestBody @Valid UpdateUserRequest body,
            @PathVariable("id") String id,
            Authentication auth
    );

    @GetMapping
            (
                    consumes = MediaType.APPLICATION_JSON_VALUE,
                    produces = MediaType.APPLICATION_JSON_VALUE
            )
    @Operation(
            summary = "Listar usuários",
            description = "Lista usuários"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuários listado com sucesso",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ListarUsuarioUseCaseOutput.class)
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500", description = "Erro interno",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    ResponseEntity<?> listUser();

    @GetMapping(
            path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Recuperar usuário",
            description = "Recupera um usuário específico"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário recuperado com sucesso",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = GetUsuarioUseCaseOutput.class)
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500", description = "Erro interno",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    ResponseEntity<?> getUser(
            @PathVariable("id") String id
    );


    @DeleteMapping(

            path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Deletar usuário",
            description = "Deleta um usuário específico"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500", description = "Erro interno",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    ResponseEntity<?> deleteUser(
            @PathVariable("id") String id,
            Authentication auth
    );

}

