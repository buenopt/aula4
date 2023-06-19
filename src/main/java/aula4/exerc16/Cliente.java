package aula4.exerc16;

public class Cliente {
    private int codigo;
    private String nome; private int desconto = 0;
    public Cliente(int codigo, String nome) { this.codigo = codigo;
        this.nome = nome;
    }
    public int getCodigo() { return codigo; } public String getNome() { return nome; }
    public int getDesconto() { return desconto; }
    public void setDesconto(int desconto) { this.desconto = desconto;
    }
}
