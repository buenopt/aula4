import br.inf.bueno.Cliente;
import br.inf.bueno.ClienteRepo;
import br.inf.bueno.Resolvedor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class ResolvedorTest {

    @Test
    public void testDefinirPromocao_CodsNull_ThrowsException() {
        ClienteRepo mockRepo = mock(ClienteRepo.class);
        Resolvedor resolvedor = new Resolvedor(mockRepo);

        int[] cods = null;

        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            resolvedor.definirPromocao(cods);
        });

        String expectedMessage = "sem codigo algum";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }
    // Caminho executado: 1

    @Test
    public void testDefinirPromocao_CodsEmpty_ThrowsException() {
        ClienteRepo mockRepo = mock(ClienteRepo.class);
        Resolvedor resolvedor = new Resolvedor(mockRepo);

        int[] cods = {};

        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            resolvedor.definirPromocao(cods);
        });

        String expectedMessage = "sem codigo algum";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }
    // Caminho executado: 1

    @Test
    public void testDefinirPromocao_CodInvalido_ThrowsException() throws Exception {
        ClienteRepo mockRepo = mock(ClienteRepo.class);
        Resolvedor resolvedor = new Resolvedor(mockRepo);

        int[] cods = { 1 }; // Código inválido

        when(mockRepo.getCliente(1)).thenReturn(null); // Simula cliente não encontrado

        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            resolvedor.definirPromocao(cods);
        });

        String expectedMessage = "Codigo do cliente nao valido";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }
    // Caminho executado: 1 -> 3

    @Test
    public void testDefinirPromocao_TresClientes() throws Exception {
        ClienteRepo mockRepo = mock(ClienteRepo.class);
        Resolvedor resolvedor = new Resolvedor(mockRepo);

        int[] cods = { 1, 2, 3 }; // Três códigos de clientes

        Cliente cliente1 = new Cliente(1, "Cliente 1");
        Cliente cliente2 = new Cliente(2, "Cliente 2");
        Cliente cliente3 = new Cliente(3, "Cliente 3");

        when(mockRepo.getCliente(1)).thenReturn(cliente1);
        when(mockRepo.getCliente(2)).thenReturn(cliente2);
        when(mockRepo.getCliente(3)).thenReturn(cliente3);

        ArrayList<Cliente> resposta = resolvedor.definirPromocao(cods);

        Assertions.assertEquals(3, resposta.size());

        Assertions.assertEquals(25, resposta.get(0).getDesconto());
        Assertions.assertEquals(25, resposta.get(1).getDesconto());
        Assertions.assertEquals(25, resposta.get(2).getDesconto());
    }
    // Caminho executado: 1 -> 4 -> 5a

    @Test
    public void testDefinirPromocao_DoisClientes() throws Exception {
        ClienteRepo mockRepo = mock(ClienteRepo.class);
        Resolvedor resolvedor = new Resolvedor(mockRepo);

        int[] cods = { 1, 2 }; // Dois códigos de clientes

        Cliente cliente1 = new Cliente(1, "Cliente 1");
        Cliente cliente2 = new Cliente(2, "Cliente 2");

        when(mockRepo.getCliente(1)).thenReturn(cliente1);
        when(mockRepo.getCliente(2)).thenReturn(cliente2);

        ArrayList<Cliente> resposta = resolvedor.definirPromocao(cods);

        Assertions.assertEquals(2, resposta.size());

        Assertions.assertEquals(15, resposta.get(0).getDesconto());
        Assertions.assertEquals(10, resposta.get(1).getDesconto());
    }
    // Caminho executado: 1 -> 4 -> 5b -> 7

    @Test
    public void testDefinirPromocao_ApenasUmCliente() throws Exception {
        ClienteRepo mockRepo = mock(ClienteRepo.class);
        Resolvedor resolvedor = new Resolvedor(mockRepo);

        int[] cods = { 1 }; // Um código de cliente

        Cliente cliente1 = new Cliente(1, "Cliente 1");

        when(mockRepo.getCliente(1)).thenReturn(cliente1);

        ArrayList<Cliente> resposta = resolvedor.definirPromocao(cods);

        Assertions.assertEquals(1, resposta.size());

        Assertions.assertEquals(15, resposta.get(0).getDesconto());
    }
    // Caminho executado: 1 -> 4 -> 6
}
