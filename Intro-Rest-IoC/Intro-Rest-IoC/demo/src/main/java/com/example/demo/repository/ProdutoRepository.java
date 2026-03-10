package com.example.demo.repository;

import com.example.demo.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    // A interface disponibiliza várias definições prontas como save(), findAll(), findById(), delete()
    // Também suporta a criação de métodos de consulta derivados do nome do campo (findByNome)

    // O Spring gerará uma consulta SQL utilizando o operação LIKE: WHERE nome LIKE %...%
    // SELECT * FROM produto WHERE nome LIKE %...% (case sensitive)
    List<Produto> findByNomeContaining(String nome);

    @Query(value = """
    SELECT * FROM produto
    WHERE preco <= :precoLimite
    ORDER BY preco ASC
    """, nativeQuery = true)
    List<Produto> buscarPorPrecoLimiteComSQL(@Param("precoLimite") Double precoLimite);
}