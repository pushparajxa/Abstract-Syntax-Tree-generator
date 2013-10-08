import java.io.*;
import java.util.*;
class a 
{
static int linenumber=1;
static double scope =0.0;//1.0is reserved for main method;//a.scope i.e 0.0 is global scope
static Hashtable symboltable = new Hashtable(); 
static char buffer[]= new char[100];
static int current_pointer=0;
static long size=0;
static RandomAccessFile fr;
static RandomAccessFile out;
static boolean error = false;
static String token;
static String lexeme;
static String previous_token_lexeme;
static double previous_scope =0.0;
/*public static void main(String args[]) throws IOException
{
a a1 = new a();
root_node rootnode = new root_node();
a1.nexttoken();
/*
if(token.equals("TK_INT") || token.equals("TK_REAL") ||token.equals("TK_CHAR")|| token.equals("TK_STRING"))
{
Declarations d = rootnode.addchild(rootnode,"Declarations");
}

else if(token.equals("TK_ARRAY"))
{
ArrayDeclarations a1 = rootnode.addchild(rootnode,"ArrayDeclarations");
}

else if(token.equals("TK_FUNCTION"))
{


}*/
//}end of main*/
static void  nexttoken() throws IOException , FileNotFoundException 
{
int i; 
char c;
fr = new RandomAccessFile("file.txt","r");
int j=0;
//while(1==1)
//{
fr.seek(size);//to test for end of file
i = fr.read();
c = (char)i;
if(error == true)
{
System.out.println("error at linenumber "+linenumber);
return;
}

else if(i =='$')
{
System.out.println("TK_EOF");
token = "TK_EOF";
return;
}
else {

gettoken();
}

//}

}//nexttoken


static void gettoken() throws FileNotFoundException,IOException 
{
//System.out.println("enterd gettoken");
int state=1;
RandomAccessFile fr = new RandomAccessFile("file.txt","r");
out = new RandomAccessFile("output.txt","rwd");
fr.seek(size);
int i1;
char c; 
int flag=0;
int j=0;
first: while(1==1)
{
i1 = fr.read();
c = (char)i1;

	switch(state)
	{	
	
			//START OF INT
	case 1:
		if(c == 'i'){state = 2; buffer[current_pointer] = c;current_pointer++; size= size+1;break;}
		
		if(c == ' '){ state = 1;size++;break;}
		if(i1 == 9){ state = 1;size++;break;}//horizantal tab
		if(i1 == 10){ state = 1;size++;break;}//LIne fedd
		if(i1 ==11){ state = 1;size++;break;}//vertical tab
		if(i1 ==13){linenumber++; state = 1;size++;break;}//carriage return
		
		if(c == 'c'){state = 8; buffer[current_pointer] = c;current_pointer++; size= size+1;break;}
	
	    if(c == 'r'){state = 13; buffer[current_pointer] = c;current_pointer++; size= size+1;break;}
	
		if(c == 's'){state = 18; buffer[current_pointer] = c;current_pointer++; size= size+1;break;}
		
		if(c == 'w'){state = 25; buffer[current_pointer] = c;current_pointer++; size= size+1;break;}
		
		if(c == 'v'){state = 80; buffer[current_pointer] = c;current_pointer++; size= size+1;break;}
		
		if(c == 'a'){state = 92; buffer[current_pointer] = c;current_pointer++; size= size+1;break;}

		if(c == 'f'){state = 98; buffer[current_pointer] = c;current_pointer++; size= size+1;break;}
		
		if(c == 'm'){state = 112; buffer[current_pointer] = c;current_pointer++; size= size+1;break;}		
		
		if(c == '<'){state = 43; buffer[current_pointer] = c;current_pointer++; size= size+1;break;}
		
		if(c == '>'){state = 46; buffer[current_pointer] = c;current_pointer++; size= size+1;break;}
		
		if(c == '='){state = 49; buffer[current_pointer] = c;current_pointer++; size= size+1;break;}
		
		if(c == '('){ size= size+1; token = "TK_LPAREN"; System.out.println("TOKEN NAME is TK_LPAREN and lexeme is ("); return;}
		
		
		if(c == ')'){ size= size+1;token = "TK_RPAREN"; System.out.println("TOKEN NAME is TK_RPAREN and lexeme is )"); return;}
		
		if(c == '{'){ size= size+1; token = "TK_LBRACE"; /*scope = scope+0.1;*/ System.out.println("TOKEN NAME is TK_LBRACE and lexeme is { "+linenumber); return;}
		
		
		if(c == '}'){ size= size+1; token = "TK_RBRACE"; System.out.println("TOKEN NAME is TK_RBRACE and lexeme is }"); return;}
		
		if(c == '['){ size= size+1; token = "TK_LSQB";System.out.println("TOKEN NAME is TK_LSQB and lexeme is ["); return;}
		
		
		if(c == ']'){ size= size+1; token = "TK_RSQB";System.out.println("TOKEN NAME is TK_RSQB and lexeme is ]"); return;}
		
		if(c == ','){ size= size+1;token = "TK_COMMA"; System.out.println("TOKEN NAME is TK_COMMA and lexeme is ,"); return;}
		
		if(c == ';'){ size= size+1;token = "TK_SEMICOLON"; System.out.println("TOKEN NAME is TK_SEMICOLON and lexeme is ;"); return;}
		
		if(c == ':'){ size= size+1; token = "TK_COLON";System.out.println("TOKEN NAME is TK_COLON and lexeme is :"); return;}
		
		
		if(c == '.'){ size= size+1;token = "TK_DOT"; System.out.println("TOKEN NAME is TK_DOT and lexeme is ."); return;}
		
		
		if(c == '*'){ size= size+1;token = "TK_ASTERISK"; System.out.println("TOKEN NAME is TK_ASTERISK and lexeme is *"); return;}
		
		
		if(c == '\''){state = 59; token = "\'";buffer[current_pointer] = c;current_pointer++; size= size+1;break;}	
		
		if(c == '\"'){state = 65; token = "\"";buffer[current_pointer] = c;current_pointer++; size= size+1;break;}	
		
		if(c == '/'){state = 68;token = "/"; buffer[current_pointer] = c;current_pointer++; size= size+1;break;}	
		
		if(Character.isLetter(c)){state = 7;buffer[current_pointer] = c;current_pointer++; size= size+1;break;}
		
		if(c == '+'){ token = "TK_PLUS";System.out.println("Token is TK_PLUS and lexeme is"+token); size= size+1;return;}
	
		if(c == '-'){ token = "TK_MINUS";System.out.println("Token is TK_MINUS and lexeme is"+token); size= size+1;return;}
		
		if(Character.isDigit(c))
		{
		fr.seek(size-1);
		int j3 = fr.read();
		char c3 = (char)j3;
		if(c3=='+' || c3=='-')
		{
		buffer[current_pointer]=c3; current_pointer++;
		state = 55; 
		fr.seek(size);
		int j4 = fr.read();//to get the cursor to the right point;
		buffer[current_pointer] = c;current_pointer++; size= size+1;break;
		}
		else
		{
		fr.seek(size);
		int j4 = fr.read();//to get the cursor to the right point since we sought till size-1  above
		state = 55; 
		buffer[current_pointer] = c;current_pointer++; size= size+1;break;
		}
		}
	
		if(c == '$'){return;}
	
			//START of INT
	case 2:
		if( c== 'n'){state = 3; buffer[current_pointer] = c; current_pointer++; size = size+1; break;}
		if( c== 'f'){state = 74; buffer[current_pointer] = c; current_pointer++; size = size+1; break;}
		
		if(Character.isLetterOrDigit(c))
		{
		state = 7; buffer[current_pointer]= c;current_pointer++;size++;break;
		}
		if(!Character.isLetterOrDigit(c))
		{
		String s = new String(buffer,0,current_pointer);
	token = "TK_ID";
		System.out.println("Token name is Token_ID and lexeme is"+" "+ token);
		current_pointer =0;
		return;		
		}
		
	case 3:
	    if(c == 't'){state = 4; buffer[current_pointer] = c; current_pointer++; size = size+1; 			break;}
		if(Character.isLetterOrDigit(c))
		{
		state = 7; buffer[current_pointer]= c;current_pointer++;size++;break;
		}
		if(!Character.isLetterOrDigit(c))
		{
		String s = new String(buffer,0,current_pointer);
	token = "TK_ID";
		System.out.println("Token name is Token_ID and lexeme is"+" "+ token);
		current_pointer =0;
		return;		
		}
			
	case 4:
		
		if( c== ' ')
		{ 
		String s = new String(buffer,0,current_pointer);
		size = size+1;
		token = "TK_INT";
		System.out.println("TOKEN IS ::TK_INT and lexeme is "+" "+ s+" "+"line number is"+" "+linenumber);
		/*s=s+Double.toString(scope);
		symbol_table_entry s1 = new symbol_table_entry(s,scope);
		if(symboltable.contains(s)==true)
		{
		error = true;
		System.out.println("variable already present in the given scope");
		}
		else//insert into hash table
		{
		symboltable.put(s,s1);
		}*/
		byte arr[] = s.getBytes();
		out.write(arr);
		current_pointer = 0;
		flag = 1;
		return;
		}
		if(Character.isLetterOrDigit(c))
		{
		state = 7; buffer[current_pointer]= c;current_pointer++;size++;break;
		}
		if(!Character.isLetterOrDigit(c))
		{
		String s = new String(buffer,0,current_pointer);
		token = s;
		System.out.println("KEYWORD ERROR Token name is TK_ID and lexeme is"+" "+ s);
		current_pointer =0;
		return;		
		}
				
		
		//END of INT
		
		//START of IF
		case 74:
		if(c == ' ')
		{
		String s = new String(buffer,0,current_pointer);
		token = s;
		token = "TK_IF";
		System.out.println("TOKEN NAME is Token_IF and lexeme is"+" "+ s);
		current_pointer = 0;
		return;		
		}
		
		if(Character.isLetterOrDigit(c))
		{
		state = 7; buffer[current_pointer]= c;current_pointer++;size++;break;
		}
		
		if(!Character.isLetterOrDigit(c))
		{
		String s = new String(buffer,0,current_pointer);
		token = "TK_ID";
		System.out.println("KEYWORD ERROR Token name is TK_IF and lexeme is"+" "+ s);
		current_pointer =0;
		return;		
		}
		
		//END of IF
		
		case 7:
		if(Character.isLetterOrDigit(c) || c=='_')
		{
		state = 7;
		buffer[current_pointer]= c;current_pointer++;size++;break;		
		}
		if(!(Character.isLetterOrDigit(c) || c=='_'))
		{
		String s = new String(buffer,0,current_pointer);
		a.lexeme = s;
		token = "TK_ID";
		System.out.println("TOKEN IS ::TK_ID and lexeme is "+" "+ s);
		current_pointer =0;
		return;
		}
		
		//  START of CHAR
		case 8:
		
		if( c== 'h'){state = 9; buffer[current_pointer] = c; current_pointer++; size = size+1; 			break;}
		if(Character.isLetterOrDigit(c))
		{
		state = 7; buffer[current_pointer]= c;current_pointer++;size++;break;
		}
		if(!Character.isLetterOrDigit(c))
		{
		String s = new String(buffer,0,current_pointer);
		token = "TK_ID";
		System.out.println("Token name is Token_ID and lexeme is"+" "+" "+ s);
		current_pointer =0;
		return;		
		}
		
	case 9:
	    if(c == 'a'){state = 10; buffer[current_pointer] = c; current_pointer++; size = size+1; 			break;}
		if(Character.isLetterOrDigit(c))
		{
		state = 7; buffer[current_pointer]= c;current_pointer++;size++;break;
		}
		if(!Character.isLetterOrDigit(c))
		{
		String s = new String(buffer,0,current_pointer);
		token = "TK_ID";
		System.out.println("Token name is Token_ID and lexeme is"+" "+ s);
		current_pointer =0;
		return;		
		}
			
		case 10:
	    if(c == 'r'){state = 11; buffer[current_pointer] = c; current_pointer++; size = size+1; 			break;}
		if(Character.isLetterOrDigit(c))
		{
		state = 7; buffer[current_pointer]= c;current_pointer++;size++;break;
		}
		if(!Character.isLetterOrDigit(c))
		{
		String s = new String(buffer,0,current_pointer);
	token = "TK_ID";
		System.out.println("Token name is Token_ID and lexeme is"+" " +s);
		current_pointer =0;
		return;		
		}	
			
						
		case 11:
		
		if( c== ' ')
		{ 
		String s = new String(buffer,0,current_pointer);
		size = size+1;
	token = "TK_CHAR";
		System.out.println("TOKEN IS ::TK_CHAR and lexeme is "+" "+ s);
		byte arr[] = s.getBytes();
		out.write(arr);
		current_pointer = 0;
		flag = 1;
		return;
		}
		if(Character.isLetterOrDigit(c))
		{
		state = 7; buffer[current_pointer]= c;current_pointer++;size++;break;
		}
		if(!Character.isLetterOrDigit(c))
		{
		String s = new String(buffer,0,current_pointer);
		token = "TK_ID";
		System.out.println("KEYWORD ERROR Token name is TK_ID and lexeme is"+" "+ s);
		current_pointer =0;
		return;		
		}
		
		//END of CHAR		
		
		// START of REAL
		case 13:
		
		if( c== 'e'){state = 14; buffer[current_pointer] = c; current_pointer++; size = size+1; 			break;}
		if(Character.isLetterOrDigit(c))
		{
		state = 7; buffer[current_pointer]= c;current_pointer++;size++;break;
		}
		if(!Character.isLetterOrDigit(c))
		{
		String s = new String(buffer,0,current_pointer);
		token = "TK_ID";
		System.out.println("Token name is Token_ID and lexeme is"+" "+" "+ s);
		current_pointer =0;
		return;		
		}
		
	case 14:
		if(c == 't'){state = 107; buffer[current_pointer] = c; current_pointer++; size = size+1; 			break;}
	
	    if(c == 'a'){state = 15; buffer[current_pointer] = c; current_pointer++; size = size+1; 			break;}
		if(Character.isLetterOrDigit(c))
		{
		state = 7; buffer[current_pointer]= c;current_pointer++;size++;break;
		}
		if(!Character.isLetterOrDigit(c))
		{
		String s = new String(buffer,0,current_pointer);
		token = "TK_ID";
		System.out.println("Token name is Token_ID and lexeme is"+" "+ s);
		current_pointer =0;
		return;		
		}
			
		case 15:
	    if(c == 'l'){state = 16; buffer[current_pointer] = c; current_pointer++; size = size+1; 			break;}
		if(Character.isLetterOrDigit(c))
		{
		state = 7; buffer[current_pointer]= c;current_pointer++;size++;break;
		}
		if(!Character.isLetterOrDigit(c))
		{
		String s = new String(buffer,0,current_pointer);
		token = "TK_ID";
		System.out.println("Token name is Token_ID and lexeme is"+" " +s);
		current_pointer =0;
		return;		
		}	
			
						
		case 16:
		
		if( c== ' ')
		{ 
		String s = new String(buffer,0,current_pointer);
		size = size+1;
		token = "TK_REAL";
		System.out.println("TOKEN IS ::TK_REAL and lexeme is "+" "+ s);
		byte arr[] = s.getBytes();
		out.write(arr);
		current_pointer = 0;
		flag = 1;
		return;
		}
		if(Character.isLetterOrDigit(c))
		{
		state = 7; buffer[current_pointer]= c;current_pointer++;size++;break;
		}
		if(!Character.isLetterOrDigit(c))
		{
		String s = new String(buffer,0,current_pointer);
		token = "TK_ID";
		System.out.println("KEYWORD ERROR Token name is TK_ID and lexeme is"+" "+ s);
		current_pointer =0;
		return;		
		}
		
		// END of REAL
		
		//START of STRING
		case 18:
		
		if( c== 't'){state = 19; buffer[current_pointer] = c; current_pointer++; size = size+1; 			break;}
		if(Character.isLetterOrDigit(c))
		{
		state = 7; buffer[current_pointer]= c;current_pointer++;size++;break;
		}
		if(!Character.isLetterOrDigit(c))
		{
		String s = new String(buffer,0,current_pointer);
		token = "TK_ID";
		System.out.println("Token name is Token_ID and lexeme is"+" "+" "+ s);
		current_pointer =0;
		return;		
		}
		
	case 19:
	    if(c == 'r'){state = 20; buffer[current_pointer] = c; current_pointer++; size = size+1; 			break;}
		if(Character.isLetterOrDigit(c))
		{
		state = 7; buffer[current_pointer]= c;current_pointer++;size++;break;
		}
		if(!Character.isLetterOrDigit(c))
		{
		String s = new String(buffer,0,current_pointer);
		token = "TK_ID";
		System.out.println("Token name is Token_ID and lexeme is"+" "+ s);
		current_pointer =0;
		return;		
		}
			
		case 20:
	    if(c == 'i'){state = 21; buffer[current_pointer] = c; current_pointer++; size = size+1; 			break;}
		if(Character.isLetterOrDigit(c))
		{
		state = 7; buffer[current_pointer]= c;current_pointer++;size++;break;
		}
		if(!Character.isLetterOrDigit(c))
		{
		String s = new String(buffer,0,current_pointer);
		token = "TK_ID";
		System.out.println("Token name is Token_ID and lexeme is"+" " +s);
		current_pointer =0;
		return;		
		}	
			
		case 21:
	    if(c == 'n'){state = 22; buffer[current_pointer] = c; current_pointer++; size = size+1; 			break;}
		if(Character.isLetterOrDigit(c))
		{
		state = 7; buffer[current_pointer]= c;current_pointer++;size++;break;
		}
		if(!Character.isLetterOrDigit(c))
		{
		String s = new String(buffer,0,current_pointer);
		token = "TK_ID";
		System.out.println("Token name is Token_ID and lexeme is"+" " +s);
		current_pointer =0;
		return;		
		}	

		case 22:
	    if(c == 'g'){state = 23; buffer[current_pointer] = c; current_pointer++; size = size+1; 			break;}
		if(Character.isLetterOrDigit(c))
		{
		state = 7; buffer[current_pointer]= c;current_pointer++;size++;break;
		}
		if(!Character.isLetterOrDigit(c))
		{
		String s = new String(buffer,0,current_pointer);
		token = "TK_ID";
		System.out.println("Token name is Token_ID and lexeme is"+" " +s);
		current_pointer =0;
		return;		
		}	

			
		case 23:
		
		if( c== ' ')
		{ 
		String s = new String(buffer,0,current_pointer);
		size = size+1;
		token = "TK_STRING";
		System.out.println("TOKEN IS ::TK_STRING and lexeme is "+" "+ s);
		byte arr[] = s.getBytes();
		out.write(arr);
		current_pointer = 0;
		flag = 1;
		return;
		}
		if(Character.isLetterOrDigit(c))
		{
		state = 7; buffer[current_pointer]= c;current_pointer++;size++;break;
		}
		if(!Character.isLetterOrDigit(c))
		{
		String s = new String(buffer,0,current_pointer);
		token = "TK_ID";
		System.out.println("KEYWORD ERROR Token name is TK_ID and lexeme is"+" "+ s);
		current_pointer =0;
		return;		
		}
		
		//END of STRING
		
		//START of WHILE
		case 25:
		
		if( c== 'h'){state = 26; buffer[current_pointer] = c; current_pointer++; size = size+1; 			break;}
		if(Character.isLetterOrDigit(c))
		{
		state = 7; buffer[current_pointer]= c;current_pointer++;size++;break;
		}
		if(!Character.isLetterOrDigit(c))
		{
		String s = new String(buffer,0,current_pointer);
		token = "TK_ID";
		System.out.println("Token name is Token_ID and lexeme is"+" "+" "+ s);
		current_pointer =0;
		return;		
		}
		
	case 26:
	    if(c == 'i'){state = 27; buffer[current_pointer] = c; current_pointer++; size = size+1; 			break;}
		if(Character.isLetterOrDigit(c))
		{
		state = 7; buffer[current_pointer]= c;current_pointer++;size++;break;
		}
		if(!Character.isLetterOrDigit(c))
		{
		String s = new String(buffer,0,current_pointer);
		token = "TK_ID";
		System.out.println("Token name is Token_ID and lexeme is"+" "+ s);
		current_pointer =0;
		return;		
		}
			
		case 27:
	    if(c == 'l'){state = 28; buffer[current_pointer] = c; current_pointer++; size = size+1; 			break;}
		if(Character.isLetterOrDigit(c))
		{
		state = 7; buffer[current_pointer]= c;current_pointer++;size++;break;
		}
		if(!Character.isLetterOrDigit(c))
		{
		String s = new String(buffer,0,current_pointer);
		token = "TK_ID";
		System.out.println("Token name is Token_ID and lexeme is"+" " +s);
		current_pointer =0;
		return;		
		}	
			
		case 28:
	    if(c == 'e'){state = 29; buffer[current_pointer] = c; current_pointer++; size = size+1; 			break;}
		if(Character.isLetterOrDigit(c))
		{
		state = 7; buffer[current_pointer]= c;current_pointer++;size++;break;
		}
		if(!Character.isLetterOrDigit(c))
		{
		String s = new String(buffer,0,current_pointer);
		token = s;
		System.out.println("Token name is Token_ID and lexeme is"+" " +s);
		token = "TK_ID";
		current_pointer =0;
		return;		
		}	

		
		case 29:
		
		if( c== ' ')
		{ 
		String s = new String(buffer,0,current_pointer);
		size = size+1;
		token = "TK_WHILE";
		System.out.println("TOKEN IS ::TK_WHILE and lexeme is "+" "+ s);
		byte arr[] = s.getBytes();
		out.write(arr);
		current_pointer = 0;
		flag = 1;
		return;
		}
		if(Character.isLetterOrDigit(c))
		{
		state = 7; buffer[current_pointer]= c;current_pointer++;size++;break;
		}
		if(!Character.isLetterOrDigit(c))
		{
		String s = new String(buffer,0,current_pointer);
		token = "TK_ID";
		System.out.println("KEYWORD ERROR Token name is TK_WHILE and lexeme is"+" "+ s);
		current_pointer =0;
		return;		
		}
		//END of WHILE
		
		//START of DIGIT
		case 55:
		if(Character.isDigit(c))
		{ 
		state = 55;
		buffer[current_pointer]= c;current_pointer++;size++;break;
		}
		if(c == '.'){state = 56; buffer[current_pointer]= c;current_pointer++;size++;break;}
		if((!Character.isDigit(c)) && (c!='.')) 
		{
		if(buffer[0]=='+' || buffer[0]=='-')
		{
		token = "TK_LIT_INT";
		String s = new String(buffer,0,current_pointer);
		System.out.println("TOKEN NAME is TK_LIT_INT and lexeme is"+" "+s);
		current_pointer = 0;
		a.lexeme = s;
		return;
		}
		else
		{
		String s = new String(buffer,0,current_pointer);
		token = "TK_LIT_INT";
		System.out.println("TOKEN NAME is TK_LIT_INT and lexeme is"+" "+s);
		current_pointer = 0;
		a.lexeme = s;
		return;
		}
		}
		
		case 56:
		if(Character.isDigit(c))
		{ 
		state = 56;
		buffer[current_pointer]= c;current_pointer++;size++;break;
		}
		
		if((!Character.isDigit(c)) && (c!='.')) 
		{
		if(buffer[0]=='+' || buffer[0]=='-')
		{
		String s = new String(buffer,0,current_pointer);
		a.lexeme = s;
		token = "TK_LIT_REAL";
		System.out.println("TOKEN NAME is TK_LIT_REAL and lexeme is"+" "+s);
		current_pointer = 0;
		return;
		}
		else
		{
		String s = new String(buffer,0,current_pointer);
		a.lexeme = s;
		token = "TK_LIT_REAL";
		System.out.println("TOKEN NAME is TK_LIT_REAL and lexeme is"+" "+s);
		current_pointer = 0;
		return;
		}
		}
		
	
		//END of INTEGERS and REAL
		
		//START of <, <> ,<=
		case 43:
		if(c == '>')
		{
		buffer[current_pointer]= c;current_pointer++;size++;
		token = "TK_LEQ";
		System.out.println("TOKEN NAME is TK_NEQ and lexeme is"+"<>");
		current_pointer = 0;
		return;		
		}
		
		if(c == '=')
		{
		buffer[current_pointer]= c;current_pointer++;size++;
		token ="TK_LE";
		System.out.println("TOKEN NAME is TK_LE and lexeme is"+"<=");
		current_pointer = 0;
		return;		
		}
		
		if((c!='>')&&(c!='='))
		{
		System.out.println("TOKEN NAME is TK_LT and lexeme is"+"<");
		token = "TK_LT";
		current_pointer = 0;
		return;		
		}
		
		//END of < , <> ,<= 
		//START of >, >=
		case 46:
		if(c == '=')
		{
		buffer[current_pointer]= c;current_pointer++;size++;
		System.out.println("TOKEN NAME is TK_GE and lexeme is"+">=");
		token = "TK_GE";
		current_pointer = 0;
		return;		
		}
		
		if(c!='=')
		{
		System.out.println("TOKEN NAME is TK_GT and lexeme is"+">");
		current_pointer = 0;
		token = "TK_GT";
		return;		
		}
		
		//END of >,  >=
		//START of =, ==
		case 49:
		if(c == '=')
		{
		buffer[current_pointer]= c;current_pointer++;size++;
		System.out.println("TOKEN NAME is TK_EQ and lexeme is"+"==");
		token = "TK_EQ";
		current_pointer = 0;
		return;		
		}
		
		if(c!='=')
		{
		token = "TK_EQUAL";
		//System.out.println("TOKEN NAME is TK_EQU and lexeme is"+"=");
		current_pointer = 0;
		return;		
		}
		
		//END of  =, ==
		//START of CHARACTER LITERAL
		case 59:
		if(Character.isLetterOrDigit(c))
		{
		state = 60; buffer[current_pointer]= c;current_pointer++;size++;break;
		}
		
		if(c=='\\'){state = 61; buffer[current_pointer]= c;current_pointer++;size++;break;}
		
		if((!(Character.isLetterOrDigit(c))) && (c!='\\'))
		{
		current_pointer = 0;
		System.out.println("Error in entering character literal"+" "+"'"+c);
		return;
		}
		
		case 60:
		if(c == '\'')
		{
		buffer[current_pointer]= c;size++;
		String s = new String(buffer,1,current_pointer-1);
		System.out.println("TOKEN NAME is TK_LIT_CHAR and lexeme is"+" "+s);
		token = "TK_LIT_CHAR";
		current_pointer = 0;
		return;
		}
		
		if(c != '\'')
		{
		String s = new String(buffer,1,current_pointer-1);
		System.out.println("Error in entering character literal"+" "+"'"+s);// to print 'a 
		current_pointer = 0;
		return;
		}
		
		
		case 61:
		if(c== 'n' || c== 't' || c=='\"')
		{
		state = 60; buffer[current_pointer]= c;current_pointer++;size++;break;
		}
		
		if(c!='n' && c!= 't' && c!='\"')
		{
		String s = new String(buffer,1,current_pointer-1);
		System.out.println("Error in entering character literal"+" "+"'"+s);// to print 'a 
		current_pointer = 0;
		return;
		
		}
		
		//END of CHARACTER LITERAL
		//START of STRING LITERAL
		case 65:
		if(Character.isLetterOrDigit(c)){state = 65; buffer[current_pointer]= c;current_pointer++;size++;		 break;}
		
		if(c == '\"')
		{
		buffer[current_pointer]= c;size++;
		String s = new String(buffer,1,current_pointer-1);
		System.out.println("TOKEN NAME is TK_LIT_STRING and lexeme is"+" "+s);
		token = "TK_LIT_STRING";
		current_pointer=0;
		return;
		}
		
		if((!Character.isLetterOrDigit(c))&&(c!='\"'))
		{
		buffer[current_pointer]= c;
		String s = new String(buffer,0,current_pointer);
		System.out.println("ERROR in entering STRING LITERAL"+" "+s);
		current_pointer=0;
		return;
		}
		//END of STRING LITERAL
		
		//START of MULTI LINE and SINGLE LINE comment
		case 68:
		if(c == '*')
		{
		state = 69;buffer[current_pointer]= c;current_pointer++;size++;break;
		}
		
		if(c=='/'){state = 71;buffer[current_pointer]= c;current_pointer++;size++;break;}
		else{ token = "TK_DIVIDE"; current_pointer =0; return;}
		
		case 69:
		if(c!='*'){state = 69;if(i1==13){linenumber++;}buffer[current_pointer]= c;current_pointer++;size++;break;}
		if(c=='*')
		{
			state = 70;buffer[current_pointer]= c;current_pointer++;size++;
			break;
		}
		
		case 70:
		if(c == '/')
		{
		//String s = new String(buffer,2,current_pointer-3);
		//System.out.println("MULTILINE COMMENT with string "+s);
		size++;
		current_pointer=0;
		return;		
		}
		if(c!='/')
		{state = 69;buffer[current_pointer]= c;current_pointer++;size++;break;}
		
		//end of multiline comment
		//start of single line comment
		
		case 71:
		if(i1!=13)//for carriage return
		{
		state = 71;buffer[current_pointer]= c;current_pointer++;size++;break;
		}
		if(i1==13)
		{
		linenumber++;
		if(current_pointer==2)
		{
		size = size+1;
		token = "TK_SINGLE_LINE_COMMENT";
		System.out.println("SINGLE LINE COMMENT ");
		current_pointer = 0;
		return;
		}
		if(current_pointer>2)
		{
		String s = new String(buffer,2,current_pointer-2);
		token = "TK_SINGLE_LINE_COMMENT";
		System.out.println("SINGLE LINE COMMENT and the commented string is "+s);
		size++;
		current_pointer = 0;
		return;
		}
		}
		
		
		//END of Multi line and sinfle line comments
		//start of void 
		case 80:
		
		if( c== 'o'){state = 81; buffer[current_pointer] = c; current_pointer++; size = size+1; 			break;}
		if(Character.isLetterOrDigit(c))
		{
		state = 7; buffer[current_pointer]= c;current_pointer++;size++;break;
		}
		if(!Character.isLetterOrDigit(c))
		{
		String s = new String(buffer,0,current_pointer);
		System.out.println("Token name is Token_ID and lexeme is"+" "+" "+ s);
		token = "TK_ID";
		current_pointer =0;
		return;		
		}
		
		
		case 81:
		
		if( c== 'i'){state = 82; buffer[current_pointer] = c; current_pointer++; size = size+1; 			break;}
		if(Character.isLetterOrDigit(c))
		{
		state = 7; buffer[current_pointer]= c;current_pointer++;size++;break;
		}
		if(!Character.isLetterOrDigit(c))
		{
		String s = new String(buffer,0,current_pointer);
		System.out.println("Token name is Token_ID and lexeme is"+" "+" "+ s);
		a.lexeme = s;
		token = "TK_ID";
		current_pointer =0;
		return;		
		}
		
		case 82:
		
		if( c== 'd'){state = 83; buffer[current_pointer] = c; current_pointer++; size = size+1; 			break;}
		if(Character.isLetterOrDigit(c))
		{
		state = 7; buffer[current_pointer]= c;current_pointer++;size++;break;
		}
		if(!Character.isLetterOrDigit(c))
		{
		String s = new String(buffer,0,current_pointer);
		System.out.println("Token name is Token_ID and lexeme is"+" "+" "+ s);
		token = "TK_ID";
		current_pointer =0;
		return;		
		}
		
		
		case 83:
		
		if( c == ' ')
		{ 
		String s = new String(buffer,0,current_pointer);
		size = size+1;
		System.out.println("TOKEN IS ::TK_VOID and lexeme is "+" "+ s);
		token = "TK_VOID";
		byte arr[] = s.getBytes();
		out.write(arr);
		current_pointer = 0;
		flag = 1;
		return;
		}
		if(Character.isLetterOrDigit(c))
		{
		state = 7; buffer[current_pointer]= c;current_pointer++;size++;break;
		}
		if(!Character.isLetterOrDigit(c))
		{
		String s = new String(buffer,0,current_pointer);
		System.out.println("KEYWORD ERROR Token name is TK_ID and lexeme is"+" "+ s);
		token = "TK_ID";
		current_pointer =0;
		return;		
		}
		
		// end of void
		//start of array
		case 92:
		
		if( c== 'r'){state = 93; buffer[current_pointer] = c; current_pointer++; size = size+1; 			break;}
		if(Character.isLetterOrDigit(c))
		{
		state = 7; buffer[current_pointer]= c;current_pointer++;size++;break;
		}
		if(!Character.isLetterOrDigit(c))
		{
		String s = new String(buffer,0,current_pointer);
		System.out.println("Token name is Token_ID and lexeme is"+" "+" "+ s);
		token = "TK_ID";
		current_pointer =0;
		return;		
		}
		
		
		case 93:
		
		if( c== 'r'){state = 94; buffer[current_pointer] = c; current_pointer++; size = size+1; 			break;}
		if(Character.isLetterOrDigit(c))
		{
		state = 7; buffer[current_pointer]= c;current_pointer++;size++;break;
		}
		if(!Character.isLetterOrDigit(c))
		{
		String s = new String(buffer,0,current_pointer);
		System.out.println("Token name is Token_ID and lexeme is"+" "+" "+ s);
		token = "TK_ID";
		current_pointer =0;
		return;		
		}
		
		case 94:
		
		if( c== 'a'){state = 95; buffer[current_pointer] = c; current_pointer++; size = size+1; 			break;}
		if(Character.isLetterOrDigit(c))
		{
		state = 7; buffer[current_pointer]= c;current_pointer++;size++;break;
		}
		if(!Character.isLetterOrDigit(c))
		{
		String s = new String(buffer,0,current_pointer);
		System.out.println("Token name is Token_ID and lexeme is"+" "+" "+ s);
		token = "TK_ID";
		current_pointer =0;
		return;		
		}
		
		case 95:
		
		if( c== 'y'){state = 96; buffer[current_pointer] = c; current_pointer++; size = size+1; 			break;}
		if(Character.isLetterOrDigit(c))
		{
		state = 7; buffer[current_pointer]= c;current_pointer++;size++;break;
		}
		if(!Character.isLetterOrDigit(c))
		{
		String s = new String(buffer,0,current_pointer);
		System.out.println("Token name is Token_ID and lexeme is"+" "+" "+ s);
		token = "TK_ID";
		current_pointer =0;
		return;		
		}
		
		
		case 96:
		
		if( c == ' ')
		{ 
		String s = new String(buffer,0,current_pointer);
		size = size+1;
		System.out.println("TOKEN IS ::TK_ARRAY and lexeme is "+" "+ s);
		token = "TK_ARRAY";
		byte arr[] = s.getBytes();
		out.write(arr);
		current_pointer = 0;
		flag = 1;
		return;
		}
		if(Character.isLetterOrDigit(c))
		{
		state = 7; buffer[current_pointer]= c;current_pointer++;size++;break;
		}
		if(!Character.isLetterOrDigit(c))
		{
		String s = new String(buffer,0,current_pointer);
		System.out.println("KEYWORD ERROR Token name is TK_ID and lexeme is"+" "+ s);
		token = "TK_ID";
		current_pointer =0;
		return;		
		}
		//end of array
		//start of function
	
		case 98:
		
		if( c== 'u'){state = 99; buffer[current_pointer] = c; current_pointer++; size = size+1; 			break;}
		if(Character.isLetterOrDigit(c))
		{
		state = 7; buffer[current_pointer]= c;current_pointer++;size++;break;
		}
		if(!Character.isLetterOrDigit(c))
		{
		String s = new String(buffer,0,current_pointer);
		System.out.println("Token name is Token_ID and lexeme is"+" "+" "+ s);
		token = "TK_ID";
		current_pointer =0;
		return;		
		}
		
		
		case 99:
		
		if( c== 'n'){state = 100; buffer[current_pointer] = c; current_pointer++; size = size+1; 			break;}
		if(Character.isLetterOrDigit(c))
		{
		state = 7; buffer[current_pointer]= c;current_pointer++;size++;break;
		}
		if(!Character.isLetterOrDigit(c))
		{
		String s = new String(buffer,0,current_pointer);
		System.out.println("Token name is Token_ID and lexeme is"+" "+" "+ s);
		token = "TK_ID";
		current_pointer =0;
		return;		
		}
		
		case 100:
		
		if( c== 'c'){state = 101; buffer[current_pointer] = c; current_pointer++; size = size+1; 			break;}
		if(Character.isLetterOrDigit(c))
		{
		state = 7; buffer[current_pointer]= c;current_pointer++;size++;break;
		}
		if(!Character.isLetterOrDigit(c))
		{
		String s = new String(buffer,0,current_pointer);
		System.out.println("Token name is Token_ID and lexeme is"+" "+" "+ s);
		token = "TK_ID";
		current_pointer =0;
		return;		
		}
		
		case 101:
		
		if( c== 't'){state = 102; buffer[current_pointer] = c; current_pointer++; size = size+1; 			break;}
		if(Character.isLetterOrDigit(c))
		{
		state = 7; buffer[current_pointer]= c;current_pointer++;size++;break;
		}
		if(!Character.isLetterOrDigit(c))
		{
		String s = new String(buffer,0,current_pointer);
		System.out.println("Token name is Token_ID and lexeme is"+" "+" "+ s);
		token = "TK_ID";
		current_pointer =0;
		return;		
		}
		
		case 102:
		
		if( c== 'i'){state = 103; buffer[current_pointer] = c; current_pointer++; size = size+1; 			break;}
		if(Character.isLetterOrDigit(c))
		{
		state = 7; buffer[current_pointer]= c;current_pointer++;size++;break;
		}
		if(!Character.isLetterOrDigit(c))
		{
		String s = new String(buffer,0,current_pointer);
		System.out.println("Token name is Token_ID and lexeme is"+" "+" "+ s);
		token = "TK_ID";
		current_pointer =0;
		return;		
		}
		
		case 103:
		
		if( c== 'o'){state = 104; buffer[current_pointer] = c; current_pointer++; size = size+1; 			break;}
		if(Character.isLetterOrDigit(c))
		{
		state = 7; buffer[current_pointer]= c;current_pointer++;size++;break;
		}
		if(!Character.isLetterOrDigit(c))
		{
		String s = new String(buffer,0,current_pointer);
		System.out.println("Token name is Token_ID and lexeme is"+" "+" "+ s);
		token = "TK_ID";
		current_pointer =0;
		return;		
		}
		
		case 104:
		
		if( c== 'n'){state = 105; buffer[current_pointer] = c; current_pointer++; size = size+1; 			break;}
		if(Character.isLetterOrDigit(c))
		{
		state = 7; buffer[current_pointer]= c;current_pointer++;size++;break;
		}
		if(!Character.isLetterOrDigit(c))
		{
		String s = new String(buffer,0,current_pointer);
		System.out.println("Token name is Token_ID and lexeme is"+" "+" "+ s);
		token = "TK_ID";
		current_pointer =0;
		return;		
		}
		
		case 105:
		
		if( c == ' ')
		{ 
		String s = new String(buffer,0,current_pointer);
		size = size+1;
		System.out.println("TOKEN IS ::TK_FUNCTION and lexeme is "+" "+ s);
		scope = scope+1;//incermenting the scope;
		byte arr[] = s.getBytes();
		token = "TK_FUNCTION";
		out.write(arr);
		current_pointer = 0;
		flag = 1;
		return;
		}
		if(Character.isLetterOrDigit(c))
		{
		state = 7; buffer[current_pointer]= c;current_pointer++;size++;break;
		}
		if(!Character.isLetterOrDigit(c))
		{
		String s = new String(buffer,0,current_pointer);
		System.out.println("KEYWORD ERROR Token name is TK_ID and lexeme is"+" "+ s);
		token = "TK_ID";
		current_pointer =0;
		return;		
		}
		//end of function
		
		//start of return
		case 107:
		
		if( c== 'u'){state = 108; buffer[current_pointer] = c; current_pointer++; size = size+1; 			break;}
		if(Character.isLetterOrDigit(c))
		{
		state = 7; buffer[current_pointer]= c;current_pointer++;size++;break;
		}
		if(!Character.isLetterOrDigit(c))
		{
		String s = new String(buffer,0,current_pointer);
		System.out.println("Token name is Token_ID and lexeme is"+" "+" "+ s);
		token = "TK_ID";
		current_pointer =0;
		return;		
		}
		
		case 108:
		
		if( c== 'r'){state = 109; buffer[current_pointer] = c; current_pointer++; size = size+1; 			break;}
		if(Character.isLetterOrDigit(c))
		{
		state = 7; buffer[current_pointer]= c;current_pointer++;size++;break;
		}
		if(!Character.isLetterOrDigit(c))
		{
		String s = new String(buffer,0,current_pointer);
		System.out.println("Token name is Token_ID and lexeme is"+" "+" "+ s);
		token = "TK_ID";
		current_pointer =0;
		return;		
		}
		
		case 109:
		
		if( c== 'n'){state = 110; buffer[current_pointer] = c; current_pointer++; size = size+1; 			break;}
		if(Character.isLetterOrDigit(c))
		{
		state = 7; buffer[current_pointer]= c;current_pointer++;size++;break;
		}
		if(!Character.isLetterOrDigit(c))
		{
		String s = new String(buffer,0,current_pointer);
		System.out.println("Token name is Token_ID and lexeme is"+" "+" "+ s);
		token = "TK_ID";
		current_pointer =0;
		return;		
		}
		
		case 110:
		
		if( c == ' ')
		{ 
		String s = new String(buffer,0,current_pointer);
		size = size+1;
		System.out.println("TOKEN IS ::TK_RETURN and lexeme is "+" "+ s);
		byte arr[] = s.getBytes();
		out.write(arr);
		token = "TK_RETURN";
		current_pointer = 0;
		flag = 1;
		return;
		}
		if(Character.isLetterOrDigit(c))
		{
		state = 7; buffer[current_pointer]= c;current_pointer++;size++;break;
		}
		if(!Character.isLetterOrDigit(c))
		{
		String s = new String(buffer,0,current_pointer);
		System.out.println("KEYWORD ERROR Token name is TK_ID and lexeme is"+" "+ s);
		token = "TK_ID";
		current_pointer =0;
		return;		
		}
		//end of return
		//start of main
		case 112:
		
		if( c== 'a'){state = 113; buffer[current_pointer] = c; current_pointer++; size = size+1; 			break;}
		if(Character.isLetterOrDigit(c))
		{
		state = 7; buffer[current_pointer]= c;current_pointer++;size++;break;
		}
		if(!Character.isLetterOrDigit(c))
		{
		String s = new String(buffer,0,current_pointer);
		System.out.println("Token name is Token_ID and lexeme is"+" "+" "+ s);
		token = "TK_ID";
		current_pointer =0;
		return;		
		}
		
		case 113:
		
		if( c== 'i'){state = 114; buffer[current_pointer] = c; current_pointer++; size = size+1; 			break;}
		if(Character.isLetterOrDigit(c))
		{
		state = 7; buffer[current_pointer]= c;current_pointer++;size++;break;
		}
		if(!Character.isLetterOrDigit(c))
		{
		String s = new String(buffer,0,current_pointer);
		System.out.println("Token name is Token_ID and lexeme is"+" "+" "+ s);
		token = "TK_ID";
		current_pointer =0;
		return;		
		}
		
		case 114:
		
		if( c== 'n'){state = 115; buffer[current_pointer] = c; current_pointer++; size = size+1; 			break;}
		if(Character.isLetterOrDigit(c))
		{
		state = 7; buffer[current_pointer]= c;current_pointer++;size++;break;
		}
		if(!Character.isLetterOrDigit(c))
		{
		String s = new String(buffer,0,current_pointer);
		System.out.println("Token name is Token_ID and lexeme is"+" "+" "+ s);
		token = "TK_ID";
		current_pointer =0;
		return;		
		}
		
				
		case 115:
		
		if( c == ' ')
		{ 
		String s = new String(buffer,0,current_pointer);
		size = size+1;
		System.out.println("TOKEN IS ::TK_MAIN and lexeme is "+" "+ s+" linenumber is "+linenumber);
		byte arr[] = s.getBytes();
		out.write(arr);
		token = "TK_MAIN";
		current_pointer = 0;
		flag = 1;
		return;
		}
		if(Character.isLetterOrDigit(c))
		{
		state = 7; buffer[current_pointer]= c;current_pointer++;size++;break;
		}
		if(!Character.isLetterOrDigit(c))
		{
		String s = new String(buffer,0,current_pointer);
		System.out.println("KEYWORD ERROR Token name is TK_ID and lexeme is"+" "+ s+linenumber);
		token = "TK_ID";
		current_pointer =0;
		return;		
		}
		
		//end  of main
		
		
		
		
	
	}
	
		
}


}
static Identifier searchID(String lexeme,double scope)
{
char a1[];
String s=Double.toString(scope);
a1 = s.toCharArray();
String key = lexeme +Double.toString(scope);
int i = a1.length;
String s2;
	while(i>2)
	{
	s2 = new String(a1,0,i);
	key = lexeme +s2;
	if(a.symboltable.containsKey(key)==true)
	{
		
		return (Identifier)a.symboltable.get(key);
	}
	else
	{
	i--;
	}
	}
	//checking in function's declarations//scope
	s2 = new String(a1,0,2);
	s2 = s2+"0";
	key = lexeme + s2;
	if(a.symboltable.containsKey(key)==true)
	{
		
	return (Identifier)a.symboltable.get(key);
		
		
	}
	//checking for gloabl declarations
	
	else 
	{
	key = lexeme +"1.0";	
	if(a.symboltable.containsKey(key)==true)
	{
				
	 	return (Identifier)a.symboltable.get(key);
	
	}
	
	else
	{
		return null;
	}
	}



}//searchID
}//end of class a


