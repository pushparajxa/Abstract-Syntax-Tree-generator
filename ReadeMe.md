As part of the Structure of Programming langauges Course, we implemented lexer, parser and Abstract Syntax Tree builder for the language whose lexical elements are below.

Comments and white spaces:
	/* and */		Single line comment // 
		\n \b \t \r	White spaces


Identifiers:
	Case sensitive
	

can be of any length . They start with a letter and can contain _ and digits.

Integer Literals:
	One or more digits optionally preceded with a '+' or a '-'
	(Return '+' or '-' if present as a separate token)
Real Literals:
	Integer followed by a '.' followed by zero or more digits
Character literals:
	Printable characters, including alphabets and digits in single quotes.
	Escape characters such as '\n','\r','\t','\\','\"'
String literals:
	Enclosed in double quotes
	Cannot include new lines
Keywords:
Class,  int, real, char, String, if,  else, while, new
All the key words must be separated by a space. 


/** Token List **
you may use enemerated types to refer to to the tokens
TK_ID – Identifier 

* Literals *
TK_LIT_INT - Integer Data (21, 441, }
TK_LIT_REAL - Real Data   {+4.55, 2.5, -1.22 etc}
TK_LIT_CHAR - Character Data  {‘a’ ‘b’  escape chars}
TK_LIT_STRING - String Data (“SPL” etc)

* Punctuation and Operators *
TK_LPAREN - Left Parenthesis ('(')
TK_RPAREN - Right Parenthesis (')')
TK_LBRACE - Left Braces ('{')
TK_RBRACE - Right Braces ('}')
TK_LSQB - Left Square Brackets ('[')
TK_RSQB - Right Square Brackets (']')
TK_DOT – Dot ('.')
TK_COMMA - Comma Operator (',')
TK_COLON – Colon (':')
TK_SEMICOLON – Semicolon (';')
TK_PLUS - Plus Operator ('+')
TK_MINUS - Minus Operator ('-')
TK_ASTERISK - Asterisk Operator ('*')
TK_LT - Less Than ('<')
TK_GT - Greater Than ('>')
TK_LE - Less Than or Equal To ('<=')
TK_GE - Greater Than or Equal To ('>=')
TK_EQ - Equal To ('==')
TK_NEQ - Not Equal To ('<>')

* Keywords *
TK_CLASS –Class Keyword ('Class')
TK_NEW  -Keyword (‘new’)
TK_INT - Integer Keyword ('int')
TK_REAL - Real Keyword ('real')
TK_CHAR - Character Keyword ('char')
TK_STRING - String Keyword ('string')
TK_IF - If Construct ('if')
TK_WHILE - While Construct ('while')
TK_EOF - End of file Token

**/

