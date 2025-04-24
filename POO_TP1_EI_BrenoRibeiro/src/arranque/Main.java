package arranque;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import cliente.Cartao;
import cliente.Cupao;
import comercio.Inventario;
import comercio.ProdutoInfo;
import menu.JanelaCartao;
import menu.JanelaCompra;

public class Main {

        public static void main(String[] args) {
                // criar o inventário e restantes elementos necessário à aplicação
                Inventario inventario = new Inventario();
                criarProdutos(inventario);
                criarCupoes(inventario);
                criarCartoes(inventario);

                // criar e apresentar as janelas da aplicação
                SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                                JanelaCompra jc = new JanelaCompra("Loja HonESta de Castelo Branco", inventario);
                                jc.setLocation(20, 20);
                                jc.setVisible(true);
                                // TODO FEITO passar uma coleção de cartões para o inventário (em vez da lista vazia) 
                                JanelaCartao jcard = new JanelaCartao(inventario.getCartoes());
                                jcard.setLocation(20 + jc.getWidth() + 20, 20);
                                jcard.setVisible(true);
                        }
                });
        }

        /**
         * Cria os produtos e adiciona-os ao inventário
         * 
         * @param inventario onde colocoar os produtos
         */
        private static void criarProdutos(Inventario inventario) {

                List<ProdutoInfo> produtos = new ArrayList<ProdutoInfo>(List.of(
                                new ProdutoInfo("123-001", "EST", "BarraMix maçã canela", 89),
                                new ProdutoInfo("123-002", "EST", "CeriCrisp", 149),
                                new ProdutoInfo("123-003", "EST", "Sumix Limonada", 149),
                                new ProdutoInfo("123-004", "EST", "Chocolate com amêndoas", 149),
                                new ProdutoInfo("123-005", "EST", "Arroz agulha", 109),
                                new ProdutoInfo("123-006", "EST", "Arroz carolino", 119),
                                new ProdutoInfo("123-007", "EST", "Arroz basmati", 169),

                                new ProdutoInfo("222-001", "AlbiCereal", "Arroz Agulha", 169),
                                new ProdutoInfo("222-002", "AlbiCereal", "Arroz Carolino", 179),
                                new ProdutoInfo("222-003", "AlbiCereal", "Céu Estrelado", 219),
                                new ProdutoInfo("222-004", "AlbiCereal", "Aveia crunch", 249),
                                new ProdutoInfo("222-005", "AlbiCereal", "Massa esparguete", 99),
                                new ProdutoInfo("222-006", "AlbiCereal", "Massa macarronete", 109),

                                new ProdutoInfo("301-001", "DoceVida", "Chocolate de leite", 149),
                                new ProdutoInfo("301-002", "DoceVida", "Chocolate com avelã", 169),
                                new ProdutoInfo("301-003", "DoceVida", "Chocolate negro 70%", 189),

                                new ProdutoInfo("404-001", "Refrescate", "Sumo de maçã", 139),
                                new ProdutoInfo("404-002", "Refrescate", "Sumo de laranja", 139),
                                new ProdutoInfo("404-003", "Refrescate", "Sumo de ananás", 139),

                                new ProdutoInfo("505-001", "Frutas", "Goiaba", 179),
                                new ProdutoInfo("505-002", "Frutas", "Jabuticaba", 141)));
                inventario.adicionarProdutos(produtos);
        }

        /**
         * Cria e configura os cupões e adiciona-os ao inventário
         * 
         * @param inventario onde colocoar os cupões
         */
        private static void criarCupoes(Inventario inventario) {
                List<Cupao> cupoes = new ArrayList<Cupao>(List.of(
                                new Cupao("1001", 50, "em refrigerantes",
                                                LocalDate.now(), LocalDate.now().plusDays(7),
                                                new ArrayList<ProdutoInfo>(List.of(
                                                                inventario.getProduto("123-003"),
                                                                inventario.getProduto("404-001"),
                                                                inventario.getProduto("404-002"),
                                                                inventario.getProduto("404-003")))),

                                new Cupao("1002", 25, "em chocolates",
                                                LocalDate.now(), LocalDate.now().plusDays(7),
                                                new ArrayList<ProdutoInfo>(List.of(
                                                                inventario.getProduto("123-004"),
                                                                inventario.getProduto("301-001"),
                                                                inventario.getProduto("301-002"),
                                                                inventario.getProduto("301-003")))),

                                new Cupao("1003", 25, "em massa",
                                                LocalDate.now(), LocalDate.now().plusDays(7),
                                                new ArrayList<ProdutoInfo>(List.of(
                                                                inventario.getProduto("222-005"),
                                                                inventario.getProduto("222-006")))),

                                new Cupao("1004", 25, "em arroz",
                                                LocalDate.now(), LocalDate.now().plusDays(7),
                                                new ArrayList<ProdutoInfo>(List.of(
                                                                inventario.getProduto("123-005"),
                                                                inventario.getProduto("123-006"),
                                                                inventario.getProduto("123-007"),
                                                                inventario.getProduto("222-001"),
                                                                inventario.getProduto("222-002")))),

                                new Cupao("1005", 15, "na marca EST",
                                                LocalDate.now(), LocalDate.now().plusDays(7),
                                                new ArrayList<ProdutoInfo>(List.of(
                                                                inventario.getProduto("123-001"),
                                                                inventario.getProduto("123-002"),
                                                                inventario.getProduto("123-003"),
                                                                inventario.getProduto("123-004"),
                                                                inventario.getProduto("123-005"),
                                                                inventario.getProduto("123-006"),
                                                                inventario.getProduto("123-007")))),

                                new Cupao("1006", 25, "em cereais de pequeno-almoço",
                                                LocalDate.now().plusDays(8), LocalDate.now().plusDays(15),
                                                new ArrayList<ProdutoInfo>(List.of(
                                                                inventario.getProduto("123-002"),
                                                                inventario.getProduto("222-003"),
                                                                inventario.getProduto("222-004")))),
                                new Cupao("1007", 25, "em frutas",
                                                LocalDate.now().plusDays(8), LocalDate.now().plusDays(15),
                                                new ArrayList<ProdutoInfo>(List.of(
                                                                inventario.getProduto("505-001"),
                                                                inventario.getProduto("505-002"))))));
                inventario.adicionarCupoes(cupoes);

        }

        /**
         * Cria e configura os cartões e adiciona-os ao inventário
         * 
         * @param inventario onde colocoar os cartões
         */
        private static void criarCartoes(Inventario inventario) {
                List<Cartao> cartoes = new ArrayList<Cartao>(List.of(
                                new Cartao("10101", 0),
                                new Cartao("20202", 250),
                                new Cartao("30303", 58)));
                inventario.adicionarCartoes(cartoes);
                inventario.associarCupao("10101", "1001");
                inventario.associarCupao("10101", "1003");
                inventario.associarCupao("10101", "1005");
                inventario.associarCupao("10101", "1006");
                inventario.associarCupao("10101", "1007");

                inventario.associarCupao("20202", "1001");
                inventario.associarCupao("20202", "1002");
                inventario.associarCupao("20202", "1004");
                inventario.associarCupao("20202", "1006");
                inventario.associarCupao("20202", "1007");

                inventario.associarCupao("30303", "1002");
                inventario.associarCupao("30303", "1003");
                inventario.associarCupao("30303", "1004");
                inventario.associarCupao("30303", "1005");
                inventario.associarCupao("30303", "1006");
                inventario.associarCupao("30303", "1007");
                
        }
}