class root_node
{
Declarations d;
ArrayDeclarations a;
ArrayList<function> method_list  = new ArrayList<function>();
//mainmethod m ;
Object addchild(root_node r,String s)
{
//switch statement we cann't use as we cann't compare to strings change to else if
Object ob = null;
if(s.equals("Declarations"))
{
r.d = new Declarations();
return r.d;
}
else if(s.equals("ArrayDeclarations"))
{
r.a = new ArrayDeclarations();
return r.a;
}

else if (s.equals("function"))
{
function m1 = new function();
r.method_list.add(m1);

return m1;
}

else
{
return ob;
}
//else if(s.equals("main"))
//{
//return;
//r.m = new mainmethod();
//return r.m;
//}

}//addchild method 

}//end of class  root_node

class Declarations
{
double scope;
int totalsize;
int location;
ArrayList<Declaration> declarationlist = new ArrayList<Declaration>();

Declaration addchild()
{
Declaration d1 = new Declaration();
declarationlist.add(d1);
return d1;
}

void method_declarations() throws IOException
{
if(a.token.equals("TK_INT") || a.token.equals("TK_REAL") ||a.token.equals("TK_CHAR")|| a.token.equals("TK_STRING"))
{
Declaration d1 = this.addchild();
System.out.println("declaration child was added");
d1.scope = this.scope;
d1.method_declaration();
this.totalsize = this.totalsize+ d1.totalsize;
method_declarations();
return;
}
else if(a.token.equals("TK_ARRAY")|| a.token.equals("TK_FUNCTION")|| a.token.equals("TK_MAIN")||a.token.equals("TK_LBRACE"))
{
return;
}

}//end of void method

}//end of class Declarations 

