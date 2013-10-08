import java.util.*;
import java.io.*;
class ast
{
static double function_no_scope=1.0;//1.0 is for main and 0.0 is for global
public static void main(String args[]) throws IOException
{
a a1 = new a();
root_node rootnode = new root_node();
a.nexttoken();
int i=0;
while((!a.token.equals("TK_EOF")))
{
if(a.token.equals("TK_INT") || a.token.equals("TK_REAL") ||a.token.equals("TK_CHAR")|| a.token.equals("TK_STRING"))
{
Declarations d =(Declarations)rootnode.addchild(rootnode,"Declarations");
d.scope = 1.0;//global scope
d.method_declarations();//consumes entire declaration
}

else if(a.token.equals("TK_ARRAY"))
{
ArrayDeclarations a11 = (ArrayDeclarations)rootnode.addchild(rootnode,"ArrayDeclarations");
a11.scope = 1.0;//global scope
a11.arraymethod_declarations();
}

else if (a.token.equals("TK_FUNCTION"))
{
function f1 =(function)rootnode.addchild(rootnode,"function");
f1.scope = function_no_scope+1.0;
function_no_scope=function_no_scope+1.0;//function_count=no.of.functions
f1.m_function();
a.nexttoken();
}

else
{
i++;
break;
}
/*else if(a1.token.equals("TK_MAIN"))
{
mainmethod m1 = (mainmethod)rootnode.addchild(rootnode,"main"); 
m1.scope = 0;
m1.m_function(rootnode);
}*/
}
Declaration id =(Declaration)rootnode.d.declarationlist.get(1);
System.out.println("Declaration type"+id.type+" and size is "+id.totalsize);
ArrayDeclaration ad = (ArrayDeclaration)rootnode.a.arraydeclarationlist.get(0);
System.out.println("Array size is "+ad.i1.size +"  and name is "+ ad.i1.lexeme);
function f = (function)rootnode.method_list.get(0);
System.out.println("function name is "+ f.name +"no .of parameters is "+ f.no_of_params +" and size of params is "+f.totalsize_of_params);
System.out.println("decalarations in the function "+((Declaration)f.d.declarationlist.get(0)).type);



//end of while
}//end of main

}//end of class ast