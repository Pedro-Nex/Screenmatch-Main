package br.com.alura.screenmatch.modelos;

public class Serie extends Titulo{
    private int temporadas;
    private boolean ativa;
    private int epPorTemporada;
    private int minutoPorEp;

    public int getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(int temporadas) {
        this.temporadas = temporadas;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

    public int getEpPorTemporada() {
        return epPorTemporada;
    }

    public void setEpPorTemporada(int epPorTemporada) {
        this.epPorTemporada = epPorTemporada;
    }

    public int getMinutoPorEp() {
        return minutoPorEp;
    }

    public void setMinutoPorEp(int minutoPorEp) {
        this.minutoPorEp = minutoPorEp;
    }

    @Override
    public int getDuracaoEmMinutos() {
        return temporadas * epPorTemporada * minutoPorEp;
    }
}