class Declaration
{
double scope;
String type;
int totalsize;
Idlist il;

Idlist addidlist()
{
il = new Idlist();
return il;
}

void method_declaration() throws IOException
{
if(a.token.equals("TK_INT"))
{ this.type = "TK_INT";}

else if(a.token.equals("TK_CHAR"))
{ this.type = "TK_CHAR";}

else if(a.token.equals("TK_REAL"))
{ this.type = "TK_REAL";}

else if(a.token.equals("TK_STRING"))
{ this.type = "TK_STRING";}

a.nexttoken();
if(a.token.equals("TK_ID")) //else write error code here
{
Idlist i1 = this.addidlist();
i1.type = this.type;
i1.scope = this.scope;
i1.method_idlist();
this.totalsize = i1.size;
return;
}

else
{
System.out.println("variable name missing"+a.linenumber);
while(!(a.token.equals("TK_SEMICOLON ")))
{
a.nexttoken();
return;
}
}

}

}
class Idlist
{
double scope;
String type;
int size;
ArrayList<Identifier> id = new ArrayList<Identifier>();

Identifier addchild()
{
Identifier f1= new Identifier();
id.add(f1);
return f1;
}

void method_idlist() throws IOException
{
if(a.token.equals("TK_ID"))
{
Identifier id1 = addchild();
id1.type = this.type;
id1.loc= a.linenumber;
id1.lexeme = a.lexeme;
id1.scope= this.scope;
id1.index = id1.lexeme+Double.toString(id1.scope);
id1.isIdentifier = true;
if(id1.type.equals("TK_INT"))
{ id1.size =2; }

else if(id1.type.equals("TK_CHAR"))
{ id1.size = 1;}

else if(id1.type.equals("TK_REAL"))
{ id1.size = 4;}

else if(id1.type.equals("TK_STRING"))
{ id1.size = 8;}

if(a.symboltable.containsKey(id1.index)==true)//error handling for symbol table containing the same entry
{
System.out.println("Identifier already exists and its lexeme is"+id1.lexeme +"a.lexeme is"+a.lexeme);
return;
}
else
{
a.symboltable.put(id1.index,id1);
}
this.size = this.size + id1.size;
}
a.nexttoken();
if(a.token.equals("TK_COMMA")) //eat comma and get nexttoken
{
a.nexttoken();
method_idlist();
return;
}
else if(a.token.equals("TK_SEMICOLON"))
{
a.nexttoken();
return;
}

}//end of void method

}//end of class Idlist

