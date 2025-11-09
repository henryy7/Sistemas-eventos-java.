import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaEventos {
    private List<Evento> eventos = new ArrayList<>();
    private Usuario usuario;

    public void iniciar() {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Cadastro de Usuário ===");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Cidade: ");
        String cidade = sc.nextLine();
        usuario = new Usuario(nome, cidade);

        int opcao;
        do {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Cadastrar evento");
            System.out.println("2. Listar eventos");
            System.out.println("3. Participar de evento");
            System.out.println("4. Cancelar participação");
            System.out.println("5. Meus eventos");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> cadastrarEvento(sc);
                case 2 -> listarEventos();
                case 3 -> participarEvento(sc);
                case 4 -> cancelarParticipacao(sc);
                case 5 -> usuario.listarParticipacoes();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
        sc.close();
    }

    private void cadastrarEvento(Scanner sc) {
        System.out.println("\n=== Cadastrar Evento ===");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Endereço: ");
        String endereco = sc.nextLine();
        System.out.print("Categoria: ");
        String categoria = sc.nextLine();
        System.out.print("Data e hora (dd/MM/yyyy HH:mm): ");
        String dataHora = sc.nextLine();
        System.out.print("Descrição: ");
        String descricao = sc.nextLine();

        LocalDateTime horario = LocalDateTime.parse(dataHora, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        Evento e = new Evento(nome, endereco, categoria, horario, descricao);
        eventos.add(e);
        System.out.println("Evento cadastrado com sucesso!");
    }

    private void listarEventos() {
        if (eventos.isEmpty()) {
            System.out.println("Nenhum evento cadastrado.");
        } else {
            System.out.println("\n=== Lista de Eventos ===");
            for (int i = 0; i < eventos.size(); i++) {
                System.out.println((i + 1) + " - " + eventos.get(i).getNome());
            }
        }
    }

    private void participarEvento(Scanner sc) {
        listarEventos();
        if (eventos.isEmpty()) return;
        System.out.print("Escolha o número do evento: ");
        int i = sc.nextInt() - 1;
        if (i >= 0 && i < eventos.size()) {
            usuario.participar(eventos.get(i));
        } else {
            System.out.println("Evento inválido!");
        }
    }

    private void cancelarParticipacao(Scanner sc) {
        listarEventos();
        if (eventos.isEmpty()) return;
        System.out.print("Escolha o número do evento para cancelar: ");
        int i = sc.nextInt() - 1;
        if (i >= 0 && i < eventos.size()) {
            usuario.cancelar(eventos.get(i));
        } else {
            System.out.println("Evento inválido!");
        }
    }
}