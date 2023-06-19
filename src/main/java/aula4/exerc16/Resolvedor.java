package aula4.exerc16;

import java.util.ArrayList;

public class Resolvedor {
    private ClienteRepo repo;
    public Resolvedor(ClienteRepo repo) { this.repo = repo; }
    //Verificar se o array "cods" é nulo ou vazio. Se sim, lançar uma exceção. (Nó 1)
    public ArrayList<Cliente> definirPromocao(int[ ] cods) throws Exception { if (cods == null || cods.length == 0) //(1)
    throw new IllegalArgumentException("sem codigo algum");
        //Criar uma lista <clientes> vazia para armazenar os clientes. (Nó 2)
        var clientes = new ArrayList<Cliente>(); for (int cod : cods) {
            //Para cada código <cod> no array "cods"
            Cliente c =repo.getCliente(cod); //Chama o método "getCliente" do repositório <repo> para obter um cliente correspondente ao código (Nó 3)
            if(c==null) //Verificar se o cliente retornado é nulo. Se sim, lançar uma exceção. (Nó 4)
                throw new Exception("Codigo do cliente nao valido");
            clientes.add(c); //Adicionar o cliente à lista "clientes". (Nó 5)
        }
        var resposta =new ArrayList<Cliente>(); //Criar uma lista "resposta" vazia para armazenar a resposta da promoção. (Nó 6)
        if (clientes.size()>=3) //verifica se o tamanho da lista <clientes>, se o tamanho for maior ou igual a 3
        {
        //todos ganham 25% de desconto
            for (Cliente c:clientes)
            {
            c.setDesconto(25); //Definir um desconto de 25% para cada cliente na lista "clientes". (Nó 7)
            resposta.add(c); //Adicionar todos os clientes à lista "resposta". (Nó 8)
            }
        }
        else //Caso contrário
        {
            //o 1º Cliente ganha 15%
            clientes.get(0).setDesconto(15); //Definir um desconto de 15% para o primeiro cliente na lista "clientes". (Nó 9)
            resposta.add(clientes.get(0)); //Adicionar o primeiro cliente à lista "resposta". (Nó 10)

            //e,se existir, o 2º cliente ganha 10%
            if(clientes.size()>1) //Se houver um segundo cliente na lista "clientes":
            {
                clientes.get(1).setDesconto(10); //Definir um desconto de 10% para o segundo cliente. (Nó 11)
                resposta.add(clientes.get(1)); //Adicionar o segundo cliente à lista "resposta". (Nó 12)
            }
        }
        return resposta; //Retornar a lista "resposta". (Nó 13)
        }
    }


