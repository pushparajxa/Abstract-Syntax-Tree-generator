
class root_node
{
Declarations d;
ArrayDeclarations a;
Arraylist<function> method_list  = new ArrayList<function>();
mainmethod m ;
Object addchild(root_node r,String s)
{
//switch statement we cann't use as we cann't compare to strings change to else if

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

else if(s.equals("function"))
{
function m1 = new function();
r.method_list.add(m1);

return m1;
}

else if(s.equals("main"))
{
r.m = new mainmethod();
return r.m;
}

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

void method_declarations()
{
if(a.token.equals("TK_INT") || a1.token.equals("TK_REAL") ||a1.token.equals("TK_CHAR")|| a1.token.equals("TK_STRING"))
{
Declaration d1 = this.addchild();
d1.scope = this.scope;
d1.method_declaration();
this.totalsize = this.totalsize+ d1.totalsize;
method_declarations();
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
return d1.il;
}

void method_declaration()
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
this.totalsize = i1.totalsize;
return;
}

else
{
System.out.println("variable name missing"+a.linenumber);
while(!(a.token.equals("TK_SEMICOLON ")))
{
a.nexttoken();
}
}

}

}
class Idlist
{
String type;
int size;
ArrayList<Identifier> id = new ArrayList<Identifier>();

Identifier addchild()
{
Identifier f1= new Identifier();
id.add(f1);
return f1;
}

void method_idlist()
{
if(a.token.equals("TK_ID"))
{
Identifier id1 = this.addchild();
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
}
if(a.symboltable.containsKey(id1.index)==true)//error handling for symbol table containing the same entry
{
System.out.pritln("Identifier already exists and its lexeme is"+id1.lexeme);
}
else
{
a.symboltable.put(id1.index,id1);
}
this.size = this.size + id1.size;
a.nexttoken();
if(a.token.equals("TK_COMMA")) //eat comma and get nexttoken
{
a.nexttoken();
method_idlist();

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
boolean isMethod = false;
}


class ArrayDeclarations
{
int totalsize;
int location;
ArrayList<ArrayDeclaration> arraydeclarationlist = new ArrayList<ArrayDeclaration>();

ArrayDeclaration addchild()
{
ArrayDeclaration ad1 = new ArrayDeclaration();
arraydeclarationlist.add(ad1);
return ad1;
}

void arraymethod_declarations()
{
if(a.token.equals("TK_ARRAY"))
{
a.nexttoken();
if(a.token.equals("TK_INT") || a.token.equals("TK_REAL") ||a.token.equals("TK_CHAR")||a.token.equals("TK_STRING"))
{
ArrayDeclaration ad1 = this.addchild();
ad1.arraymethod_declaration();
this.totalsize = this.totalsize+ ad1.totalsize;
arraymethod_declarations();
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
String type;
int size;
Identifier i1;

Identifier addchild()
{
i1= new Identifier();
return i1;
}

void arraymethod_declaration()
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
id1.scope= a.scope;
id1.index = id1.lexeme+Double.toString(id1.scope);
id1.isArray = true;


a.nexttoken();
if(a.token.equals("TK_LSQB"))
{
a.nexttoken();
  if(a.token.equals("TK_LIT_INT"))
  {
     id1.size =Integer.parseInt(id1.lexeme);
     
      if(a.symboltable.containsKey(id1.index)==true)//error handling for symbol table containing the same entry
       {
        System.out.pritln("Identifier already exists and its lexeme is"+id1.lexeme);
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
double  scope;
paramlist p1;
Declarations d;
StaticArrayDeclarations s;
Statements s1;

void m_function(root_node r1)
{
this.scope = a.scope +1;
if(a.token.equals("TK_FUNCTION"))
{
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
		i1.function_index=r1.method_list.indexOf(this);
		Identifier i1 = new Identifier();
		i1.isFunction = true;
		i1.location = a.linenumber;
		i1.scope= this.scope;
		i1.lexeme = a.lexeme;
		i1.type = "FUNCTION";
		i1.index = i1.lexeme+Double.toString(i1.scope);
		a.nexttoken();//LBRACE will be the token
		a.nexttoken();//eat LPAREN
		if(a.symboltable.containsKey(id1.index)==true)//error handling for symbol table containing the same entry
		{
		System.out.pritln("function with the following name already exists and its lexeme is"+id1.lexeme);
		return;//set error in ast function as 1;
		}
		else
		{
		a.symboltable.put(id1.index,id1);
		}
		}
			
		
		if(a.token.equals("TK_INT") ||a.token.equals("TK_REAL") ||a.token.equals("TK_CHAR") || a.token.equals("TK_STRING"))
		{
		p1 = new paramlist();
		p1.m_paramlist();
		this.no_of_params = p1.no_of_params;
		}
		              
		if(a.token.equals("TK_LBRACE"))
		{
		a.nexttoken();//EAT LBRACE
		}
				
		if(a.token.equals("TK_INT") || a.token.equals("TK_REAL") ||a.token.equals("TK_CHAR")|| a.token.equals("TK_STRING"))
		{
			 d = new Declarations();
			 d.scope = this.scope;
			d.method_declations();
		}
                       
             if(a.token.equals("TK_ARRAY"))
                {
                 sa = new StackArrayDeclarations();
				sa.scope = this.scope;
		 sa.stackarraymethod_declarations();
                }
		
		if(a.token.equals("TK_LBRACE")||a.token.equals("TK_IF")||a.token.equals("TK_WHILE")||a.token.equals("TK_ID"))//FIRST OF STATEMENT
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
				a.nextoken();
			}
		}
		if(a.token.equals("TK_RBRACE"))
				{
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
void m_paramlist()
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
		return;
		a.nexttoken();
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
Sring type;
int size;
Identifier id1;

void m_param()
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
System.out.pritln("Identifier already exists and its lexeme is"+id1.lexeme);
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

static void e(root r)
{
t(r);
e_p(r);
return;
}

static void e_p(root r)
{
if(a.token.equals("TK_PLUS"))
{
Operator op = new Operator();
op.lexeme = "*";
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
Operartor op = new Operator();
op.lexeme = "*";
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
else if(a.token.equals("TK_RBRACE"))
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
System.out.println("Error");
return;
}

}//end of void e_p

static void t(root r)
{
f(r);
t_p(r);

}

static void t_p(root r)
{
if(a.token.equals("TK_ASTERISK"))
{

Operartor op = new Operator();
op.lexeme = "*";
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
Operartor op = new Operator();
op.lexeme = "*";
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

static void f(root r)
{
if(a.token.equals("TK_ID"))
{
a.nexttoken();
if(a.token.equals("TK_LSQB"))
{
a.nexttoken();
e(r);
return;

}

Identifier i;//get from symbol table the corresponding Identifier object;
if(r.child == null)
{
r.child =i;
a.nexttoken();
return; 
}

else
{
r.child.rightchild = i;
a.nexttoken();
return;
}

}



}//end of f

}//end of class expressions

class root
{
root child=null;
}


class Operator extends root
{
String type;
root leftchild;
root rightchild;
String value;
void Operator(String s)
{
this.type = s;
}

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

void stackarraymethod_declarations()
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
}//inner if
}//outer if
return;
}//end of void method
}

class StackArrayDeclaration
{
String type;
int size;
Identifier i1;
double scope;
Identifier addchild()
{
i1= new Identifier();
return i1;
}

void stackarraymethod_declaration()
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
     id1.size =Integer.parseInt(id1.lexeme);
     if(a.symboltable.containsKey(id1.index)==true)//error handling for symbol table containing the same entry
       {
        System.out.pritln("Identifier already exists and its lexeme is"+id1.lexeme);
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
        System.out.pritln("Identifier already exists and its lexeme is"+id1.lexeme);
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
  return;
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

class lexpressions
{
static void le(root r)
{
expressions.e(r);

if(a.token.equals("TK_NEQ"))
{
Operator op = new Operator();
op.lexeme = "!=";
root r1 = r.child;
r.child = op;
op.leftchild = r1;
a.nexttoken();
}

if(a.token.equals("TK_EQ"))
{
Operator op = new Operator();
op.lexeme = "==";
root r1 = r.child;
r.child = op;
op.leftchild = r1;
a.nexttoken();
}

if(a.token.equals("TK_GE"))
{
Operator op = new Operator();
op.lexeme = "<=";
root r1 = r.child;
r.child = op;
op.leftchild = r1;
a.nexttoken();
}

if(a.token.equals("TK_LE"))
{
Operator op = new Operator();
op.lexeme = ">=";
root r1 = r.child;
r.child = op;
op.leftchild = r1;
a.nexttoken();
}

if(a.token.equals("TK_GT"))
{
Operator op = new Operator();
op.lexeme = ">";
root r1 = r.child;
r.child = op;
op.leftchild = r1;
a.nexttoken();
}

if(a.token.equals("TK_LT"))
{
Operator op = new Operator();
op.lexeme = "<";
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
ArrayList<statement> s = new ArrayList<block>();
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

void method_statements()
{
if(a.token.equals("TK_LBRACE"))
{
a.nexttoken();//eat lbrace;
block b1 = add_block();
b1.block_count++;
b1.scope =this.scope +Math.pow(1.0,(double)b1.block_count);
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
b1.method_block();
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
void method_statement()
{
if(a.token.equals("TK_ID"))
{
//Identifier i=a.symboltable.get(index);
Identifier id1;

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
	f.id = id1;
	 f.id.index = index;
	a.nexttoken();//eat LAPREN;
	f.method_functioncall();
	return;	
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

void method_block()
{
if(a.token.equals("TK_INT") || a.token.equals("TK_REAL") ||a.token.equals("TK_CHAR")|| a.token.equals("TK_STRING"))
{
Declarations d1 =  add_Declarations();
d1.scope = this.scope;
d1.method_declations();

	if(a.token.equals("TK_LBRACE")||a.token.equals("TK_IF")||a.token.equals("TK_WHILE")||a.token.equals("TK_ID"))//FIRST OF STATEMENT
	{
	statements s2 = add_statements();
	s2.scope = this.scope;
	s2.method_statements();
	
	}
		
	else if(a.token.equals("TK_RBRACE"))
	{
	a.nexttoken();//consume RBRACE;
	return;
	}

}

}//end of method_block
}//end of block

class assign
{
double scope;
root r;
void method_assign()
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
a1.id = r.child;
a.nexttoken();//eat LSQB
expressions.e(a1.r);//to caluclate the expresions;//TK_RSQB will be consumed
root.child = a1;
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
Statements s1;
void method_if_class()
{
		
		if(a.token.equals("TK_LPAREN "))
		{
             a.scope = this.scope;
			lexpression.le(Lexpressions);
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
Statements s1;
void method_while_class()
{
		
		if(a.token.equals("TK_LPAREN "))
		{
             a.scope = this.scope;
			lexpression.le(Lexpressions);
			method_if_class();
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
ArrayList<args> ar = new ArrayList<args>();	
int no_args=0;
void method_arglist()
{
if(a.token.equals("TK_ID"))
{
char a1;
String s=Double.toString(scope);
a1 = s.toCharArray();
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
	Identifier id2= a.symboltable.get(key);
		arg1 = new arg();
		arg1.value = id2.value;
		ar.add(arg1);
		a.nexttoken();
		method_arglist();
		return;
	}
	//checking for gloabl declarations
	
	else if(a.symboltable.containsKey(key)==true)
	{
	key = a.lexeme +"1.0";		
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


