package poo.tempo;

/**
 * Representa um periodo de tempo entre uma hora inicial e hora final
 */
public class Periodo {

    private Hora inicio;
    private Hora fim;

    /**
     * Criar um período indicando a hora inicial e final garantindo que a inicial é
     * menor que a final
     *
     * @param inicio hora inicial do periodo
     * @param fim    hora final do periodo
     */
    public Periodo(Hora inicio, Hora fim) {
        // não precisamos testar ini ou fim == null pois é testado pelo compareTo
        if (inicio.compareTo(fim) > 0) {
            throw new IllegalArgumentException();
        }
        this.inicio = inicio;
        this.fim = fim;
    }

    /**
     * Criar um período indicando a hora inicial e a duração em segundos
     *
     * @param ini     hora inicial do período
     * @param duracao duracao do período em segundos
     */
    public Periodo(Hora ini, int duracao) {
        if (duracao < 0) {
            throw new IllegalArgumentException();
        }
        // não precisamos testar ini == null, pois é implicitamente testado em
        // ini.clone()
        this.inicio = ini;
        // tem de ser um clone senão na linha seguinte altera também a hora inicial
        fim = ini.clone();
        fim.somaSegundos(duracao);
    }

    /**
     * retorna a duração, em segundos, do período
     *
     * @return a duração, em segundos, do período
     */
    public int duracao() {
        return fim.diferencaSegs(inicio);
    }

    /**
     * indica se uma dada hora está dentro deste período de tempo
     *
     * @param h a hora que se pretende verificar se está dentro do período de tempo
     * @return true, se a hora está dentro
     */
    public boolean estaDentro(Hora h) {
        return h.compareTo(inicio) >= 0 && h.compareTo(fim) <= 0;
    }

    /**
     * Verifica se um, outro, período de tempo interseta ou não este período
     *
     * @param outro período que se pretende verificar a interseção
     * @return se outro período intersecta o nosso
     */
    public boolean interseta(Periodo outro) {
        if (outro.fim.compareTo(inicio) <= 0) {
            return false;
        }
        return outro.inicio.compareTo(fim) < 0;
    }

    /**
     * indica se o período contém o periodo p. Um periodo contém outro se este
     * estiver completamente dentro dele
     *
     * @param p período que pode estar contido neste
     * @return se p está contido neste período
     */
    public boolean contem(Periodo p) {
        return p.inicio.compareTo(inicio) >= 0 && p.fim.compareTo(fim) <= 0;
    }

    /**
     * Junta o período p a este. A junção só é válida se os períodos se
     * intersetarem, caso contrário, não há junção
     *
     * @param p período a juntar a este
     */
    public void junta(Periodo p) {
        // podiamos returnar null se não intersetar, mas é melhor
        // atirar uma exceção e considerar esta ação um erro
        // Se o cliente quiser evitar a exceção, pode chamar ele o método interseta
        if (!interseta(p)) {
            throw new IllegalArgumentException();
        }

        if (p.inicio.compareTo(inicio) < 0) {
            inicio = p.inicio;
        }
        if (p.fim.compareTo(fim) > 0) {
            fim = p.fim;
        }
    }

    /**
     * devolve a união do período com o período p. Se os períodos não se
     * intersetarem não há união (matematicamente falando não seria verdade, pois a
     * união de dois períodos que não se intersetam seriam os dois períodos, mas
     * aqui estamos apenas a considerar um período). Se se intersetarem a união
     * indica o maior periodo de tempo possível juntando os dois
     *
     * @param p período a testar
     * @return a união do período com o período p
     */
    public Periodo uniao(Periodo p) {
        // podiamos returnar null se não intersetar, mas é melhor
        // atirar uma exceção e considerar esta ação um erro
        // Se o cliente quiser evitar a exceção, pode chamar ele o método interseta
        if (!interseta(p)) {
            throw new IllegalArgumentException();
        }
        Hora i = p.inicio.compareTo(inicio) < 0 ? p.inicio : inicio;
        Hora f = p.fim.compareTo(fim) > 0 ? p.fim : fim;
        return new Periodo(i, f);
    }

    /**
     * Indica a interseção do período com o período p, ou seja, qual o período de
     * tempo que possuem em comum. Se os períodos não se intersetarem não há
     * intesecao.
     *
     * @param p perído a testar
     * @return o período de tempo que possuem em comum
     */
    public Periodo interseccao(Periodo p) {
        // podiamos returnar null se não intersetar, mas é melhor
        // atirar uma exceção e considerar esta ação um erro
        // Se o cliente quiser evitar a exceção, pode chamar ele o método interseta
        if (!interseta(p)) {
            throw new IllegalArgumentException();
        }

        Hora i = p.inicio.compareTo(inicio) > 0 ? p.inicio : inicio;
        Hora f = p.fim.compareTo(fim) < 0 ? p.fim : fim;
        return new Periodo(i, f);
    }

    /**
     * devolve a hora final
     *
     * @return a hora final
     */
    public Hora getFim() {
        return fim;
    }

    /**
     * altera a hora final
     *
     * @param fim a nova hora final
     */
    public void setFim(Hora fim) {
        if (fim.compareTo(inicio) < 0) {
            throw new IllegalArgumentException();
        }
        this.fim = fim;
    }

    /**
     * devolve a hora inicial
     *
     * @return a hora inicial
     */
    public Hora getInicio() {
        return inicio;
    }

    /**
     * altera a hora inicial
     *
     * @param ini a nova hora inicial
     */
    public void setInicio(Hora ini) {
        if (ini.compareTo(fim) > 0) {
            throw new IllegalArgumentException();
        }
        this.inicio = ini;
    }

    /**
     * retorna o Período em forma de String
     *
     * @return o Período em forma de String
     */
    @Override
    public String toString() {
        return "[ " + inicio + " - " + fim + " ]";
    }
}
