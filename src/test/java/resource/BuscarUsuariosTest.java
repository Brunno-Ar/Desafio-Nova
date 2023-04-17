package resource;

import com.ntendencia.domain.Usuario;
import com.ntendencia.repositories.UsuarioRepository;
import com.ntendencia.services.UsuarioService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest(classes = UsuarioService.class)
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class BuscarUsuariosTest {

    @TestConfiguration
    static class BuscarUsuarioTestConfigurarion{
        @Bean
        public UsuarioService usuarioService(){
            return new UsuarioService();
        }
    }

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void buscaUsuarioPorIdTest(){
        Usuario usuario = usuarioService.buscarUsuarioPorId(1);

        Assertions.assertEquals(usuario, 1);
    }
}
