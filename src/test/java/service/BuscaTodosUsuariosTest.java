package service;

import com.ntendencia.domain.Usuario;
import com.ntendencia.domain.enums.SexoUsuario;
import com.ntendencia.repositories.EnderecoRepository;
import com.ntendencia.repositories.UsuarioRepository;
import com.ntendencia.services.UsuarioService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest(classes = UsuarioService.class)
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class BuscaTodosUsuariosTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @MockBean
    private UsuarioRepository usuarioRepository;

    @MockBean
    private EnderecoRepository enderecoRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void BuscaTodosUsuariosTest() {
        Usuario usuarioMockado = new Usuario(1, "Bruno", "123.123.123.23", "03/02/2003", SexoUsuario.MASCULINO);
        List<Usuario> usuarios = usuarioService.buscarTodosOsUsuarios();
        usuarios.add(usuarioMockado);
        when(usuarioRepository.findAll()).thenReturn(usuarios);

        Assert.assertEquals(1, usuarios.size());
    }


}
