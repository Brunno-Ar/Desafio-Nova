package service;

import com.ntendencia.repositories.EnderecoRepository;
import com.ntendencia.repositories.UsuarioRepository;
import com.ntendencia.services.UsuarioService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest(classes = UsuarioService.class)
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class RemoverUsuariosTest {

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
    public void RemoverUsuariosTest() {
        usuarioService.excluirUsuario(1);
        Mockito.verify(usuarioRepository, Mockito.times(1)).deleteById(1);
    }


}