class Identifier extends root
{
int location;
String  type;
String lexeme;
int function_index;
int  size;
double scope;
int loc;
String index;
boolean isArray= false;
boolean isParam = false;
boolean isIdentifier = false;
boolean isFunction = false;
}


class ArrayDeclarations
{
double scope;
int totalsize;
int location;
ArrayList<ArrayDeclaration> arraydeclarationlist = new ArrayList<ArrayDeclaration>();

ArrayDeclaration addchild()
{
ArrayDeclaration ad1 = new ArrayDeclaration();
arraydeclarationlist.add(ad1);
return ad1;
}

void arraymethod_declarations() throws IOException
{
if(a.token.equals("TK_ARRAY"))
{
a.nexttoken();
if(a.token.equals("TK_INT") || a.token.equals("TK_REAL") ||a.token.equals("TK_CHAR")||a.token.equals("TK_STRING"))
{
ArrayDeclaration ad1 = this.addchild();
ad1.scope = this.scope;
ad1.arraymethod_declaration();
this.totalsize = this.totalsize+ ad1.totalsize;
arraymethod_declarations();
return;
}//inner if
}//outer if

else if(a.token.equals("TK_FUNCTION")|| a.token.equals("TK_MAIN"))
{
return;
}


}//end of void method
}

class ArrayDeclaration
{
double scope;
String type;
Identifier i1;
int totalsize;
Identifier addchild()
{
i1= new Identifier();
return i1;
}

void arraymethod_declaration() throws IOException
{
if(a.token.equals("TK_INT"))
{ this.type = "TK_INT";}

else if(a.token.equals("TK_CHAR"))
{ this.type = "TK_CHAR";}

else if(a.token.equals("TK_REAL"))
{ this.type = "TK_REAL";}


else if(a.token.equals("TK_STRING"))
{ this.type = "TK_STRING";}

a.nexttoken();
if(a.token.equals("TK_ID"))
{
Identifier id1 = this.addchild();
id1.type = this.type;
id1.loc= a.linenumber;
id1.lexeme = a.lexeme;
id1.scope= this.scope;
id1.index = id1.lexeme+Double.toString(id1.scope);
id1.isArray = true;

a.nexttoken();
if(a.token.equals("TK_LSQB"))
{
a.nexttoken();
  if(a.token.equals("TK_LIT_INT"))
  {
     id1.size =Integer.parseInt(a.lexeme);
     this.totalsize =Integer.parseInt(a.lexeme);
      if(a.symboltable.containsKey(id1.index)==true)//error handling for symbol table containing the same entry
       {
        System.out.println("Identifier already exists and its lexeme is"+id1.lexeme);
       }
      else
       {
        a.symboltable.put(id1.index,id1);
       }
     a.nexttoken();
      if(a.token.equals("TK_RSQB"))
      {
       a.nexttoken();
        if(a.token.equals("TK_SEMICOLON"))
        {
         a.nexttoken();
         return;
        }
        else 
        {
         System.out.println("semi colon missing" +a.linenumber);
         while(!(a.token.equals("TK_SEMICOLON ")))
         {
          a.nexttoken();
         }

        }
      }
      else 
      {
       System.out.println("] missing" +a.linenumber);
         while(!(a.token.equals("TK_SEMICOLON ")))
         {
          a.nexttoken();
         }

        }
      }
  }
  else 
  {
   System.out.println(" index of array missing" +a.linenumber);
         while(!(a.token.equals("TK_SEMICOLON ")))
         {
          a.nexttoken();
         }

        }
  }


}//end of void method
}//end of arramethod_declaration

