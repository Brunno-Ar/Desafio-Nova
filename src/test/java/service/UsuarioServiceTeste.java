package service;

import com.ntendencia.DTO.UsuarioDTO;
import com.ntendencia.domain.Usuario;
import com.ntendencia.domain.enums.SexoUsuario;
import com.ntendencia.repositories.CidadeRepository;
import com.ntendencia.repositories.EnderecoRepository;
import com.ntendencia.repositories.EstadoRepository;
import com.ntendencia.repositories.UsuarioRepository;
import com.ntendencia.services.Utils;
import com.ntendencia.services.exceptions.IntegridadeDeDadosException;
import com.ntendencia.services.exceptions.ObjetoNaoEncontradoException;
import com.ntendencia.services.impl.UsuarioServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
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

    @MockBean
    private CidadeRepository cidadeRepository;

    @MockBean
    private EstadoRepository estadoRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    protected static final String MOCK_FOLDER = "/usuarioMock";

    protected static final String USUARIO_MOCK = "usuarioMock.json";

    protected static Usuario getMockObject() {
        return Utils.getMockObject(MOCK_FOLDER, USUARIO_MOCK, Usuario.class);
    }

    protected static UsuarioDTO getMockObjectDTO() {
        return Utils.getMockObject(MOCK_FOLDER, USUARIO_MOCK, UsuarioDTO.class);
    }

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void buscaUsuarioPorIdTest() {
        Usuario usuarioMockado = getMockObject();
        when(usuarioRepository.findById(usuarioMockado.getId())).thenReturn(Optional.of(usuarioMockado));
        Usuario usuario = usuarioServiceImpl.buscarUsuarioPorId(usuarioMockado.getId());

        assertEquals(usuario, usuarioMockado);
    }

    @Test
    public void buscaUsuarioPorIdTestSeNaoForEncontrado() {
        Usuario usuarioMockado = getMockObject();
        when(usuarioRepository.findById(usuarioMockado.getId())).thenReturn(null);

        Throwable throwable = Assertions.catchThrowable(() -> usuarioServiceImpl
                .buscarUsuarioPorId(usuarioMockado.getId()));

        assertThat(throwable).isInstanceOf(ObjetoNaoEncontradoException.class)
                .hasMessageContaining("Objeto não encontrado! Id: " + usuarioMockado.getId()  + " , Tipo: " + Usuario.class.getName());
    }

    @Test
    public void buscaTodosUsuariosTest() {
        Usuario usuarioMockado = getMockObject();
        List<Usuario> usuarios = usuarioServiceImpl.buscarTodosOsUsuarios();
        usuarios.add(usuarioMockado);
        when(usuarioRepository.findAll()).thenReturn(usuarios);

        Assert.assertEquals(1, usuarios.size());
    }

    @Test
    public void atualizarDadosUsuarioTest() {
        Usuario usuarioMockado = new Usuario(1, "Bruno", "123.123.123.23", "03/02/2003",
                SexoUsuario.MASCULINO);
        Usuario novoUsuarioMockaco = new Usuario(1, "Breno", "123.123.123.23", "16/03/2003",
                SexoUsuario.MASCULINO);
        when(usuarioRepository.findById(usuarioMockado.getId())).thenReturn(Optional.of(usuarioMockado));
        usuarioServiceImpl.atualizarUsuario(novoUsuarioMockaco);
        Mockito.verify(usuarioRepository, Mockito.times(1)).save(novoUsuarioMockaco);
    }

    @Test
    public void removerUsuariosTest() {
        usuarioServiceImpl.excluirUsuario(1);
        Mockito.verify(usuarioRepository, Mockito.times(1)).deleteById(1);
    }

    @Test
    public void removerUsuariosTestQuandoUsuarioTemEndereco() {
        doThrow(new DataIntegrityViolationException("")).when(usuarioRepository).deleteById(1);
        Throwable throwable = Assertions.catchThrowable(() -> usuarioServiceImpl.excluirUsuario(1));
        assertThat(throwable).isInstanceOf(IntegridadeDeDadosException.class)
                .hasMessageContaining("Não é possivel excluir usuarios que contém Endereço");
    }

    @Test
    public void inserirUsuariosTest() {
        Usuario usuarioMockado = getMockObject();
        when(usuarioRepository.findById(usuarioMockado.getId())).thenReturn(Optional.of(usuarioMockado));
        when(usuarioRepository.save(usuarioMockado)).thenReturn((usuarioMockado));

        usuarioServiceImpl.inserirNovoUsuario(usuarioMockado);
        Mockito.verify(usuarioRepository, Mockito.times(1)).save(usuarioMockado);
    }

//    @Test
//    public void inserirUsuarioSeTiverCPFCadastradoTest(){
//        Usuario usuarioMockado = getMockObject();
//        when(usuarioRepository.findByCpf(usuarioMockado.getCPF()).getCPF()).thenReturn(null);
//
//        Throwable throwable = Assertions.catchThrowable(() -> usuarioServiceImpl.inserirNovoUsuario(usuarioMockado));
//
//        assertThat(throwable).isInstanceOf(IntegridadeDeDadosException.class)
//                .hasMessageContaining("CPF já cadastrado na base de dados");
//    }

    @Test
    public void buscarUsuariosFiltradosTest() {
        Usuario usuarioMockado = getMockObject();
        List<Usuario> usuarios = usuarioServiceImpl.buscarUsuariosFiltrados(usuarioMockado.getNome(),
                usuarioMockado.getCPF(), usuarioMockado.getDataNascimento(), usuarioMockado.getSexo());
        when(usuarioRepository.findBy(usuarioMockado.getNome(), usuarioMockado.getCPF(), usuarioMockado.getDataNascimento(),
                usuarioMockado.getSexo())).thenReturn(usuarios);

        Mockito.verify(usuarioRepository, Mockito.times(1)).findBy(usuarioMockado.getNome(),
                usuarioMockado.getCPF(), usuarioMockado.getDataNascimento(), usuarioMockado.getSexo());
    }

    @Test
    public void buscarUsuariosFiltradosTestQuandoUsuarioForNulo() {
        Usuario usuarioMockado = getMockObject();
        when(usuarioRepository.findBy(usuarioMockado.getNome(), usuarioMockado.getCPF(), usuarioMockado.getDataNascimento(),
                usuarioMockado.getSexo())).thenReturn(null);

        Throwable throwable = Assertions.catchThrowable(() -> usuarioServiceImpl
                .buscarUsuariosFiltrados(usuarioMockado.getNome(), usuarioMockado.getCPF(),
                        usuarioMockado.getDataNascimento(), usuarioMockado.getSexo()));

        assertThat(throwable).isInstanceOf(ObjetoNaoEncontradoException.class)
                .hasMessageContaining("Objeto não encontrado! Id: " + usuarioMockado.getNome()  + " , Tipo: " + Usuario.class.getName());
    }

    @Test
    public void inserirObjetoPeloDTOTest(){
        UsuarioDTO usuarioMockado = getMockObjectDTO();
        usuarioServiceImpl.inserirObjetoPeloDTO(usuarioMockado);

    }
}
