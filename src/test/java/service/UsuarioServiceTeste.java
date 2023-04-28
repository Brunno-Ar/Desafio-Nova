package service;

import com.ntendencia.domain.Usuario;
import com.ntendencia.domain.enums.SexoUsuario;
import com.ntendencia.repositories.EnderecoRepository;
import com.ntendencia.repositories.UsuarioRepository;
import com.ntendencia.services.UsuarioServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest(classes = UsuarioServiceImpl.class)
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class UsuarioServiceTeste {

    @InjectMocks
    private UsuarioServiceImpl usuarioServiceImpl;

    @MockBean
    private UsuarioRepository usuarioRepository;

    @MockBean
    private EnderecoRepository enderecoRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void buscaUsuarioPorIdTest() {
        Usuario usuarioMockado = new Usuario(1, "Bruno", "123.123.123.23", "03/02/2003",
                SexoUsuario.MASCULINO);
        when(usuarioRepository.findById(usuarioMockado.getId())).thenReturn(Optional.of(usuarioMockado));
        Usuario usuario = usuarioServiceImpl.buscarUsuarioPorId(1);

        Assertions.assertEquals(usuario, usuarioMockado);
    }

    @Test
    public void BuscaTodosUsuariosTest() {
        Usuario usuarioMockado = new Usuario(1, "Bruno", "123.123.123.23", "03/02/2003",
                SexoUsuario.MASCULINO);
        List<Usuario> usuarios = usuarioServiceImpl.buscarTodosOsUsuarios();
        usuarios.add(usuarioMockado);
        when(usuarioRepository.findAll()).thenReturn(usuarios);

        Assert.assertEquals(1, usuarios.size());
    }

    @Test
    public void AtualizarDadosUsuarioTest() {
        Usuario usuarioMockado = new Usuario(1, "Bruno", "123.123.123.23", "03/02/2003",
                SexoUsuario.MASCULINO);
        Usuario novoUsuarioMockaco = new Usuario(1, "Breno", "123.123.123.23", "16/03/2003",
                SexoUsuario.MASCULINO);
        when(usuarioRepository.findById(usuarioMockado.getId())).thenReturn(Optional.of(usuarioMockado));
        usuarioServiceImpl.atualizarUsuario(novoUsuarioMockaco);
        Mockito.verify(usuarioRepository, Mockito.times(1)).save(novoUsuarioMockaco);
    }

    @Test
    public void RemoverUsuariosTest() {
        usuarioServiceImpl.excluirUsuario(1);
        Mockito.verify(usuarioRepository, Mockito.times(1)).deleteById(1);
    }

}