class function
{
double scope;
String return_type;
int no_of_params, totalsize_of_params;
String  name;
//, entry,

paramlist p1;
Declarations d;
StackArrayDeclarations s;
statements s1;

void m_function() throws IOException
{
if(a.token.equals("TK_FUNCTION"))
{
System.out.println("function scope is "+scope);
a.nexttoken();//eat TK_FUNCTION
	if(a.token.equals("TK_INT") ||a.token.equals("TK_REAL") ||a.token.equals("TK_CHAR") || a.token.equals("TK_STRING") || a.token.equals("TK_VOID"))
	{
		if(a.token.equals("TK_INT"))
		{
		this.return_type = "INT";
		a.nexttoken();
		}
		else if(a.token.equals("TK_REAL") )
		{
		this.return_type = "REAL";
		a.nexttoken();
		}
		else if(a.token.equals("TK_CHAR") )
		{
		this.return_type = "CHAR";
		a.nexttoken();
		}
		else if(a.token.equals("TK_REAL") )
		{
		this.return_type = "STRING";
		a.nexttoken();
		}
		else if(a.token.equals("TK_VOID") )
		{
		this.return_type = "VOID";
		a.nexttoken();
		}
		}
		else//if no proper return type was found
		{
		System.out.println("error at lne number " +a.linenumber);
                 while(!(a.token.equals("TK_SEMICOLON ")))
                {
                 a.nexttoken();
				 return;
                }
		}
				
			
		if(a.token.equals("TK_ID"))
		{
		this.name = a.lexeme;
		Identifier i1 = new Identifier();
		i1.isFunction = true;
		i1.location = a.linenumber;
		i1.scope= this.scope;
		i1.lexeme = a.lexeme;
		i1.type = "FUNCTION";
		i1.index = i1.lexeme+Double.toString(i1.scope);
		a.nexttoken();//eat ID
		a.nexttoken();//eat LPAREN
		if(a.symboltable.containsKey(i1.index)==true)//error handling for symbol table containing the same entry
		{
		//System.out.println("function with the following name already exists and its lexeme is"+id1.lexeme);
		return;//set error in ast function as 1;
		}
		else
		{
		a.symboltable.put(i1.index,i1);
		}
		}
			
		
		if(a.token.equals("TK_INT") ||a.token.equals("TK_REAL") ||a.token.equals("TK_CHAR") || a.token.equals("TK_STRING"))
		{
		p1 = new paramlist();
		p1.m_paramlist();
		this.no_of_params = p1.no_of_params;
		this.totalsize_of_params = p1.totalsize;
		}
		//if no parameters are there
		else if(a.token.equals("TK_RPAREN"))
		{
		a.nexttoken();
		}
		else
		{
		System.out.println("Error from function");
		}
		              
		if(a.token.equals("TK_LBRACE"))
		{
		a.nexttoken();//EAT LBRACE
		}
				
		if(a.token.equals("TK_INT") || a.token.equals("TK_REAL") ||a.token.equals("TK_CHAR")|| a.token.equals("TK_STRING"))
		{
			 d = new Declarations();
			 d.scope = this.scope;
			d.method_declarations();
		}
                       
             if(a.token.equals("TK_ARRAY"))
                {
                s = new StackArrayDeclarations();
				s.scope = this.scope;
		s.stackarraymethod_declarations();
                }
		
		if(a.token.equals("TK_LBRACE")||a.token.equals("TK_IF")||a.token.equals("TK_WHILE")||a.token.equals("TK_ID"))//FIRST OF STATEMENTS
		{
		s1 = new statements();
		s1.scope = this.scope;
		s1.method_statements();
		}
		if(a.token.equals("TK_RETURN"))
		{
		    a.nexttoken();//EAT tk_return;
			if(a.token.equals("TK_SEMICOLON"))
			{
				a.nexttoken();
			}
		}
		if(a.token.equals("TK_RBRACE"))
				{
				System.out.println("From function");
				a.nexttoken();//eat LBRACE
				return;
				}
          
         	
		
		
		
}

}//end of method_function
}//end of class function

