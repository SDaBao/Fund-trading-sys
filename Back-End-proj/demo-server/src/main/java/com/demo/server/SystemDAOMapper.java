package com.demo.server;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.mapping.StatementType;

@Mapper
public interface SystemDAOMapper {
    @Insert("INSERT INTO mrkt_info (mrkt_time, prdct_id, prdct_val)" +
            "SELECT #{currDate}, prdct_id, prdct_val*(RAND()*0.2+0.9) FROM mrkt_info where mrkt_time=#{lastDate}")
    void importMarket(@Param("currDate")String currDate, @Param("lastDate")String lastDate);

    @Options(statementType = StatementType.CALLABLE)
    @Select("CALL systemClear(#{date})")
    void systemClear(@Param("date")String date);

    @Options(statementType = StatementType.CALLABLE)
    @Select("CALL systemRollback(#{date})")
    void systemRollback(@Param("date")String date);
}
