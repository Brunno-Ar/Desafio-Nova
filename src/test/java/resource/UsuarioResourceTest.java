package resource;

import com.ntendencia.domain.Usuario;
import com.ntendencia.dto.UsuarioDTO;
import com.ntendencia.dto.UsuarioNewDTO;
import com.ntendencia.resources.UsuarioResource;
import com.ntendencia.services.UsuarioService;
import com.ntendencia.services.Utils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.ntendencia.services.Utils.asJsonString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
@ContextConfiguration(classes = {UsuarioResource.class})
@AutoConfigureMockMvc
public class UsuarioResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService service;

    @MockBean
    private UsuarioResource resource;

    protected static final String MOCK_FOLDER = "/usuarioMock";

    protected static final String USUARIO_MOCK = "usuarioMock.json";
    protected static final String USUARIONEWDTO_MOCK = "usuarioNewDTOMock.json";

    protected static Usuario getMockObject() {
        return Utils.getMockObject(MOCK_FOLDER, USUARIO_MOCK, Usuario.class);
    }

    protected static UsuarioDTO getMockObjectDTO() {
        return Utils.getMockObject(MOCK_FOLDER, USUARIO_MOCK, UsuarioDTO.class);
    }

    protected static UsuarioNewDTO getMockObjectNewDTO() {
        return Utils.getMockObject(MOCK_FOLDER, USUARIONEWDTO_MOCK, UsuarioNewDTO.class);
    }

    @Test
    public void TestarBuscarUsuarioPorId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/usuarios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse();
    }

    @Test
    public void TestarBuscarTodosUsuarios() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse();
    }

    @Test
    public void TestarExcluirUsuario() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/usuarios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andReturn().getResponse();
    }

    @Test
    public void TestarAtualizarUsuario() throws Exception {
        UsuarioDTO usuarioMockado = getMockObjectDTO();
        mockMvc.perform(MockMvcRequestBuilders.put("/usuarios/1")
                        .content(asJsonString(usuarioMockado))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse();
        Mockito.verify(resource).atualizarUsuario(usuarioMockado, 1);
    }

}