class paramlist
{
int no_of_params=0;
int totalsize;
ArrayList<param> parameterlist = new ArrayList<param>();

param addchild()
{
param p1 = new param();
parameterlist.add(p1);
return p1;
}
void m_paramlist() throws IOException
{
		if(a.token.equals("TK_INT"))
		{
		param p2 = addchild();
		p2.type = "INT";
		p2.m_param();
		p2.size =2;
		no_of_params = no_of_params +1;
		totalsize =totalsize + p2.size;
		m_paramlist();
		}
		else if(a.token.equals("TK_REAL") )
		{
		param p2 = addchild();
		p2.type = "REAL";
		p2.m_param();
		p2.size =4;
		no_of_params = no_of_params +1;
		totalsize =totalsize + p2.size;
		m_paramlist();
		}
		else if(a.token.equals("TK_CHAR") )
		{
		param p2 = addchild();
		p2.type = "CHAR";
		p2.m_param();
		p2.size =1;
		no_of_params = no_of_params +1;
		totalsize =totalsize + p2.size;
		m_paramlist();
		}
		else if(a.token.equals("TK_STRING") )
		{
		param p2 = addchild();
		p2.type = "REAL";
		p2.m_param();
		p2.size =8;
		no_of_params = no_of_params +1;
		totalsize =totalsize + p2.size;
		m_paramlist();
		}
				
		else if(a.token.equals("TK_RPAREN"))
		{
		a.nexttoken();
		return;
		}
		else
		{
		                
                 System.out.println("incorrect parameters"+a.linenumber);
                  while(!(a.token.equals("TK_SEMICOLON ")))
                    {
                    a.nexttoken();
					return;
                     }
                
		}
		
}


}//end of paramlist class

class param
{
String type;
int size;
Identifier id1;

void m_param() throws IOException
{
a.nexttoken();//get identifier after eating type
if(a.token.equals("TK_ID"))
{
id1 = new Identifier();
id1.type = this.type;
id1.loc= a.linenumber;
id1.lexeme = a.lexeme;
id1.scope= a.scope;
id1.index = id1.lexeme+Double.toString(id1.scope);
id1.isParam = true;
if(a.symboltable.containsKey(id1.index)==true)//error handling for symbol table containing the same entry
{
System.out.println("Identifier already exists and its lexeme is"+id1.lexeme);
}
else
{
a.symboltable.put(id1.index,id1);
}
a.nexttoken();
if(a.token.equals("TK_COMMA"))
{
a.nexttoken();
return;
}
else if(a.token.equals("TK_RPAREN"));
{
return;
}
}
else
{
System.out.println("incorrect parameters"+a.linenumber);
                  while(!(a.token.equals("TK_SEMICOLON ")))
                    {
                    a.nexttoken();
                     }
}
}//end of m_ param
}//end of class param

class expressions
{

static void e(root r) throws IOException
{
t(r);
e_p(r);
return;
}

static void e_p(root r) throws IOException
{
if(a.token.equals("TK_PLUS"))
{
Operator op = new Operator();
op.type = "+";
root r1 = r.child;
r.child = op;
op.leftchild = r1;
a.nexttoken();//EAT PLUS
t(r);
e_p(r);
return;
}

else if(a.token.equals("TK_MINUS"))
{
Operator op = new Operator();
op.type = "-";
root r1 = r.child;
r.child = op;
op.leftchild = r1;
a.nexttoken();

a.nexttoken();//EAT MINUS
t(r);
e_p(r);
return;
}

else if(a.token.equals("TK_SEMICOLON"))
{
a.nexttoken();
return;
}

else if(a.token.equals("TK_LPAREN"))
{
a.nexttoken();
return;
}


else if(a.token.equals("TK_RSQB"))
{
a.nexttoken();
return;
}

else if(a.token.equals("TK_NEQ")||a.token.equals("TK_EQ")||a.token.equals("TK_LT")||a.token.equals("TK_GT")||a.token.equals("TK_LE")||a.token.equals("TK_GE"))
{
//RELATIONAL OPERTAORS
return;
}

else
{
return;
}

}//end of void e_p

static void t(root r) throws IOException
{
f(r);
t_p(r);
return;
}

static void t_p(root r) throws IOException
{
if(a.token.equals("TK_ASTERISK"))
{

Operator op = new Operator();
op.type = "*";
root r1 = r.child;
r.child = op;
op.leftchild = r1;
a.nexttoken();//EAT asterik
t(r);
e_p(r);
return;
}

else if(a.token.equals("TK_DIVIDE"))
{
Operator op = new Operator();
op.type = "/";
root r1 = r.child;
r.child = op;
op.leftchild = r1;
a.nexttoken();//EAT divide
t(r);
e_p(r);
return;
}

else {return;}


}

static void f(root r) throws IOException
{
if(a.token.equals("TK_ID"))
{
String s = a.lexeme;
a.nexttoken();
if(a.token.equals("TK_LSQB"))
{
a.nexttoken();
e(r);
return;

}

Identifier i =a.searchID(s,a.scope);;//get from symbol table the corresponding Identifier object;
if(r.child == null)
{
r.child =i;
return; 
}

else
{
r.child.rightchild = i;
return;
}

}

else if(a.token.equals("TK_LIT_INT"))
{
if(r.child == null)
{
r.addchild("TK_LIT_INT",a.lexeme);
a.nexttoken();
return; 
}

else
{
r.child.addrightchild("TK_LIT_INT",a.lexeme);
a.nexttoken();
return;
}

}
else if(a.token.equals("TK_LIT_REAL"))
{
if(r.child == null)
{
r.addchild("TK_LIT_REAL",a.lexeme);
a.nexttoken();
return; 
}

else
{
r.child.addrightchild("TK_LIT_REAL",a.lexeme);
a.nexttoken();
return;
}

}





}//end of f

}//end of class expressions

