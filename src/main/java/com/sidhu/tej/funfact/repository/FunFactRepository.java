package com.sidhu.tej.funfact.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;

@Repository
public class FunFactRepository {

    private static final String CATEGORY_COUNT_SQL = "select count(*) from dbo.fun_facts where category = :cat";

    private static final String FUN_FACT_SQL = " " +
            " with vw as ( " +
            " select category, fun_fact, " +
            "        row_number() over (partition by category order by category) seq_nbr " +
            "   from fun_facts " +
            "  where lower(category) = lower(:cat) " +
            ") " +
            " select fun_fact from vw where seq_nbr = :seq_nbr ";

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public int getCategoryFactCount (String category) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("cat", category);
        return namedParameterJdbcTemplate.queryForObject(CATEGORY_COUNT_SQL, parameterSource, Integer.class);
    }

    public String getFact (String category, int seqNbr) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("cat", category);
        parameterSource.addValue("seq_nbr", seqNbr);
        return namedParameterJdbcTemplate.queryForObject(FUN_FACT_SQL, parameterSource, String.class);
    }
}
