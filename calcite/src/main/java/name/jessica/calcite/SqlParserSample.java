package name.jessica.calcite;

import org.apache.calcite.config.Lex;
import org.apache.calcite.sql.SqlNode;
import org.apache.calcite.sql.dialect.OracleSqlDialect;
import org.apache.calcite.sql.parser.SqlParseException;
import org.apache.calcite.sql.parser.SqlParser;

public class SqlParserSample {
	public static void main(String[] args) throws SqlParseException {
		// Sql语句
		String sql = "select * from tables where id = 1";
		// 解析配置
		SqlParser.Config javaConfig = SqlParser.configBuilder().setLex(Lex.JAVA).build();
		// 创建解析器
		SqlParser parser = SqlParser.create(sql, javaConfig);
		// 解析sql
		SqlNode sqlNode = parser.parseQuery();
		// 还原某个方言的SQL
		System.out.println(sqlNode.toSqlString(OracleSqlDialect.DEFAULT));
	}

}
