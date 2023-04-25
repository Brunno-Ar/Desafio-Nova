package resource;

import com.ntendencia.domain.Usuario;
import com.ntendencia.domain.enums.SexoUsuario;
import com.ntendencia.repositories.EnderecoRepository;
import com.ntendencia.repositories.UsuarioRepository;
import com.ntendencia.services.UsuarioService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Optional;

@SpringBootTest(classes = UsuarioService.class)
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class BuscarUsuariosTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @MockBean
    private UsuarioRepository usuarioRepository;

    @MockBean
    private EnderecoRepository enderecoRepository;

//    @Before
//    public void setup(){
//        MockitoAnnotations.initMocks(this);
//    }
    @Test
    public void buscaUsuarioPorIdTest(){
        Usuario usuarioMockado = new Usuario (1, "Bruno", "123.123.123.23", "03/02/2003", SexoUsuario.MASCULINO);
        Mockito.when(usuarioRepository.findById(usuarioMockado.getId())).thenReturn(Optional.of(usuarioMockado));
        Usuario usuario = usuarioService.buscarUsuarioPorId(1);

        Assertions.assertEquals(usuario, usuarioMockado);
    }

}
