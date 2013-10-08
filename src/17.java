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
void method_functioncall()
{
if(a.token.equals("TK_ID")||a.token.equals("TK_LIT_INT")||a.token.equals("TK_LIT_CHAR")||a.token.equals("TK_LIT_REAL")||a.token.equals("TK_LIT_STRING"))
{
al = new arglist();
a1.scope = this.scope;
a1.method_arglist();
this.no_args = a1.no_args;
return;
}

else if(a.token.equals("TK_RPAREN"))//function with no arguments
{
a.nexttoken();//eat TK_RPAREN
	if(a.token.equals("TK_SEMICOLON");
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
ArrayList<args> ar = new ArrayList<args>();	
int no_args=0;
void method_arglist()
{
if(a.token.equals("TK_ID"))
{
char a1;
String s=Double.toString(scope);
a1 = s.to CharArray();
String key = a.lexeme +Double.toString(this.scope);
i = a1.getlength;
String s2;
	while(i>2)
	{
	s2 = new String(a1,0,i);
	key = a.lexeme +s2;
	if(a.symboltable.containsKey(key)==true)
	{
		no_args++;
		Identifier id2= a.symboltable.get(key);
		arg1 = new arg();
		arg1.value = id2.value;
		ar.add(arg1);
		a.nexttoken();
		method_arglist();
		return;
	}
	i--;
	}
	//checking in function's declarations
	s2 = new String(a1,0,2);
	s2 = s2+"0";
	key = a.lexeme + s2;
	if(a.symboltable.containsKey(key)==true)
	{
		no_args++;
	Identifier id2= a.symboltable.get(key);
		arg1 = new arg();
		arg1.value = id2.value;
		ar.add(arg1);
		a.nexttoken();
		method_arglist();
		return;
	}
	//checking for gloabl declarations
	key = a.lexeme +"1.0";
	else if(a.symboltable.containsKey(key)==true)
	{
		no_args++;
	Identifier id2= a.symboltable.get(key);
		arg1 = new arg();
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
		arg1 = new arg();
		arg1.value = a.lexeme;
		ar.add(arg1);
		a.nexttoken();
		method_arglist();
		return;
}

else if(a.token.equals("TK_LIT_CHAR"))
{
		no_args++;
		arg1 = new arg();
		arg1.value = a.lexeme;
		ar.add(arg1);
		a.nexttoken();
		method_arglist();
		return;
}

else if(a.token.equals("TK_LIT_REAL"))
{
		no_args++;
		arg1 = new arg();
		arg1.value = a.lexeme;
		ar.add(arg1);
		a.nexttoken();
		method_arglist();
		return;
}

else if(a.token.equals("TK_LIT_STRING"))
{
		no_args++;
		arg1 = new arg();
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