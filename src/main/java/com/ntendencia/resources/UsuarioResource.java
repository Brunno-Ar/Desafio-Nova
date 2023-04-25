package com.ntendencia.resources;

import com.ntendencia.DTO.UsuarioDTO;
import com.ntendencia.DTO.UsuarioNewDTO;
import com.ntendencia.domain.Usuario;
import com.ntendencia.domain.enums.SexoUsuario;
import com.ntendencia.services.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/usuarios")
@Api(value = "UsuarioResource", tags = {"Operações de usuario"})
public class UsuarioResource {

    @Autowired
    private UsuarioService service;


    @ApiResponses(value = {@ApiResponse(code = 200, message = "Retorna a lista de pessoa"), @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"), @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Retornar um usuario pelo Id",
            notes = "Retornar um usuario pelo Id.",
            tags = {"buscar"})
    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable Integer id) {
        Usuario usuario = service.buscarUsuarioPorId(id);
        return ResponseEntity.ok().body(usuario);

    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "Atualizar usuario",
            notes = "Modifica dados de um usuario na lista.",
            tags = {"inserir", "atualizar"})
    public ResponseEntity atualizarUsuario(@Valid @RequestBody UsuarioDTO objDto, @PathVariable Integer id) {
        Usuario obj = service.inserirObjetoPeloDTO(objDto);
        obj.setId(id);
        obj = service.atualizarUsuario(obj);
        return ResponseEntity.ok("Usuario atualizado com sucesso ");
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Apagar usuario",
            notes = "Deleta um usuario da lista .",
            tags = {"deletar"})
    public ResponseEntity excluirUsuario(@PathVariable Integer id) {
        service.excluirUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @ApiResponses(value = {@ApiResponse(code = 200, message = "Retorna a lista de pessoa"), @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"), @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Retornar todos os usuario",
            notes = "Retornar todos os usuario.",
            tags = {"buscar"})
    public ResponseEntity<List<UsuarioDTO>> buscarTodosOsUsuarios() {
        List<Usuario> list = service.buscarTodosOsUsuarios();
        List<UsuarioDTO> listDTO = list.stream().map(UsuarioDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);

    }

    @ApiResponses(value = {@ApiResponse(code = 200, message = "Retorna a lista de pessoa"), @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"), @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ApiOperation(value = "Retornar usuarios de forma paginada",
            notes = "Retornar usuarios de forma paginada. Quantos objetos retornar por pagina, Odernar por nome, CPF, data de nascimento ou a direção ASC e DESC .",
            tags = {"buscar"})
    public ResponseEntity<Page<UsuarioDTO>> buscaPaginadaDeUsuario(@RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "linesPerPager", defaultValue = "24") Integer linesPerPage, @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy, @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        Page<Usuario> list = service.buscaPaginadaDeUsuario(page, linesPerPage, orderBy, direction);
        Page<UsuarioDTO> listDTO = list.map(UsuarioDTO::new);
        return ResponseEntity.ok().body(listDTO);

    }


    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "Adicionar novo usuario",
            notes = "Adiciona um novo usuario na lista.",
            tags = {"inserir"})
    public ResponseEntity inserirNovoUsuario(@Valid @RequestBody UsuarioNewDTO objDto) {
        Usuario obj = service.inserirObjetoPeloDTO(objDto);
        obj = service.inserirNovoUsuario(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body("Usuario criado com sucesso");
    }

    @ApiResponses(value = {@ApiResponse(code = 200, message = "Retorna a lista de pessoa"), @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"), @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    @RequestMapping(value = "/filters", method = RequestMethod.GET)
    @ApiOperation(value = "Retornar usuario usando filtros",
            notes = "Retornar usuario usando filtros.",
            tags = {"buscar"})
    public ResponseEntity<List<Usuario>> buscarUsuariosFiltrados(@RequestParam(value = "nome", required = false) String nome, @RequestParam(value = "cpf", required = false) String cpf, @RequestParam(value = "dataNascimento", required = false) String dataNascimento, @RequestParam(value = "sexo", required = false) SexoUsuario sexo) {
        List<Usuario> list = service.buscarUsuariosFiltrados(nome, cpf, dataNascimento, sexo);
        return ResponseEntity.ok().body(list);

    }
}
