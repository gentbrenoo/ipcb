package cliente;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import util.Validator;

/**
 * Classe que representa um cartão de fidelização na cadeia de lojas HonESta.
 */
public class Cartao {
    private String id;
    private long saldo;
    private ArrayList<Cupao> cupoesAtribuidos = new ArrayList<Cupao>();
    private ArrayList<Cupao> activos = new ArrayList<Cupao>();
    private boolean estado = false;

    public Cartao(String id, long saldo) {
        this.id = Validator.requireNonBlankTrimmed(id);
        this.saldo = Validator.requirePositiveOrZero(saldo);
    }

    public long getSaldo() {
        return saldo;
    }

    public String getId() {
        return id;
    }

    /**
     * Ativa os cupões selecionados. Não aceita cupões que não estejam na lista de
     * cupões do cliente
     * 
     * @param ativos lista de cupões para ativar
     */
    public void ativar(List<Cupao> ativos) {
        List<Cupao> ativos2 = new ArrayList<>(ativos);
        for (Cupao cupao : ativos2) {
            if (cupoesAtribuidos.contains(cupao) && cupao.estaValido()) {
                activos.add(cupao);
            }
        }
        this.estado = true;
    }

    /**
     * Usar o cartão numa venda, se este estiver ativo. Se tiver cupões ativos estes
     * devem ser aplicados também. Os cupões usados na venda serão removidos. Os
     * cupões ativos mas não usados na venda, deixam de estar ativos, mas permanecem
     * associados ao cartão. No final do uso o cartão é automaticamente desativado.
     * 
     * @param v a venda onde usar o cartão
     * @throws IllegalStateException se o cartão não estiver ativo
     */
    public void usar(Venda v) {
        if (!estaAtivo()) {
            throw new IllegalArgumentException("O cartão não está ativo.");
        }

        List<Cupao> cupoesAtivos = new ArrayList<>(activos);
        for (Cupao cupao : cupoesAtivos) {
            cupao.aplicar(this, v);
        }
        desativarCartao();
        estado = false;
    }

    /**
     * Retorna uma lista com os cupões disponíveis. Os cupões disponíveis são
     * aqueles que estão válidos no dia atual.
     * 
     * @return uma lista com os cupões disponíveis
     */
    public List<Cupao> getCupoesDisponiveis() {
        List<Cupao> cupoesDisponiveis = new ArrayList<>();
        for (int i = 0; i < cupoesAtribuidos.size(); i++) {
            Cupao cupao = cupoesAtribuidos.get(i);
            if (cupao.estaValido())
                cupoesDisponiveis.add(cupao);
        }

        return Collections.unmodifiableList(cupoesDisponiveis);
    }

    /**
     * Retorna uma lista com os cupões que estarão disponíveis no futuro, isto é,
     * cujo início é após o dia de hoje.
     * 
     * @return uma lista com os cupões que estarão disponíveis no futuro
     */
    public List<Cupao> getCupoesFuturos() {
        List<Cupao> cupoesFuturos = new ArrayList<>();

        for (int i = 0; i < cupoesAtribuidos.size(); i++) {
            Cupao cupao = cupoesAtribuidos.get(i);
            if (cupao.futurosValidos())
                cupoesFuturos.add(cupao);
        }
        return Collections.unmodifiableList(cupoesFuturos);
    }

    /**
     * Atualiza os cupões, removendo os que já passaram de validade
     */
    public void atualizarCupoes() {
        // LocalDate hoje = LocalDate.now();
        // cupoesAtribuidos.removeIf(cupao -> cupao.getDataFim().isBefore(hoje));

        for (int i = cupoesAtribuidos.size() - 1; i >= 0; i--) {
            Cupao cupao = cupoesAtribuidos.get(i);
            if (!cupao.estaValido() && !cupao.futurosValidos()) {
                cupoesAtribuidos.remove(i);
            }
        }
        for (int i = activos.size() - 1; i >= 0; i--) {
            Cupao cupao = activos.get(i);
            if (!cupao.estaValido() && !cupao.futurosValidos()) {
                activos.remove(i);
            }
        }
    }

    /**
     * Usar o saldo do cartão. Deve garantir que o gasto não é maior que o saldo.
     * 
     * @param gasto o que retirar do saldo.
     */
    public void reduzirSaldo(long gasto) {
        if (gasto <= saldo)
            saldo -= gasto;

    }

    /**
     * Acumular saldo no cartão
     * 
     * @param valor valor a acumular no saldo
     */
    public void acumularSaldo(long valor) {
        saldo += Validator.requirePositiveOrZero(valor);
    }

    /**
     * indica se um cartão está ativo. isto é, pronto a ser usado numa compra.
     * 
     * @return true se está ativo.
     */
    public boolean estaAtivo() {
        return estado;
    }

    public void removerCupao(Cupao cupao) {
        cupoesAtribuidos.remove(cupao);
        activos.remove(cupao);
    }

    public void desativarCartao() {
        estado = false;
        activos.clear();
    }

    public void addCupao(Cupao cupao) {
        if(!cupoesAtribuidos.contains(cupao)){
            cupoesAtribuidos.add(cupao);
        }
    }

}
