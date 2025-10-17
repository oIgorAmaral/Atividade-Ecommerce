/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

import com.mycompany.model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepositorioDeProdutosMySQL {

    private final ConexaoBD conexaoBD;

    public RepositorioDeProdutosMySQL() {
        this.conexaoBD = new ConexaoMySQL();
    }

  
    public List<Produto> listarTodos() throws Exception {

        Connection conexao = null;
        PreparedStatement stmt = null; 
        ResultSet rs = null;    

  
        List<Produto> produtos = new ArrayList<>();


        try {

            conexao = conexaoBD.obterConexao();

 
            String sql = "SELECT id, nome, descricao, preco, quantidade_estoque, data_cadastro FROM produtos";
            stmt = conexao.prepareStatement(sql);


            rs = stmt.executeQuery();


            while (rs.next()) {

                Produto produto = new Produto();


                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPreco(rs.getBigDecimal("preco"));
                produto.setQuantidadeEstoque(rs.getInt("quantidade_estoque"));
                produto.setDataCadastro(rs.getTimestamp("data_cadastro"));

                produtos.add(produto);
            }
        } catch (SQLException e) {

            System.err.println("Erro ao listar produtos: " + e.getMessage());
 
            throw new Exception("Falha ao consultar os produtos no banco de dados.", e);
        } finally {

            fecharRecursos(conexao, stmt, rs);
        }


        return produtos;
    }

    private void fecharRecursos(Connection conn, PreparedStatement stmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao fechar o ResultSet: " + e.getMessage());
        }
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao fechar o PreparedStatement: " + e.getMessage());
        }

        if (conn != null) {
            conexaoBD.fecharConexao(conn);
        }
    }
}