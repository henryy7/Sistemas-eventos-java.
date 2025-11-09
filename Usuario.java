import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nome;
    private String cidade;
    private List<Evento> eventosParticipando = new ArrayList<>();

    public Usuario(String nome, String cidade) {
        this.nome = nome;
        this.cidade = cidade;
    }

    public void participar(Evento e) {
        eventosParticipando.add(e);
        System.out.println("Você se inscreveu no evento: " + e.getNome());
    }

    public void cancelar(Evento e) {
        eventosParticipando.remove(e);
        System.out.println("Participação cancelada em: " + e.getNome());
    }

    public void listarParticipacoes() {
        if (eventosParticipando.isEmpty()) {
            System.out.println("Você não está participando de nenhum evento.");
        } else {
            System.out.println("Eventos que você participa:");
            for (Evento e : eventosParticipando) {
                System.out.println("- " + e.getNome());
            }
        }
    }
}