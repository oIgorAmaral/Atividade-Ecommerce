/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.sistemaecommerce;

import com.mycompany.dao.RepositorioDeProdutosMySQL;
import com.mycompany.model.Produto;
import java.util.List;

public class SistemaEcommerce {

    public static void main(String[] args) {

        RepositorioDeProdutosMySQL repositorio = new RepositorioDeProdutosMySQL();

        System.out.println("Iniciando sistema de E-commerce...");
        System.out.println("------------------------------------");

        try {
            System.out.println("Buscando produtos no banco de dados...");

            List<Produto> produtos = repositorio.listarTodos();

            listarProdutos(produtos);

        } catch (Exception e) {

            System.err.println("\n!!! OCORREU UM ERRO NA APLICACAO !!!");
            System.err.println("Erro: " + e.getMessage());

        }

        System.out.println("\n------------------------------------");
        System.out.println("Sistema finalizado.");
    }

    private static void listarProdutos(List<Produto> produtos) {
        System.out.println("\n PRODUTOS DISPONIVEIS: ");

        if (produtos == null || produtos.isEmpty()) {
            System.out.println("Nenhum produto encontrado no estoque.");
            return;
        }
        /*Utilizei esse formato, apenas para alinhar as colunas e deixar mais claras professor,já que representa apenas a largura!*/
        System.out.printf("%-5s | %-30s | %-15s | %s%n", "ID", "Nome", "Preco", "Estoque");
        System.out.println("-------------------------------------------------------------------------");

        for (Produto produto : produtos) {
            System.out.printf("%-5d | %-30s | R$ %-13.2f | %d unidades%n",
                    produto.getId(),
                    produto.getNome(),
                    produto.getPreco(),
                    produto.getQuantidadeEstoque()
            );
        }
    }
}