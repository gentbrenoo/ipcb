package poo.tempo;

import java.time.LocalTime;

/**
 * Representa uma Hora com os campos horas, minutos e segundos
 */
public class Hora {

    public static final int SEGS_POR_MINUTO = 60;
    public static final int MINS_POR_HORA = 60;
    public static final int SEGS_POR_HORA = MINS_POR_HORA * SEGS_POR_MINUTO; // o compilador que faça a conta :-)
    public static final int HORAS_POR_DIA = 24;
    public static final int SEGS_POR_DIA = HORAS_POR_DIA * SEGS_POR_HORA;
    public static final int MINSS_POR_DIA = HORAS_POR_DIA * MINS_POR_HORA;

    private int horas;
    private int minutos;
    private int segundos;

    /**
     * Cria uma hora com uma dada hora, minutos e segundos
     * 
     * @param h as horas
     * @param m os minutos
     * @param s os segundos
     */
    public Hora(int h, int m, int s) {
        horas = checkHoras(h);
        minutos = checkMinutos(m);
        segundos = checkSegundos(segundos);
    }

    /**
     * Cria uma hora com a hora atual
     */
    public Hora() {
        LocalTime lc = LocalTime.now();
        horas = lc.getHour();
        minutos = lc.getMinute();
        segundos = lc.getSecond();
    }

    /**
     * Cria uma hora com um total de segundos
     * 
     * @param totalSegundos
     */
    public Hora(int totalSegundos) {
        // verificar se nº de segundos não é inválido
        // para este teste, não precisamos de checker porque é só usado uma vez
        if (totalSegundos < 0 || totalSegundos >= SEGS_POR_DIA) {
            throw new IllegalArgumentException();
        }

        horas = totalSegundos / SEGS_POR_HORA;
        minutos = (totalSegundos % SEGS_POR_HORA) / SEGS_POR_MINUTO;
        segundos = (totalSegundos % SEGS_POR_HORA) % SEGS_POR_MINUTO;
    }

    /**
     * Cria uma hora a partir de uma string com o formato horas:minutos:segundos
     * 
     * @param hStr a string formatada com a hora
     */
    public Hora(String hStr) {
        // ler a string no formato horas:minutos:segundos
        String str[] = hStr.split(":");

        // verificar se valores são válidos
        horas = checkHoras(Integer.parseInt(str[0]));
        minutos = checkMinutos(Integer.parseInt(str[1]));
        segundos = checkSegundos(Integer.parseInt(str[2]));
    }

    /**
     * Soma horas a esta hora. Retorna quantas vezes passaram de 24 horas (nº de
     * dias)
     * 
     * @param numHoras número de horas as somar
     * @return quantos dias passaram
     */
    public int somaHoras(int numHoras) {
        // Este método é só para somar, logo só se aceitam horas positivas
        if (numHoras < 0) {
            throw new IllegalArgumentException();
        }

        // para garantir que o nº de horas está entre 0 e 23 aceita-se apenas o resto
        // da divisão por 24
        int totalHoras = horas + numHoras;
        horas = totalHoras % HORAS_POR_DIA;
        return totalHoras / HORAS_POR_DIA;
    }

    /**
     * Soma minutos a uma hora. Retorna o número de dias que passaram.
     * 
     * @param numMinutos número de minutos a soma à hora
     * @return quantos dias passaram
     */
    public int somaMinutos(int numMinutos) {
        // Este método é só para somar, logo só se aceitam minutos positivos
        if (numMinutos < 0) {
            throw new IllegalArgumentException();
        }

        // somar os novos minutos ao já existentes
        minutos += numMinutos;
        // se a soma dos minutos superar os 59 tem-se de somar horas,
        // tantas quantas couberem dentro dos minutos somados
        int nDias = somaHoras(minutos / SEGS_POR_MINUTO);
        // garantir que minutos está entre 0 e 59
        minutos = minutos % SEGS_POR_MINUTO;
        return nDias;
    }

    /**
     * Soma segundos a uma hora. Retorna quantos dias passaram
     * 
     * @param numSegundos número de segundos a somar
     * @return quantos dias passaram
     */
    public int somaSegundos(int numSegundos) {
        // Este método é só para somar, logo só se aceitam segundos positivos
        if (numSegundos < 0) {
            throw new IllegalArgumentException();
        }

        // procedimento semelhante aos minutos
        int seg = segundos + numSegundos;
        int nDias = somaMinutos(seg / SEGS_POR_MINUTO);
        segundos = (int) (seg % SEGS_POR_MINUTO);
        return nDias;
    }

    /**
     * método que retorna o nº de segundos desde a meia-noite desse dia
     * 
     * @return retorna o nº de segundos desde a meia-noite desse dia
     */
    public int totalSegundos() {
        return horas * SEGS_POR_HORA + minutos * SEGS_POR_MINUTO + segundos;
    }

    /**
     * Compara uma hora com outra. Retorna < 0 se este hora for menor que a outra, 0
     * se forem iguais e 0 se este for maior que a outra
     * 
     * @param outra a hora com que se vai comparar
     * @return <0 se for menor, 0 se for igual e >0 se for maior
     */
    public int compareTo(Hora outra) {
        if (horas != outra.horas) {
            return horas - outra.horas;
        }
        if (minutos != outra.minutos) {
            return minutos - outra.minutos;
        }
        return segundos - outra.segundos;
    }

    /**
     * Retorna a diferença em segundos entre duas horas
     * 
     * @param outra a hora com que se vai calcular a diferença
     * @return a diferença em segundos entre as duas horas
     */
    public int diferencaSegs(Hora outra) {
        return totalSegundos() - outra.totalSegundos();
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = checkHoras(horas);
    }

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = checkMinutos(minutos);
    }

    public int getSegundos() {
        return segundos;
    }

    public void setSegundos(int segundos) {
        this.segundos = checkSegundos(segundos);
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d:%02d", horas, minutos, segundos);
    }

    @Override
    public Hora clone() {
        return new Hora(horas, minutos, segundos);
    }

    private int checkHoras(int horas) {
        if (horas < 0 || horas >= HORAS_POR_DIA) {
            throw new IllegalArgumentException();
        }
        return horas;
    }

    private int checkMinutos(int minutos) {
        if (minutos < 0 || minutos >= MINS_POR_HORA) {
            throw new IllegalArgumentException();
        }
        return minutos;
    }

    private int checkSegundos(int segundos) {
        if (segundos < 0 || segundos >= SEGS_POR_MINUTO) {
            throw new IllegalArgumentException();
        }
        return segundos;
    }

}
