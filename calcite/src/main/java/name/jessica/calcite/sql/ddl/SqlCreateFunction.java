package name.jessica.calcite.sql.ddl;

import org.apache.calcite.sql.SqlCreate;
import org.apache.calcite.sql.SqlIdentifier;
import org.apache.calcite.sql.SqlKind;
import org.apache.calcite.sql.SqlNode;
import org.apache.calcite.sql.SqlOperator;
import org.apache.calcite.sql.SqlSpecialOperator;
import org.apache.calcite.sql.SqlWriter;
import org.apache.calcite.sql.parser.SqlParserPos;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SqlCreateFunction extends SqlCreate {
	private final SqlIdentifier name;
	private final SqlNode className;

	private static final SqlSpecialOperator OPERATOR =
			new SqlSpecialOperator("CREATE FUNCTION", SqlKind.CREATE_FUNCTION);
	public SqlCreateFunction(SqlParserPos pos,
							 boolean replace,
							 boolean ifNotExists,
							 SqlIdentifier name,
	SqlNode className) {
		super(OPERATOR, pos, replace, ifNotExists);
		this.name = Objects.requireNonNull(name);
		this.className = className;

	}

	@Override public void unparse(SqlWriter writer, int leftPrec,
								  int rightPrec) {
		writer.keyword(getReplace() ? "CREATE OR REPLACE" : "CREATE");
		writer.keyword("FUNCTION");
		if (ifNotExists) {
			writer.keyword("IF NOT EXISTS");
		}
		name.unparse(writer, 0, 0);
		writer.keyword("AS");
		className.unparse(writer, 0, 0);
	}

	@Override
	public SqlOperator getOperator() {
		return OPERATOR;
	}

	@Override
	public List<SqlNode> getOperandList() {
		return Arrays.asList(name,className);
	}
}