class root
{
String value;
root child;
root rightchild;
root leftchild;
void addchild(String token,String value)
{
if(token.equals("TK_LIT_INT")==true)
{

this.child = new integer_literal(value);

}
else if(token.equals("TK_LIT_REAL")==true)

{
this.child = new integer_literal(value);
}
}//addchild

void addrightchild(String token,String value)
{
if(token.equals("TK_LIT_INT")==true)
{

this.rightchild = new integer_literal(value);

}
else if(token.equals("TK_LIT_REAL")==true)

{
this.rightchild = new integer_literal(value);
}
}//addrightchild


}

class integer_literal extends root
{
//String value;
integer_literal(String s)
{
this.value = s;
}
}

class real_literal extends root
{
//String value;
real_literal(String s)
{
this.value = s;
}
}


class Operator extends root
{
Operator()
{
}
Operator(String s)
{
this.type = s;
}
String type;
root leftchild;
root rightchild;

}

/*
class mainmethod
{
String  name="main";
double  scope=1;
Declarations d;

void m_function(root_node r1)
{

if(a.token.equals("TK_MAIN")) {a.nexttoken();} 

if(a.token.equals("TK_LPARAN")) {a.nexttoken();} 
else
{
System.out.println("incorrect main"+a.linenumber);
while(!(a.token.equals("TK_SEMICOLON ")))
{
  a.nexttoken();
}
}
if(a.token.equals("TK_RPARAN")) {a.nexttoken();} 
else
{
System.out.println("incorrect main"+a.linenumber);
while(!(a.token.equals("TK_SEMICOLON ")))
{
  a.nexttoken();
}
}
if(a.token.equals("TK_LBRACE")) {a.nexttoken();} 
else
{
System.out.println("incorrect main"+a.linenumber);
while(!(a.token.equals("TK_SEMICOLON ")))
{
  a.nexttoken();
}
}
if(a.token.equals("TK_INT") || a.token.equals("TK_REAL") ||a.token.equals("TK_CHAR")|| a.token.equals("TK_STRING"))
		{
			 d = new Declarations();
			d.method_declations();

		}
//STMTS

if(a.token.equals("TK_RBRACE")) {return;} 
else
{
System.out.println("incorrect main }"+a.linenumber);
while(!(a.token.equals("TK_SEMICOLON ")))
{
  a.nexttoken();
}
}

}// eo mfunc
}// eo class
*/
class StackArrayDeclarations
{
double scope;
int totalsize;
int location;
ArrayList<StackArrayDeclaration> stackarraydeclarationlist = new ArrayList<StackArrayDeclaration>();

StackArrayDeclaration addchild()
{
StackArrayDeclaration ad1 = new StackArrayDeclaration();
stackarraydeclarationlist.add(ad1);
return ad1;
}

void stackarraymethod_declarations() throws IOException
{
if(a.token.equals("TK_ARRAY"))
{
a.nexttoken();
if(a.token.equals("TK_INT") || a.token.equals("TK_REAL") ||a.token.equals("TK_CHAR")||a.token.equals("TK_STRING"))
{
StackArrayDeclaration ad1 = this.addchild();
ad1.scope = this.scope;
ad1.stackarraymethod_declaration();
this.totalsize = this.totalsize+ ad1.totalsize;
stackarraymethod_declarations();
return;
}//inner if
}//outer if
else if(a.token.equals("TK_LBRACE")||a.token.equals("TK_IF")||a.token.equals("TK_WHILE")||a.token.equals("TK_ID"))//FIRST OF STATEMENTS
{
return;
}
else
{
System.out.println("Error from stack array declarations");
return;
}

}//end of void method
}//class StackArrayDeclarations

class StackArrayDeclaration
{
String type;
int totalsize;
Identifier i1;
double scope;
Identifier addchild()
{
i1= new Identifier();
return i1;
}

void stackarraymethod_declaration() throws IOException
{
if(a.token.equals("TK_INT"))
{ this.type = "TK_INT";}

else if(a.token.equals("TK_CHAR"))
{ this.type = "TK_CHAR";}

else if(a.token.equals("TK_REAL"))
{ this.type = "TK_REAL";}


else if(a.token.equals("TK_STRING"))
{ this.type = "TK_STRING";}

a.nexttoken();
if(a.token.equals("TK_ID"))
{
Identifier id1 = this.addchild();
id1.type = this.type;
id1.loc= a.linenumber;
id1.lexeme = a.lexeme;
id1.scope= this.scope;
id1.index = id1.lexeme+Double.toString(id1.scope);
id1.isArray = true;
a.nexttoken();
if(a.token.equals("TK_LSQB"))
{
a.nexttoken();//EAT LSQB
  if(a.token.equals("TK_LIT_INT"))
  {
     id1.size =Integer.parseInt(a.lexeme);
	 this.totalsize = Integer.parseInt(a.lexeme);
     if(a.symboltable.containsKey(id1.index)==true)//error handling for symbol table containing the same entry
       {
        System.out.println("Identifier already exists and its lexeme is"+id1.lexeme);
		return;
       }
     else
       {
        a.symboltable.put(id1.index,id1);
       }
     a.nexttoken();
      if(a.token.equals("TK_RSQB"))
      {
       a.nexttoken();
        if(a.token.equals("TK_SEMICOLON"))
        {
         a.nexttoken();
         return;
        }
        else //SEMICOLON MISSING
        {
         System.out.println("semicolon missing"+a.linenumber);
                  while(!(a.token.equals("TK_SEMICOLON ")))
                    {
                    a.nexttoken();
                     }
        }
      }
	  
      else //MISSING RSQB]
      {
       System.out.println("] missing"+a.linenumber);
                  while(!(a.token.equals("TK_RSQB ")))
                    {
                    a.nexttoken();
                     }
      }
      }
  
  else if(a.token.equals("TK_ID"))
  {
  //WRITE CODE TO GET VALUE O FTHE ID FROM SYMBOL TABLE
   //id1.size =Integer.parseInt(id1.lexeme);
     if(a.symboltable.containsKey(id1.index)==true)//error handling for symbol table containing the same entry
       {
        System.out.println("Identifier already exists and its lexeme is"+id1.lexeme);
		return;
	  }
     else
       {
        a.symboltable.put(id1.index,id1);
       }
  
  a.nexttoken();//eat TK_ID
  if(a.token.equals("TK_RSQB"))
  {
  a.nexttoken();
  if(a.token.equals("TK_SEMICOLON"));
  {
  return;
  }
  }
  }

  else if(a.token.equals("TK_RSQB"))
  {
  a.nexttoken();
  if(a.token.equals("TK_SEMICOLON"));
  {
  return;
  }
  }
  }
  }
else 
{
System.out.println("stack array index missing"+a.linenumber);
                  while(!(a.token.equals("TK_SEMICOLON ")))
                    {
                    a.nexttoken();
					return;
                     }
}


}//end of void method
}//end of stackarramethod_declaration

class lexpression
{
static void le(root r) throws IOException
{
expressions.e(r);

if(a.token.equals("TK_NEQ"))
{
Operator op = new Operator();
op.type = "!=";
root r1 = r.child;
r.child = op;
op.leftchild = r1;
a.nexttoken();
}

if(a.token.equals("TK_EQ"))
{
Operator op = new Operator();
op.type = "==";
root r1 = r.child;
r.child = op;
op.leftchild = r1;
a.nexttoken();
}

if(a.token.equals("TK_GE"))
{
Operator op = new Operator();
op.type = "<=";
root r1 = r.child;
r.child = op;
op.leftchild = r1;
a.nexttoken();
}

if(a.token.equals("TK_LE"))
{
Operator op = new Operator();
op.type = ">=";
root r1 = r.child;
r.child = op;
op.leftchild = r1;
a.nexttoken();
}

if(a.token.equals("TK_GT"))
{
Operator op = new Operator();
op.type = ">";
root r1 = r.child;
r.child = op;
op.leftchild = r1;
a.nexttoken();
}

if(a.token.equals("TK_LT"))
{
Operator op = new Operator();
op.type = "<";
root r1 = r.child;
r.child = op;
op.leftchild = r1;
a.nexttoken();
}

expressions.e(r);
return;
}//method_lexpressions
}//lEXPRESSIONS


class statements
{
double scope;
ArrayList<statement> s = new ArrayList<statement>();
ArrayList<block> b = new ArrayList<block>();
statement add_statement()
{
statement s2 = new statement();
s.add(s2);
return s2;
}//end of addchild

block add_block()
{
block b2 = new block();
b.add(b2);
return b2;
}//end of addchild

void method_statements() throws IOException
{
if(a.token.equals("TK_LBRACE"))
{
System.out.println("from statements received {");
a.nexttoken();//eat lbrace;
block b1 = add_block();
b1.block_count++;
b1.scope =this.scope +Math.pow(0.1,(double)block.block_count);
/*
int i2 = (double)this.scope;//scope =3.11 i2 = 3.00
double d1 = this.scope-i2;//d1= 3.11-1=0.11
double d2 = d1;//0.11
d1 = d1+d1*0.1;//d1 =0.121
d1=d1-0.1;//d1 = 0.021
d2 = d2-0.1;//d2 = 0.01
d1=d1-2*d2;//0.021-2*0.01=0.001
this.scope = this.scope +d1;//3.11+0.001=3.111
*/
b.add(b1);
System.out.println("\n\nBlocked callled \n\n");
b1.method_block();
System.out.println("Blocked call was over");
method_statements();
return;
}

else if(a.token.equals("TK_IF")||a.token.equals("TK_WHILE")||a.token.equals("TK_ID"))//FIRST OF STATEMENT
{
statement s4 = add_statement();
s4.scope = this.scope;
s4.method_statement();
method_statements();
return;
}

else if(a.token.equals("TK_RETURN"))
{
return;
}
}//end method_statemets
}//end of class

