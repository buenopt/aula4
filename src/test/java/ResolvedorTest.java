import aula4.exerc16.Cliente;
import aula4.exerc16.ClienteRepo;
import aula4.exerc16.Resolvedor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ResolvedorTest {
    @Test
    public void TodosClientesRecebem25D() throws Exception {
        // Mock do ClienteRepo
        ClienteRepo mockRepo = mock(ClienteRepo.class);
        Resolvedor resolvedor = new Resolvedor(mockRepo);

        int[] cods = {1, 2, 3}; // Três códigos de clientes

        Cliente cliente1 = new Cliente(1, "Cliente 1");
        Cliente cliente2 = new Cliente(2, "Cliente 2");
        Cliente cliente3 = new Cliente(3, "Cliente 3");

        // Configurar comportamento do mockRepo
        when(mockRepo.getCliente(1)).thenReturn(cliente1);
        when(mockRepo.getCliente(2)).thenReturn(cliente2);
        when(mockRepo.getCliente(3)).thenReturn(cliente3);

        // Chamar o método a ser testado
        ArrayList<Cliente> resposta = resolvedor.definirPromocao(cods);

        // Verificar resultados
        Assertions.assertEquals(3, resposta.size());

        Assertions.assertEquals(25, resposta.get(0).getDesconto());
        Assertions.assertEquals(25, resposta.get(1).getDesconto());
        Assertions.assertEquals(25, resposta.get(2).getDesconto());
    }

    @Test
    public void PrimeiroRecebe15D_SegundoRecebe10D() throws Exception {
        // Mock do ClienteRepo
        ClienteRepo mockRepo = mock(ClienteRepo.class);
        Resolvedor resolvedor = new Resolvedor(mockRepo);

        int[] cods = {1, 2}; // Dois códigos de clientes

        Cliente cliente1 = new Cliente(1, "Cliente 1");
        Cliente cliente2 = new Cliente(2, "Cliente 2");

        // Configurar comportamento do mockRepo
        when(mockRepo.getCliente(1)).thenReturn(cliente1);
        when(mockRepo.getCliente(2)).thenReturn(cliente2);

        // Chamar o método a ser testado
        ArrayList<Cliente> resposta = resolvedor.definirPromocao(cods);

        // Verificar resultados
        Assertions.assertEquals(2, resposta.size());

        Assertions.assertEquals(15, resposta.get(0).getDesconto());
        Assertions.assertEquals(10, resposta.get(1).getDesconto());
    }

    @Test
    public void UnicoRecebe15D() throws Exception {
        // Mock do ClienteRepo
        ClienteRepo mockRepo = mock(ClienteRepo.class);
        Resolvedor resolvedor = new Resolvedor(mockRepo);

        int[] cods = {1}; // Um código de cliente

        Cliente cliente1 = new Cliente(1, "Cliente 1");

        // Configurar comportamento do mockRepo
        when(mockRepo.getCliente(1)).thenReturn(cliente1);

        // Chamar o método a ser testado
        ArrayList<Cliente> resposta = resolvedor.definirPromocao(cods);

        // Verificar resultados
        Assertions.assertEquals(1, resposta.size());

        Assertions.assertEquals(15, resposta.get(0).getDesconto());
    }

    @Test
    public void ArrayCodsNulo_LancarExcecao() throws Exception {
        // Mock do ClienteRepo
        ClienteRepo mockRepo = mock(ClienteRepo.class);
        Resolvedor resolvedor = new Resolvedor(mockRepo);

        int[] cods = null; // Array nulo

        // Verificar lançamento de exceção
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            resolvedor.definirPromocao(cods);
        });
    }

    @Test
    public void ArrayCodsVazio_LancarExcecao() throws Exception {
        // Mock do ClienteRepo
        ClienteRepo mockRepo = mock(ClienteRepo.class);
        Resolvedor resolvedor = new Resolvedor(mockRepo);

        int[] cods = {}; // Array vazio

        // Verificar lançamento de exceção
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            resolvedor.definirPromocao(cods);
        });
    }

    @Test
    public void ClienteNaoEncontrado_LancarExcecao() throws Exception {
        // Mock do ClienteRepo
        ClienteRepo mockRepo = mock(ClienteRepo.class);
        Resolvedor resolvedor = new Resolvedor(mockRepo);

        int[] cods = {1}; // Um código de cliente

        // Configurar comportamento do mockRepo para retornar null
        when(mockRepo.getCliente(1)).thenReturn(null);

        // Verificar lançamento de exceção
        Assertions.assertThrows(Exception.class, () -> {
            resolvedor.definirPromocao(cods);
        });
    }
}