class statement
{
double scope;
assign a1;
function_call f;
while_class wc;
if_class ic;
void method_statement() throws IOException
{
if(a.token.equals("TK_ID"))
{
Identifier id1=a.searchID(a.lexeme,this.scope);
//Identifier i=a.symboltable.get(index);
if(id1==null){System.out.println("qError");return;}
else
	{
	System.out.println("Id value is "+id1.lexeme);
//index entry will be retrived and isArray oy is param ec will be known;
a.nexttoken();//eat id
	if(a.token.equals("TK_EQUAL")||a.token.equals("TK_LSQB"))
	{
	a1 = new assign();
	a1.scope = this.scope;
	a1.r.child=id1; 
	a1.method_assign();
	return;
	}
	
	else if(a.token.equals("TK_LPAREN"))
	{
	f = new function_call();
	f.scope = this.scope;
	//f.id = id1;
	 //f.id.index = index;
	a.nexttoken();//eat LAPREN;
	f.method_functioncall();
	a.nexttoken();
	return;	
	}
	}	
}


else if(a.token.equals("TK_IF"))
{
a.nexttoken();//eat TK_IF
ic = new if_class();
ic.scope = this.scope;
ic.method_if_class();
return;
}

else if(a.token.equals("TK_WHILE"))
{
wc = new while_class();
wc.scope = this.scope;
wc.method_while_class();
return;
}

}//end of method_statement
}//end of class statemet

class block
{
Declarations d;
statements s1;
double scope;
static int block_count;

Declarations add_Declarations()
{
 d = new Declarations();
 return d;
}

 statements add_statements()
{
 s1 = new statements();
 return s1;
}

void method_block() throws IOException
{
System.out.println("from block and it's scope is "+scope+"\n");
if(a.token.equals("TK_INT") || a.token.equals("TK_REAL") ||a.token.equals("TK_CHAR")|| a.token.equals("TK_STRING"))
{

Declarations d1 =  add_Declarations();
d1.scope = this.scope;
d1.method_declarations();

	if(a.token.equals("TK_LBRACE")||a.token.equals("TK_IF")||a.token.equals("TK_WHILE")||a.token.equals("TK_ID"))//FIRST OF STATEMENT
	{
	statements s2 = add_statements();
	s2.scope = this.scope;
	s2.method_statements();
	//after statemnets we will check for }
	
		if(a.token.equals("TK_RBRACE"))
		{
		a.nexttoken();//consume RBRACE;
		return;
		}
	}
		//if we find no statements after declarations
	if(a.token.equals("TK_RBRACE"))
	{
	a.nexttoken();//consume RBRACE;
	return;
	}

}
//if we find no declarations and statements in the block
else if(a.token.equals("TK_RBRACE"))
{
System.out.println("successfullly ccompleted block");
a.nexttoken();//eat rbrace
return;
}

else if(a.token.equals("TK_LBRACE")||a.token.equals("TK_IF")||a.token.equals("TK_WHILE")||a.token.equals("TK_ID"))//FIRST OF STATEMENT
	{
	statements s2 = add_statements();
	s2.scope = this.scope;
	s2.method_statements();
	return;
	}
	
else
{
System.out.println("Error from method_block");
return;	
}



}//end of method_block
}//end of block

class assign
{
double scope;
root r= new root();
void method_assign() throws IOException
{
if(a.token.equals("TK_EQUAL"))
{
Operator op1 = new Operator("TK_EQUAL");
root r1 = r.child;
r.child = op1;
op1.leftchild = r1;
	a.nexttoken();//eat equal
	if(a.token.equals("TK_LPAREN") || a.token.equals("TK_ID") || a.token.equals("TK_LIT_INT") || a.token.equals("TK_LIT_REAL"))//first of expressions
	{
	a.scope = this.scope;//for expressions to get access to current scope
	expressions.e(r);
	System.out.println("**from assign "+a.token);
	return;
	}
	else
	{
	System.out.println("error");
	return;
	}

}

else if(a.token.equals("TK_LSQB"))
{
ArrayIdentifier a1 = new ArrayIdentifier();
a1.id =(Identifier)r.child;
a.nexttoken();//eat LSQB
expressions.e(a1.r);//to caluclate the expresions;//TK_RSQB will be consumed
r.child = a1;
	if(a.token.equals("TK_EQUAL"))
	{
	Operator op1 = new Operator("TK_EQUAL");
	root r1 = r.child;
	r.child = op1;
	op1.leftchild = r1;
	a.nexttoken();//eat TK_EQUAL
		if(a.token.equals("TK_LPAREN") || a.token.equals("TK_ID") || a.token.equals("TK_LIT_INT") || a.token.equals("TK_LIT_REAL"))//first of expressions
		{
		expressions.e(r);
		return;
		}
		else
		{
		System.out.println("error");
		return;
		}
			
	}

	else//if = was not found after id[]
	{
	System.out.println("error");
	return;
	}
}

else//for not finding = or [
{
System.out.println("error");
return;
}

}//end of method_assign

}//end of class assign


class if_class
{
double scope;
root Lexpression;
statements s1;
void method_if_class() throws IOException
{
		
		if(a.token.equals("TK_LPAREN "))
		{
             a.scope = this.scope;
			lexpression.le(Lexpression);
			method_if_class();
			return;
		}
         
		//after if(lexprn ) will be consumed at expressions
        else if(a.token.equals("TK_LBRACE "))
                 {
				 a.nexttoken();//eat LBRACE
                 s1 = new statements();
				 s1.scope = this.scope;
				 return;
				 }
				 
		  else
              {
                    System.out.println("} missing"+a.linenumber);
                  while(!(a.token.equals("TK_SEMICOLON ")))
                    {
                    a.nexttoken();
					return;
                    }
              }
                
               
}//void metod_if_class
}//end of if_class

class while_class
{
double scope;
root Lexpression;//for holding Lexpression
statements s1;
void method_while_class() throws IOException
{ 
		
		if(a.token.equals("TK_LPAREN "))
		{
             a.scope = this.scope;
			lexpression.le(Lexpression);
			method_while_class();
			return;
		}
         
		//after if(lexprn ) will be consumed in expressions
        else if(a.token.equals("TK_LBRACE "))
                 {
				 a.nexttoken();//eat LBRACE
                 s1 = new statements();
				 s1.scope = this.scope;
				 return;
				 }
				 
		  else
              {
                    System.out.println("} missing"+a.linenumber);
                  while(!(a.token.equals("TK_SEMICOLON ")))
                    {
                    a.nexttoken();
					return;
                    }
              }
                
               
}//void metod_while_class

}//end of while_class

class ArrayIdentifier extends root
{
Identifier id;//for holding id of array
root r;//for holding expression
}

class function_call
{
Identifier id;
arglist al ;
int no_args=0;
double scope;
void method_functioncall() throws IOException
{
if(a.token.equals("TK_ID")||a.token.equals("TK_LIT_INT")||a.token.equals("TK_LIT_CHAR")||a.token.equals("TK_LIT_REAL")||a.token.equals("TK_LIT_STRING"))
{
al = new arglist();
al.scope = this.scope;
al.method_arglist();
this.no_args = al.no_args;
return;
}

else if(a.token.equals("TK_RPAREN"))//function with no arguments
{
a.nexttoken();//eat TK_RPAREN
	if(a.token.equals("TK_SEMICOLON"));
	{
	a.nexttoken();//eat semicolon
	return;
	}
}
else 
{
System.out.println("Error");
return;
}
}//method_functioncall
}//class function_call 

class arglist
{
double scope;
ArrayList<arg> ar = new ArrayList<arg>();	
int no_args=0;
void method_arglist() throws IOException
{
if(a.token.equals("TK_ID"))
{
char a1[];
String s=Double.toString(scope);
a1 = s.toCharArray();
String key = a.lexeme +Double.toString(this.scope);
int i = a1.length;
String s2;
	while(i>2)
	{
	s2 = new String(a1,0,i);
	key = a.lexeme +s2;
	if(a.symboltable.containsKey(key)==true)
	{
		no_args++;
		Identifier id2=(Identifier) a.symboltable.get(key);
		arg arg1 = new arg();
		arg1.value = id2.value;
		ar.add(arg1);
		a.nexttoken();
		method_arglist();
		return;
	}
	else
	{
	i--;
	}
	}
	//checking in function's declarations
	s2 = new String(a1,0,2);
	s2 = s2+"0";
	key = a.lexeme + s2;
	if(a.symboltable.containsKey(key)==true)
	{
		no_args++;
	Identifier id2= (Identifier)a.symboltable.get(key);
		arg arg1 = new arg();
		arg1.value = id2.value;
		ar.add(arg1);
		a.nexttoken();
		method_arglist();
		return;
	}
	//checking for gloabl declarations
	
	else 
	{
	key = a.lexeme +"1.0";		
	if(a.symboltable.containsKey(key)==true)
	{
		no_args++;
	Identifier id2= (Identifier)a.symboltable.get(key);
		arg arg1 = new arg();
		arg1.value = id2.value;
		ar.add(arg1);
		a.nexttoken();
		method_arglist();
		return;
	}
	
	else
	{
	System.out.println(a.lexeme+"was not found");	
	return;
	}
	}
}

else if(a.token.equals("TK_RPAREN"))
{
a.nexttoken();
return;
}

else if(a.token.equals("TK_COMMA"))
{
a.nexttoken();
method_arglist();
return;
}

else if(a.token.equals("TK_LIT_INT"))
{
		no_args++;
		arg arg1 = new arg();
		arg1.value = a.lexeme;
		ar.add(arg1);
		a.nexttoken();
		method_arglist();
		return;
}

else if(a.token.equals("TK_LIT_CHAR"))
{
		no_args++;
		arg arg1 = new arg();
		arg1.value = a.lexeme;
		ar.add(arg1);
		a.nexttoken();
		method_arglist();
		return;
}

else if(a.token.equals("TK_LIT_REAL"))
{
		no_args++;
		arg arg1 = new arg();
		arg1.value = a.lexeme;
		ar.add(arg1);
		a.nexttoken();
		method_arglist();
		return;
}

else if(a.token.equals("TK_LIT_STRING"))
{
		no_args++;
		arg arg1 = new arg();
		arg1.value = a.lexeme;
		ar.add(arg1);
		a.nexttoken();
		method_arglist();
		return;
}

}//method_arglist
}//class arglist

class arg
{
String value;
}//class arg









